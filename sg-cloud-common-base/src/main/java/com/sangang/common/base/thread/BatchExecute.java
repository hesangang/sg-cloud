package com.sangang.common.base.thread;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author sangang
 */
@Slf4j
@Component
public class BatchExecute {

    @Value("${server.tomcat.batchExecuteThreadSize: 600}")
    private int threadsMax;

    private static ExecutorService threadPool = null;
    //测试使用： 定义多线程数量，一般就是任务数量
    private static CountDownLatch countDownLatch = null;

    /**
     * 批量执行
     *
     * @param tasks          批量待执行任务
     * @param countDownLatch
     * @param timeOut        批量执行超时时间
     * @param timeUnit       批量执行时间单位
     * @return
     */
    public Map<String, Object> execute(List<Callable<Resp>> tasks, CountDownLatch countDownLatch, Long timeOut, TimeUnit timeUnit) {

        Long startTime = System.currentTimeMillis();

        ExecutorService threadPool = null;
        Map<String, Object> respMap = new HashMap<>();
        try {
            if (null == tasks || tasks.isEmpty()) {
                log.error("批量待执行任务为空");
                return respMap;
            }

            threadPool = getThreadPool();
            if (null == threadPool) {
                log.error("程序池初始化失败");
                return respMap;
            }

            List<Future<Resp>> futureList = threadPool.invokeAll(tasks, timeOut, timeUnit);

            log.info("------------------------------------------  剩下未执行线程数    {}     ", countDownLatch.getCount());
            countDownLatch.await();//等待,不断检测数量是否为0，为零是执行后面的操作
            log.info("------------------------------------------  剩下未执行线程数    {}     ", countDownLatch.getCount());

            if (CollectionUtil.isNotEmpty(futureList) && futureList.size() > 0) {
                for (int i = 0; i < futureList.size(); i++) {

                    try {
                        Future<Resp> future = futureList.get(i);
                        if (future != null) {
                            Resp resp = future.get();
                            if (resp != null) {
                                respMap.put(resp.getTaskId(), resp.getReturnObj());
                                log.info("线程执行结果future:{}", resp.toString());
                            }
                        } else {
                            log.info("线程执行结果future:{}", future);
                        }
                    } catch (Exception e) {
                        log.info("FutureList.get 线程执行异常：{}", e);
                    }
                }
            }
        } catch (Exception e) {
            log.error("InvokeAll 线程执行异常：{},threadId:{}", e, Thread.currentThread().getId());
        }

        Long endTime = System.currentTimeMillis();

        if((endTime-startTime)>2000){

            log.warn("Tasks execution time is too long . TaskSize:{}  " ,tasks.size());

        }

        return respMap;
    }

    public Map<String, Object> execute(List<Callable<Resp>> tasks, Long timeOut, TimeUnit timeUnit) {

        Long startTime = System.currentTimeMillis();

        ExecutorService threadPool = null;
        Map<String, Object> respMap = new HashMap<>();
        try {
            if (null == tasks || tasks.isEmpty()) {
                log.error("批量待执行任务为空");
                return respMap;
            }

            threadPool = getThreadPool();
            if (null == threadPool) {
                log.error("程序池初始化失败");
                return respMap;
            }

            List<Future<Resp>> futureList = threadPool.invokeAll(tasks, timeOut, timeUnit);

            if (CollectionUtil.isNotEmpty(futureList) && futureList.size() > 0) {
                for (int i = 0; i < futureList.size(); i++) {

                    try {
                        Future<Resp> future = futureList.get(i);
                        if (future != null) {
                            Resp resp = future.get();
                            if (resp != null) {
                                respMap.put(resp.getTaskId(), resp.getReturnObj());
                                log.info("线程执行结果future:{}", resp.toString());
                            }
                        } else {
                            log.info("线程执行结果future:{}", future);
                        }
                    } catch (Exception e) {
                        log.info("FutureList.get 线程执行异常：{}", e);
                    }
                }
            }
        } catch (Exception e) {
            log.error("InvokeAll 线程执行异常：{},threadId:{}", e, Thread.currentThread().getId());
        }

        Long endTime = System.currentTimeMillis();

        if((endTime-startTime)>2000){

            log.warn("Tasks execution time is too long . TaskSize:{}  " ,tasks.size());

        }

        return respMap;
    }

