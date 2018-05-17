package com.wang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wang.aishenhuo.pc.api.myBatis.mapper")
public class AiShenHuoController {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(AiShenHuoController.class, args);
	}
}