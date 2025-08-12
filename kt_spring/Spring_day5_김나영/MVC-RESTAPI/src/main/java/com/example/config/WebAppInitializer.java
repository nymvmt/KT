package com.example.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { DatabaseConfig.class }; // Root Context에 DatabaseConfig 설정
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class }; // 웹 설정 클래스
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" }; // 모든 요청 처리
    }

    /**
     * 🌟 한글 처리를 위한 인코딩 필터 설정 (매우 중요!)
     * Spring Boot가 아닌 일반 Spring MVC에서는 필수!
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true); // 요청과 응답 모두 UTF-8 강제 설정
        characterEncodingFilter.setForceRequestEncoding(true); // 요청 인코딩 강제 설정
        characterEncodingFilter.setForceResponseEncoding(true); // 응답 인코딩 강제 설정

        return new Filter[] { characterEncodingFilter };
    }
}