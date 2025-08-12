package com.example.demo.model;

public class Item {
    private Long id;
    private String name;        // 아이템 이름
    private int price;          // 메소 가격
    private String itemType;    // 무기 종류 (검, 활, 지팡이 등)
    private int attackPower;    // 공격력
    private int requiredLevel;  // 착용 레벨 제한
    private String description; // 아이템 설명
    
    // 강화 시스템 관련 속성들
    private int enhancementLevel;    // 현재 강화 레벨
    private int maxEnhancementLevel; // 최대 강화 가능 레벨
    private boolean isDestroyed;     // 파괴 여부
    private int enhancementCost;     // 강화 비용

    // 기본 생성자
    public Item() {
        this.enhancementLevel = 0;
        this.maxEnhancementLevel = 20;
        this.isDestroyed = false;
        this.enhancementCost = 1000;
    }

    // 모든 필드 생성자
    public Item(Long id, String name, int price, String itemType,
                int attackPower, int requiredLevel, String description) {
        this();
        this.id = id;
        this.name = name;
        this.price = price;
        this.itemType = itemType;
        this.attackPower = attackPower;
        this.requiredLevel = requiredLevel;
        this.description = description;
    }

    // 강화된 아이템 생성자
    public Item(Long id, String name, int price, String itemType,
                int attackPower, int requiredLevel, String description,
                int enhancementLevel, int maxEnhancementLevel) {
        this(id, name, price, itemType, attackPower, requiredLevel, description);
        this.enhancementLevel = enhancementLevel;
        this.maxEnhancementLevel = maxEnhancementLevel;
        this.enhancementCost = calculateEnhancementCost();
    }

    // 강화 비용 계산
    private int calculateEnhancementCost() {
        return 1000 * (int) Math.pow(2, enhancementLevel);
    }

    // 강화 가능 여부 확인
    public boolean canEnhance() {
        return !isDestroyed && enhancementLevel < maxEnhancementLevel;
    }

    // 강화 실행
    public boolean enhance() {
        if (!canEnhance()) {
            return false;
        }
        
        enhancementLevel++;
        attackPower = calculateEnhancedAttackPower();
        enhancementCost = calculateEnhancementCost();
        return true;
    }

    // 강화된 공격력 계산
    private int calculateEnhancedAttackPower() {
        int baseAttack = attackPower - (enhancementLevel - 1) * 5; // 기본 공격력 복원
        return baseAttack + (enhancementLevel * 10); // 강화 레벨당 +10
    }

    // 강화 실패 시 레벨 하락
    public void downgrade() {
        if (enhancementLevel > 0) {
            enhancementLevel--;
            attackPower = calculateEnhancedAttackPower();
            enhancementCost = calculateEnhancementCost();
        }
    }

    // 아이템 파괴
    public void destroy() {
        this.isDestroyed = true;
        this.enhancementLevel = 0;
        this.attackPower = 0;
    }

    // 아이템 복구 (파괴된 아이템을 기본 상태로)
    public void repair() {
        if (isDestroyed) {
            this.isDestroyed = false;
            this.enhancementLevel = 0;
            this.attackPower = getBaseAttackPower();
            this.enhancementCost = 1000;
        }
    }

    // 기본 공격력 반환 (강화 효과 제외)
    private int getBaseAttackPower() {
        // 아이템 타입별 기본 공격력
        switch (itemType) {
            case "한손검": return 12;
            case "활": return 15;
            case "지팡이": return 18;
            default: return 10;
        }
    }

    // 강화된 아이템 이름 반환
    public String getEnhancedName() {
        if (enhancementLevel == 0) {
            return name;
        }
        return name + " (+" + enhancementLevel + ")";
    }

    // 강화 상태 정보 반환
    public String getEnhancementStatus() {
        if (isDestroyed) {
            return "파괴됨";
        }
        if (enhancementLevel == 0) {
            return "기본";
        }
        return "+" + enhancementLevel;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getItemType() { return itemType; }
    public void setItemType(String itemType) { this.itemType = itemType; }

    public int getAttackPower() { return attackPower; }
    public void setAttackPower(int attackPower) { this.attackPower = attackPower; }

    public int getRequiredLevel() { return requiredLevel; }
    public void setRequiredLevel(int requiredLevel) { this.requiredLevel = requiredLevel; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getEnhancementLevel() { return enhancementLevel; }
    public void setEnhancementLevel(int enhancementLevel) { this.enhancementLevel = enhancementLevel; }

    public int getMaxEnhancementLevel() { return maxEnhancementLevel; }
    public void setMaxEnhancementLevel(int maxEnhancementLevel) { this.maxEnhancementLevel = maxEnhancementLevel; }

    public boolean isDestroyed() { return isDestroyed; }
    public void setDestroyed(boolean destroyed) { isDestroyed = destroyed; }

    public int getEnhancementCost() { return enhancementCost; }
    public void setEnhancementCost(int enhancementCost) { this.enhancementCost = enhancementCost; }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", itemType='" + itemType + '\'' +
                ", attackPower=" + attackPower +
                ", requiredLevel=" + requiredLevel +
                ", description='" + description + '\'' +
                ", enhancementLevel=" + enhancementLevel +
                ", maxEnhancementLevel=" + maxEnhancementLevel +
                ", isDestroyed=" + isDestroyed +
                ", enhancementCost=" + enhancementCost +
                '}';
    }
}
