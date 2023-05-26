package com.sangang.common.base.constant;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * @author sangang
 */
public interface SecurityConstant {

    /**
     * token分割
     */
    public static final String TOKEN_PREFIX = "T";
    /**
     * JWT签名加密key
     */
    public static final String JWT_SIGN_KEY = DigestUtil.md5Hex(TOKEN_PREFIX);
    /**
     * token参数头
     */
    public static final String OAUTH_TOKEN = "oauthToken";

    String JWT_TOKEN_HEADER = "header";
    /**
     * token参数头
     */
    public static final String TOKEN = "Token";
    /**
     * author参数头
     */
    public static final String AUTHORITIES = "authorities";
    /**
     * 用户信息Http请求头
     */
    String USER_TOKEN_HEADER = "loginUser";
    /**
     * author的token有效期
     */
    public static final Integer OAUTH_OFFSET_DAY = 1;
    /**
     * C端用户的token有效期
     */
    public static final Integer C_OFFSET_DAY = 7;
    /**
     * B端用户的token有效期
     */
    public static final Integer B_OFFSET_DAY = 30;

    /**
     * 系统固定不进行认证，直接放行的URL，供WebSecurityConfig、ResourceServerConfig公用
     */
    String[] PATTERN_URLS = {
            "/instances",
            "/actuator/**",
            "/druid/**",
            "/assets/**",
            "/webjars/**",
            "/error",
            "/swagger-resources",
            "/configuration/security",
            "/swagger-ui.html/**",
            "/swagger-ui.html",
            "/api/swagger-ui.html",
            "/docs.html",
            "/doc.html",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/favicon.ico"
    };

}
