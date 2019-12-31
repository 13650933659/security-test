package com.cjr.securitytest.web.dto.response;

import com.cjr.securitytest.web.dto.enums.StatusCode;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: 通用的请求返回值对象
 * @author: Chenjiaru
 * @create: 2019-08-04 15:26
 */
@Data
public class BaseResponse {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 返回的数据
     */
    private Object data;


    public BaseResponse(){
    }

    public BaseResponse(Integer status, Object data, String message) {
        this.code = status;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 获取请求失败返回对象
     * @param msg 返回信息
     * @return
     */
    public static BaseResponse createFailResult(String msg) {
        msg = StringUtils.isNotBlank(msg) ? msg : StatusCode.FAIL.getMessage();
        return new BaseResponse(StatusCode.FAIL.getStatusCode(), msg);
    }

    /**
     * 获取请求成功返回对象
     * @param msg 返回信息
     * @return
     */
    public static BaseResponse createSuccessResult(String msg) {
        msg = StringUtils.isNotBlank(msg) ? msg : StatusCode.SUCCESS.getMessage();
        return new BaseResponse(StatusCode.SUCCESS.getStatusCode(), msg);
    }


}
