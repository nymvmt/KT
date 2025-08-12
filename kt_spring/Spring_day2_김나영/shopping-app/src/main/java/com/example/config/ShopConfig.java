package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  // Spring 설정 클래스임을 명시
@ComponentScan(basePackages = "com.example.shop")  // 자동 컴포넌트 스캔
public class ShopConfig {
    // @Controller, @Service, @Repository 어노테이션이 붙은 클래스들을
    // 자동으로 스캔해서 Spring Bean으로 등록
    // @Autowired가 자동으로 의존성 주입 처리
}
