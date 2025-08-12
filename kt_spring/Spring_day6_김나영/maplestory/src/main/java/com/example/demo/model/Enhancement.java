package com.example.demo.model;

import java.time.LocalDateTime;

public class Enhancement {
    private Long id;
    private Long itemId;
    private Long playerId;
    private int currentLevel;
    private int targetLevel;
    private int successRate;
    private int cost;
    private EnhancementResult result;
    private LocalDateTime timestamp;
    private String message;

    public enum EnhancementResult {
        SUCCESS("성공"),
        FAILURE("실패"),
        DESTROY("파괴"),
        DOWNGRADE("강화 레벨 하락");

        private final String description;

        EnhancementResult(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    // 기본 생성자
    public Enhancement() {
        this.timestamp = LocalDateTime.now();
    }

    // 강화 생성자
    public Enhancement(Long itemId, Long playerId, int currentLevel, int targetLevel) {
        this();
        this.itemId = itemId;
        this.playerId = playerId;
        this.currentLevel = currentLevel;
        this.targetLevel = targetLevel;
        this.cost = calculateCost(currentLevel, targetLevel);
        this.successRate = calculateSuccessRate(currentLevel, targetLevel);
    }

    // 강화 비용 계산
    private int calculateCost(int currentLevel, int targetLevel) {
        int baseCost = 1000; // 기본 비용
        int levelMultiplier = targetLevel - currentLevel;
        return baseCost * (int) Math.pow(2, levelMultiplier);
    }

    // 성공률 계산
    private int calculateSuccessRate(int currentLevel, int targetLevel) {
        int levelDifference = targetLevel - currentLevel;
        if (targetLevel - currentLevel <= 0) return 100;
        if (targetLevel - currentLevel == 1) return 90;
        if (targetLevel - currentLevel == 2) return 70;
        if (targetLevel - currentLevel == 3) return 50;
        if (targetLevel - currentLevel == 4) return 30;
        if (targetLevel - currentLevel == 5) return 15;
        if (targetLevel - currentLevel == 6) return 8;
        if (targetLevel - currentLevel == 7) return 5;
        if (targetLevel - currentLevel == 8) return 3;
        if (targetLevel - currentLevel == 9) return 1;
        
        return 1; // 10레벨 이상 차이는 1%
    }

    // 강화 실행
    public void execute() {
        int random = (int) (Math.random() * 100) + 1;
        
        if (random <= successRate) {
            // 성공
            if (random <= successRate * 0.1) { // 10% 확률로 대성공
                this.result = EnhancementResult.SUCCESS;
                this.message = "대성공! 아이템이 강화되었습니다!";
            } else {
                this.result = EnhancementResult.SUCCESS;
                this.message = "강화 성공! 아이템이 강화되었습니다.";
            }
        } else {
            // 실패
            if (random > 95) { // 5% 확률로 파괴
                this.result = EnhancementResult.DESTROY;
                this.message = "아이템이 파괴되었습니다...";
            } else if (random > 85) { // 10% 확률로 레벨 하락
                this.result = EnhancementResult.DOWNGRADE;
                this.message = "강화 실패! 아이템 레벨이 하락했습니다.";
            } else {
                this.result = EnhancementResult.FAILURE;
                this.message = "강화 실패! 다음에 다시 시도해보세요.";
            }
        }
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getItemId() { return itemId; }
    public void setItemId(Long itemId) { this.itemId = itemId; }

    public Long getPlayerId() { return playerId; }
    public void setPlayerId(Long playerId) { this.playerId = playerId; }

    public int getCurrentLevel() { return currentLevel; }
    public void setCurrentLevel(int currentLevel) { this.currentLevel = currentLevel; }

    public int getTargetLevel() { return targetLevel; }
    public void setTargetLevel(int targetLevel) { this.targetLevel = targetLevel; }

    public int getSuccessRate() { return successRate; }
    public void setSuccessRate(int successRate) { this.successRate = successRate; }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }

    public EnhancementResult getResult() { return result; }
    public void setResult(EnhancementResult result) { this.result = result; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    @Override
    public String toString() {
        return "Enhancement{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", playerId=" + playerId +
                ", currentLevel=" + currentLevel +
                ", targetLevel=" + targetLevel +
                ", successRate=" + successRate +
                ", cost=" + cost +
                ", result=" + result +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }
}
