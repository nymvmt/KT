package com.example.demo.controller;

import com.example.demo.model.Enhancement;
import com.example.demo.model.Item;
import com.example.demo.model.Player;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/enhancement")
public class EnhancementController {

    // 강화 이력 저장 (실제로는 DB 사용)
    private List<Enhancement> enhancementHistory = new ArrayList<>();
    private Long nextEnhancementId = 1L;

    // 강화 가능한 아이템 목록 조회
    @GetMapping("/items/{playerId}")
    public List<Item> getEnhanceableItems(@PathVariable Long playerId) {
        // 실제로는 PlayerController에서 플레이어 정보를 가져와야 함
        // 여기서는 샘플 아이템들을 반환
        List<Item> items = new ArrayList<>();
        
        Item sword = new Item(1L, "강화된 검", 5000, "한손검", 25, 1, "강화된 강력한 검");
        sword.setEnhancementLevel(3);
        items.add(sword);
        
        Item bow = new Item(2L, "마법 활", 8000, "활", 30, 5, "마법이 깃든 활");
        bow.setEnhancementLevel(5);
        items.add(bow);
        
        Item staff = new Item(3L, "지혜의 지팡이", 12000, "지팡이", 35, 10, "지혜가 깃든 지팡이");
        staff.setEnhancementLevel(7);
        items.add(staff);
        
        return items;
    }

    // 강화 정보 조회
    @GetMapping("/info/{itemId}")
    public EnhancementInfo getEnhancementInfo(@PathVariable Long itemId, @RequestParam Long playerId) {
        // 실제로는 DB에서 아이템 정보를 가져와야 함
        Item item = new Item(itemId, "샘플 아이템", 1000, "한손검", 20, 1, "샘플 설명");
        item.setEnhancementLevel(5);
        
        EnhancementInfo info = new EnhancementInfo();
        info.setItemId(itemId);
        info.setCurrentLevel(item.getEnhancementLevel());
        info.setMaxLevel(item.getMaxEnhancementLevel());
        info.setCanEnhance(item.canEnhance());
        info.setCost(item.getEnhancementCost());
        info.setSuccessRate(calculateSuccessRate(item.getEnhancementLevel(), item.getEnhancementLevel() + 1));
        info.setNextLevelAttackPower(item.getAttackPower() + 10);
        
        return info;
    }

    // 강화 실행
    @PostMapping("/execute")
    public EnhancementResult executeEnhancement(@RequestBody EnhancementRequest request) {
        // 실제로는 DB에서 아이템과 플레이어 정보를 가져와야 함
        Item item = new Item(request.getItemId(), "샘플 아이템", 1000, "한손검", 20, 1, "샘플 설명");
        item.setEnhancementLevel(request.getCurrentLevel());
        
        Player player = new Player(request.getPlayerId(), "샘플 플레이어", "전사");
        player.setMeso(10000);
        
        // 강화 비용 확인
        int cost = calculateEnhancementCost(request.getCurrentLevel(), request.getTargetLevel());
        if (player.getMeso() < cost) {
            return new EnhancementResult(false, "메소가 부족합니다.", null, 0, 0);
        }
        
        // 강화 실행
        Enhancement enhancement = new Enhancement(
            request.getItemId(), 
            request.getPlayerId(), 
            request.getCurrentLevel(), 
            request.getTargetLevel()
        );
        
        enhancement.execute();
        
        // 강화 결과에 따른 아이템 상태 변경
        switch (enhancement.getResult()) {
            case SUCCESS:
                item.enhance();
                break;
            case DOWNGRADE:
                item.downgrade();
                break;
            case DESTROY:
                item.destroy();
                break;
            case FAILURE:
                // 실패 시에는 상태 변화 없음
                break;
        }
        
        // 메소 차감
        player.useMeso(cost);
        
        // 강화 이력 저장
        enhancement.setId(nextEnhancementId++);
        enhancementHistory.add(enhancement);
        
        // 결과 반환
        EnhancementResult result = new EnhancementResult();
        result.setSuccess(true);
        result.setMessage(enhancement.getMessage());
        result.setItem(item);
        result.setCost(cost);
        result.setNewAttackPower(item.getAttackPower());
        
        return result;
    }

    // 강화 이력 조회
    @GetMapping("/history/{playerId}")
    public List<Enhancement> getEnhancementHistory(@PathVariable Long playerId) {
        return enhancementHistory.stream()
                .filter(e -> e.getPlayerId().equals(playerId))
                .toList();
    }

    // 강화 통계 조회
    @GetMapping("/stats/{playerId}")
    public EnhancementStats getEnhancementStats(@PathVariable Long playerId) {
        List<Enhancement> playerHistory = enhancementHistory.stream()
                .filter(e -> e.getPlayerId().equals(playerId))
                .toList();
        
        EnhancementStats stats = new EnhancementStats();
        stats.setTotalAttempts(playerHistory.size());
        stats.setSuccessCount((int) playerHistory.stream()
                .filter(e -> e.getResult() == Enhancement.EnhancementResult.SUCCESS)
                .count());
        stats.setFailureCount((int) playerHistory.stream()
                .filter(e -> e.getResult() == Enhancement.EnhancementResult.FAILURE)
                .count());
        stats.setDestroyCount((int) playerHistory.stream()
                .filter(e -> e.getResult() == Enhancement.EnhancementResult.DESTROY)
                .count());
        stats.setDowngradeCount((int) playerHistory.stream()
                .filter(e -> e.getResult() == Enhancement.EnhancementResult.DOWNGRADE)
                .count());
        
        if (stats.getTotalAttempts() > 0) {
            stats.setSuccessRate((double) stats.getSuccessCount() / stats.getTotalAttempts() * 100);
        }
        
        return stats;
    }

