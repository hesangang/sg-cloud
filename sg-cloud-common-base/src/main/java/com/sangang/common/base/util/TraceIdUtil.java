package com.sangang.common.base.util;

import cn.hutool.core.util.StrUtil;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>traceId工具类</P>
 *
 */
public class TraceIdUtil {
    public static final String TRACE_ID = "requestId";
    /**
     * 当traceId为空时，显示的traceId。随意。
     */
    private static final String DEFAULT_TRACE_ID = "0";

    /**
     * 设置traceId
     */
    public static void setTraceId(String traceId) {
        //如果参数为空，则设置默认traceId
        traceId = StrUtil.isBlank(traceId) ? DEFAULT_TRACE_ID : traceId;
        //将traceId放到MDC中
        MDC.put(TRACE_ID, traceId);
    }

    /**
     * 获取traceId
     */
    public static String getTraceId() {
        //获取
        String traceId = MDC.get(TRACE_ID);
        //如果traceId为空，则返回默认值
        return StrUtil.isBlank(traceId) ? DEFAULT_TRACE_ID : traceId;
    }

    /**
     * 判断traceId为默认值
     */
    public static Boolean defaultTraceId(String traceId) {
        return DEFAULT_TRACE_ID.equals(traceId);
    }

    /**
     * 生成traceId
     */
    public static String genTraceId() {

        String str = getRandom(6);
        return UUID.randomUUID().toString().replace("-", "") + str;
    }

    public static void destroyTraceId(){
        MDC.remove(TRACE_ID);
    }

    private static String getRandom(int length) {
        List<String> lstRandom = new ArrayList<>();
        while (lstRandom.size() < length) {
            String random = ThreadLocalRandom.current().nextInt(0, 9) + "";
            if (!lstRandom.contains(random)) {
                lstRandom.add(random);
            }
        }
        String str = String.join("", lstRandom);
        return str;
    }
}

