package com.cjr.securitytest.web.dto.enums;
/**
 * @description: 状态码枚举类
 * @author: Chenjiaru
 * @create: 2019-08-04 15:26
 */
public enum StatusCode {

    /**
     * 成功状态码
     */
    SUCCESS(200, "请求成功"),
    /**
     * 失败状态码
     */
    FAIL(300, "请求失败"),

    /**
     * 异常
     */
    EXCEPTION(500, "服务器异常")
    ;

    /**
     * 状态码
     */
    private Integer statusCode = 200;

    /**
     * 描述信息
     */
    private String message;

    StatusCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
