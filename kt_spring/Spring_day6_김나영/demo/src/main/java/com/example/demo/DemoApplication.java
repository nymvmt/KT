package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //스프링 부트에서
public class DemoApplication {

	public static void main(String[] args) { //메인으로 시작되는 부분
		SpringApplication.run(DemoApplication.class, args);
	}

}
