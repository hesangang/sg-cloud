# Read Me First

The following was discovered as part of building this project:

* The JVM level was changed from '1.8' to '17', review
  the [JDK Version Range](https://github.com/spring-projects/spring-framework/wiki/Spring-Framework-Versions#jdk-version-range)
  on the wiki for more details.

# 单点登录



* [gradle download](https://gradle.org/releases/)

* [sso demo](https://blog.csdn.net/liu320yj/article/details/127165851)

* [jdk17 download](https://www.oracle.com/cn/java/technologies/downloads/#jdk17-windows)

* [idea config jdk8/17](https://blog.csdn.net/weixin_43847283/article/details/129977741)

* [spring-cloud-alibaba](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)

* [nacos-sql](https://github.com/alibaba/nacos/blob/master/distribution/conf/mysql-schema.sql)

* [office模版填充](https://blog.csdn.net/yangbaggio/article/details/106436341)
* [office模版填充换行](https://www.codenong.com/14830667/)
* [office在线编辑](https://blog.csdn.net/qq_38238956/article/details/128411391)


- 管理端页面 /browser/dist/admin/admin.html

- 打开文档页面 /browser/dist/framed.doc.html

## ssl证书

* [openssl安装教程](https://www.cnblogs.com/dingshaohua/p/12271280.html)
* [openssl便捷版安装包](http://slproweb.com/products/Win32OpenSSL.html)
  

```shell
# 生成私钥(Generate a private key) : server.key
openssl genrsa -des3 -out server.key 2048

# Generate a CSR : server.csr
openssl req -new -key server.key -out server.csr

# Remove Passphrase from key : server.key.org 、server.crt
cp server.key server.key.org
openssl rsa -in server.key.org -out server.key

# 产生自签名证书(Generate self signed certificate): server.crt
openssl x509 -req -days 365 -in server.csr -signkey server.key -out server.crt
  
```


  





