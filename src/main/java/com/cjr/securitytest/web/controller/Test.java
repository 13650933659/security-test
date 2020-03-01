package com.cjr.securitytest.web.controller;

import com.cjr.securitytest.web.dto.response.BaseResponse;
import com.cjr.securitytest.web.security.dal.entity.User;
import com.cjr.securitytest.web.security.dal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@Slf4j
public class Test {

    @Autowired
    private UserService userService;

    /**
     * 替换企业
     * @return
     */
    @RequestMapping("/helloWorld")
    public BaseResponse helloWorld() {
        BaseResponse response = BaseResponse.createSuccessResult("请求成功！");
        response.setData("我是返回的数据");
        return response;
    }


}
