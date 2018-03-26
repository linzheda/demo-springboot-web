package com.yc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 *
 * @author lzd
 * @date 2018/3/6
 *
 * @ServletComponentScan 用于过滤器的配置
 */
@SpringBootApplication
@ServletComponentScan
public class DemoSpringbootWebApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoSpringbootWebApplication.class, args);
	}
}