    /**
     * 关闭线程池
     *
     * @param threadPool
     * @param awitTimeOut
     * @param timeUnit
     */
    private void awaitAfterShutdown(ExecutorService threadPool, Long awitTimeOut, TimeUnit timeUnit) {
        //通知线程池，线程执行完毕后关闭线程
        threadPool.shutdown();
        try {
            //awitTimeOut内线程如果还有没有执行完毕的则强制关闭线程
            if (!threadPool.awaitTermination(awitTimeOut, timeUnit)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException e0) {
            log.error("关闭线程池发生异常:{}", e0);
            try {
                Thread.currentThread().interrupt();
            } catch (Exception ee1) {
            }
        } catch (Exception ex) {
            log.error("关闭线程池发生异常:{}", ex);
            try {
                if (threadPool != null) {
                    threadPool.shutdownNow();
                }
            } catch (Exception ee2) {
            }
        }
    }

    /**
     * 获取线程池
     *
     * @return
     */
    private ExecutorService getThreadPool() {
        if (ObjectUtil.isNull(threadPool)) {
            initThreadPool();
        }
        return threadPool;
    }

    public static class Resp {
        private transient Map previous = MDC.getCopyOfContextMap();
        private String taskId;
        private Object returnObj;

        public Resp() {
        }

        public Resp(String taskId, Map mdcContext) {
            this.taskId = taskId;
            this.setMdc(mdcContext);
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public Object getReturnObj() {
            return returnObj;
        }

        public Resp getReturnResp(){
            return (Resp)returnObj;
        }

        public void setReturnObj(Object returnObj) {
            this.returnObj = returnObj;
        }

        public void setMdc(Map mdcContext) {
            if (mdcContext == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(mdcContext);
            }
        }

        public void clearMdc() {
            if (previous == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(previous);
            }
        }

        @Override
        public String toString() {
            return "Resp{" +
                    "taskId='" + taskId + '\'' +
                    ", returnObj=" + returnObj +
                    '}';
        }

    }

    //@PostConstruct
    private synchronized void initThreadPool() {
        TimeUnit unit = TimeUnit.SECONDS;
        long keepAliveTime = 0L;
        int poolSize = getThreadSize();
        LinkedBlockingQueue workQueue = new LinkedBlockingQueue<Runnable>(2000);
        Map<String, String> mdcContext = MDC.getCopyOfContextMap();
        threadPool = new MdcThreadPoolExecutor(mdcContext, poolSize, poolSize, keepAliveTime, unit, workQueue);
    }

    public int getThreadSize() {
        // 获取处理器数量
        int cpuNum = Runtime.getRuntime().availableProcessors();
        // 根据cpu数量,计算出合理的线程并发数
        int threadNum = cpuNum * 10 + 1;
        if (threadsMax > 0) {
            threadNum = threadsMax;
        }
        return threadNum;
    }


    public ExecutorService getExecutorService() {
        return threadPool;
    }


    /*public static void main(String[] args) {
        List<Callable<Resp>> tasks = new ArrayList<>();
        String user_info = "user_info_task_";
        String school_info = "school_info_task_";
        tasks.add(new Callable<Resp>() {
            @Override
            public Resp call() throws Exception {
                Resp resp = new Resp();
                resp.setTaskId(user_info);
                //TODO 此处执行获取用户信息代码，返回用户信息赋值给
                String user_data = "用户信息_" + Thread.currentThread().getName();
                resp.setReturnObj(user_data);
                return resp;
            }
        });
        tasks.add(new Callable<Resp>() {
            @Override
            public Resp call() throws Exception {
                Resp resp = new Resp();
                resp.setTaskId(school_info);
                //TODO 此处执行获取院校信息代码，返回院校信息赋值给
                String school_data = "院校信息_" + Thread.currentThread().getName();
                resp.setReturnObj(school_data);

                return resp;
            }
        });
        BatchExecute batchExecute = new BatchExecute();
        countDownLatch = new CountDownLatch(tasks.size());
        Map<String, Object> target = batchExecute.execute(tasks, countDownLatch, 60L, TimeUnit.MILLISECONDS);
        log.info(target.toString());
    }*/
}
