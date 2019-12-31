package com.cjr.securitytest.web.controller;

import com.cjr.securitytest.web.dto.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@Slf4j
public class Test {


    /**
     * 替换企业
     * @return
     */
    @RequestMapping("/helloWorld")
    public BaseResponse helloWorld() {
        log.info("info-helloWorld....");
        log.warn("warn-helloWorld....");
        return BaseResponse.createSuccessResult("helloWorld");
    }


}
