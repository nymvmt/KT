package com.example.service;

import org.springframework.stereotype.Service;

@Service  // 🏪 "나는 비즈니스 로직을 처리하는 Service야!"
public class MessageService {

    // 환영 메시지 생성
    public String getWelcomeMessage(String name) {
        return String.format("안녕하세요, %s님! Spring MVC에 오신 것을 환영합니다! 🎉", name);
    }

    // 현재 시간 메시지
    public String getCurrentTimeMessage() {
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        return "현재 시간: " + now.toString();
    }

    public String getTodayMessage() {
        // 오늘이 무슨 요일인지 알려주는 메시지 만들기
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        java.time.DayOfWeek dayOfWeek = now.getDayOfWeek();
        
        String dayInKorean;
        switch (dayOfWeek) {
            case MONDAY:
                dayInKorean = "월요일";
                break;
            case TUESDAY:
                dayInKorean = "화요일";
                break;
            case WEDNESDAY:
                dayInKorean = "수요일";
                break;
            case THURSDAY:
                dayInKorean = "목요일";
                break;
            case FRIDAY:
                dayInKorean = "금요일";
                break;
            case SATURDAY:
                dayInKorean = "토요일";
                break;
            case SUNDAY:
                dayInKorean = "일요일";
                break;
            default:
                dayInKorean = "알 수 없는 요일";
                break;
        }
        
        return String.format("오늘은 %s입니다! 멋진 하루 보내세요! 🌟", dayInKorean);
    }

    // 랜덤 메시지
    public String getRandomMessage() {
        String[] messages = {
            "오늘도 열심히 코딩해요! 💻",
            "Spring은 정말 재미있어요! 🌱",
            "MVC 패턴을 마스터해봐요! 🚀",
            "개발자의 길은 험하지만 보람있어요! ⭐"
        };

        int randomIndex = (int)(Math.random() * messages.length);
        return messages[randomIndex];
    }
}
