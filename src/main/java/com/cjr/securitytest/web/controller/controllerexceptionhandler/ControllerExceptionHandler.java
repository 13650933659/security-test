package com.cjr.securitytest.web.controller.controllerexceptionhandler;

import com.cjr.securitytest.web.dto.enums.StatusCode;
import com.cjr.securitytest.web.dto.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * springmvc的控制器的增强器（处理所有控制器抛出的异常，以后有自己的异常可以扩展,统一处理）
 */
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

	/**
	 * 捕捉{@link Exception}异常，并且处理
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public BaseResponse handleUserNotExistException(Exception ex) {
		ex.printStackTrace();
		log.warn(ex.getMessage());
		return BaseResponse.createFailResult(StatusCode.FAIL.getMessage());
	}

}
