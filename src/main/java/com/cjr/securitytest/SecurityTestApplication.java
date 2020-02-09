package com.cjr.securitytest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//可以参考 https://gitee.com/hong_ej/SpringSecurity/blob/master/pom.xml
@SpringBootApplication
@MapperScan("com.cjr.securitytest.web.security.dal.dao")		// 指定mapper.java 的位置
public class SecurityTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityTestApplication.class, args);
	}

}
