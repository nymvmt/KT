// ShopApp.java
package com.example;

import com.example.config.ShopConfig;
import com.example.shop.OrderController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ShopApp {
    public static void main(String[] args) {
        // Spring ApplicationContext를 사용한 Bean 관리
        ApplicationContext context = new AnnotationConfigApplicationContext(ShopConfig.class);
        OrderController controller = context.getBean(OrderController.class);

        System.out.println("=== 온라인 쇼핑몰 주문 시스템 ===");
        controller.handleOrderRequest("노트북", 2);
    }
}
