package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

/**
 * WebConfig - Spring MVC 설정 클래스
 * @EnableWebMvc와 WebMvcConfigurer를 통해 Spring MVC 기능 활성화
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example")
public class WebConfig implements WebMvcConfigurer {

    /**
     * Thymeleaf 템플릿 해결자 설정
     * HTML 템플릿 파일의 위치와 확장자 설정
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        
        // 템플릿 파일 위치 설정
        templateResolver.setPrefix("/WEB-INF/templates/");
        
        // 템플릿 파일 확장자 설정
        templateResolver.setSuffix(".html");
        
        // 템플릿 모드 설정 (HTML5)
        templateResolver.setTemplateMode("HTML");
        
        // 문자 인코딩 설정
        templateResolver.setCharacterEncoding("UTF-8");
        
        // 캐시 설정 (개발 중에는 false로 설정하여 실시간 반영)
        templateResolver.setCacheable(false);
        
        return templateResolver;
    }

    /**
     * Thymeleaf 템플릿 엔진 설정
     * 템플릿 해결자와 연동
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        
        // 템플릿 해결자 연결
        templateEngine.setTemplateResolver(templateResolver());
        
        // Spring의 메시지 소스 활성화
        templateEngine.setEnableSpringELCompiler(true);
        
        return templateEngine;
    }

    /**
     * Thymeleaf 뷰 해결자 설정
     * 컨트롤러에서 반환한 뷰 이름을 실제 템플릿과 연결
     */
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        
        // 템플릿 엔진 연결
        viewResolver.setTemplateEngine(templateEngine());
        
        // 문자 인코딩 설정
        viewResolver.setCharacterEncoding("UTF-8");
        
        // 뷰 해결자 우선순위 설정
        viewResolver.setOrder(1);
        
        return viewResolver;
    }
} 