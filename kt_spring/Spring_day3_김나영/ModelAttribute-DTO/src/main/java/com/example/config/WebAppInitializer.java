package com.example.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * WebAppInitializer - web.xml을 대체하는 Java Config
 * WebApplicationInitializer 인터페이스를 구현하여 서블릿 컨테이너 초기화
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        
        // Spring Web Application Context 생성
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        
        // WebConfig 클래스를 설정 클래스로 등록
        context.register(WebConfig.class);
        
        // DispatcherServlet 생성 및 설정
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        
        // DispatcherServlet을 서블릿 컨테이너에 등록
        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", dispatcherServlet);
        
        // 서블릿 매핑 설정 - 모든 요청을 DispatcherServlet이 처리
        registration.addMapping("/");
        
        // 서블릿 로드 순서 설정 (1 = 첫 번째로 로드)
        registration.setLoadOnStartup(1);
    }
} 