    // 강화 비용 계산
    private int calculateEnhancementCost(int currentLevel, int targetLevel) {
        int baseCost = 1000;
        int levelMultiplier = targetLevel - currentLevel;
        return baseCost * (int) Math.pow(2, levelMultiplier);
    }

    // 성공률 계산
    private int calculateSuccessRate(int currentLevel, int targetLevel) {
        int levelDifference = targetLevel - currentLevel;
        
        if (levelDifference <= 0) return 100;
        if (levelDifference == 1) return 90;
        if (levelDifference == 2) return 70;
        if (levelDifference == 3) return 50;
        if (levelDifference == 4) return 30;
        if (levelDifference == 5) return 15;
        if (levelDifference == 6) return 8;
        if (levelDifference == 7) return 5;
        if (levelDifference == 8) return 3;
        if (levelDifference == 9) return 1;
        
        return 1;
    }

    // 내부 클래스들
    public static class EnhancementInfo {
        private Long itemId;
        private int currentLevel;
        private int maxLevel;
        private boolean canEnhance;
        private int cost;
        private int successRate;
        private int nextLevelAttackPower;

        // Getter & Setter
        public Long getItemId() { return itemId; }
        public void setItemId(Long itemId) { this.itemId = itemId; }

        public int getCurrentLevel() { return currentLevel; }
        public void setCurrentLevel(int currentLevel) { this.currentLevel = currentLevel; }

        public int getMaxLevel() { return maxLevel; }
        public void setMaxLevel(int maxLevel) { this.maxLevel = maxLevel; }

        public boolean isCanEnhance() { return canEnhance; }
        public void setCanEnhance(boolean canEnhance) { this.canEnhance = canEnhance; }

        public int getCost() { return cost; }
        public void setCost(int cost) { this.cost = cost; }

        public int getSuccessRate() { return successRate; }
        public void setSuccessRate(int successRate) { this.successRate = successRate; }

        public int getNextLevelAttackPower() { return nextLevelAttackPower; }
        public void setNextLevelAttackPower(int nextLevelAttackPower) { this.nextLevelAttackPower = nextLevelAttackPower; }
    }

    public static class EnhancementRequest {
        private Long itemId;
        private Long playerId;
        private int currentLevel;
        private int targetLevel;

        // Getter & Setter
        public Long getItemId() { return itemId; }
        public void setItemId(Long itemId) { this.itemId = itemId; }

        public Long getPlayerId() { return playerId; }
        public void setPlayerId(Long playerId) { this.playerId = playerId; }

        public int getCurrentLevel() { return currentLevel; }
        public void setCurrentLevel(int currentLevel) { this.currentLevel = currentLevel; }

        public int getTargetLevel() { return targetLevel; }
        public void setTargetLevel(int targetLevel) { this.targetLevel = targetLevel; }
    }

    public static class EnhancementResult {
        private boolean success;
        private String message;
        private Item item;
        private int cost;
        private int newAttackPower;

        public EnhancementResult() {}

        public EnhancementResult(boolean success, String message, Item item, int cost, int newAttackPower) {
            this.success = success;
            this.message = message;
            this.item = item;
            this.cost = cost;
            this.newAttackPower = newAttackPower;
        }

        // Getter & Setter
        public boolean isSuccess() { return success; }
        public void setSuccess(boolean success) { this.success = success; }

        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }

        public Item getItem() { return item; }
        public void setItem(Item item) { this.item = item; }

        public int getCost() { return cost; }
        public void setCost(int cost) { this.cost = cost; }

        public int getNewAttackPower() { return newAttackPower; }
        public void setNewAttackPower(int newAttackPower) { this.newAttackPower = newAttackPower; }
    }

    public static class EnhancementStats {
        private int totalAttempts;
        private int successCount;
        private int failureCount;
        private int destroyCount;
        private int downgradeCount;
        private double successRate;

        // Getter & Setter
        public int getTotalAttempts() { return totalAttempts; }
        public void setTotalAttempts(int totalAttempts) { this.totalAttempts = totalAttempts; }

        public int getSuccessCount() { return successCount; }
        public void setSuccessCount(int successCount) { this.successCount = successCount; }

        public int getFailureCount() { return failureCount; }
        public void setFailureCount(int failureCount) { this.failureCount = failureCount; }

        public int getDestroyCount() { return destroyCount; }
        public void setDestroyCount(int destroyCount) { this.destroyCount = destroyCount; }

        public int getDowngradeCount() { return downgradeCount; }
        public void setDowngradeCount(int downgradeCount) { this.downgradeCount = downgradeCount; }

        public double getSuccessRate() { return successRate; }
        public void setSuccessRate(double successRate) { this.successRate = successRate; }
    }
}
