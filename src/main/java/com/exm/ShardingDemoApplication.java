package com.exm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.exm")
@MapperScan("com.exm.business.mapper")
public class ShardingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingDemoApplication.class, args);
		System.out.println("=====================================");
		System.out.println("===== 系统启动成功 =====");
	}

}
