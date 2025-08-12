package com.example.product.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // 루트 애플리케이션 컨텍스트 설정 클래스
        // 서비스, 레포지토리 등의 비즈니스 로직 관련 빈들을 등록
        return null; // 현재는 모든 설정을 WebConfig에서 처리
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // 웹 관련 설정 클래스
        // 컨트롤러, 뷰 리졸버 등의 웹 관련 빈들을 등록
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        // DispatcherServlet이 처리할 URL 패턴
        return new String[] { "/" };
    }

    @Override
    protected String getServletName() {
        return "dispatcher";
    }

    @Override
    protected Filter[] getServletFilters() {
        // UTF-8 인코딩 필터 추가 - 한글이 안깨지게 하기 위함
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setForceRequestEncoding(true);
        characterEncodingFilter.setForceResponseEncoding(true);
        
        // HTTP 메서드 오버라이드 필터 추가 - DELETE, PUT 등 지원
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        
        return new Filter[] { characterEncodingFilter, hiddenHttpMethodFilter };
    }
}
