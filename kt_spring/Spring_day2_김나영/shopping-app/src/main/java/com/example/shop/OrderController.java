// OrderController.java
package com.example.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
  @Autowired
    private OrderService orderService;
    
    // 의존성 주입을 위한 setter 메서드
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void handleOrderRequest(String productName, int quantity) {
        System.out.println("🌐 웹 요청 받음: " + productName + " 주문");

        // 입력값 검증
        if (quantity <= 0) {
            System.out.println("❌ 잘못된 수량입니다!");
            return;
        }

        // 서비스에 처리 위임
        orderService.processOrder(productName, quantity);

        System.out.println("📱 고객에게 응답 전송 완료!");
    }
}
