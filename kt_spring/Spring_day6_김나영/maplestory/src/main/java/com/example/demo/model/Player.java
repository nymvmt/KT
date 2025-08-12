package com.example.demo.model;

public class Player {
    private Long id;
    private String name;           // 캐릭터 이름
    private String job;            // 직업 (전사, 궁수, 마법사 등)
    private int level;             // 레벨
    private int exp;               // 경험치
    private int maxExp;            // 다음 레벨까지 필요한 경험치
    private int hp;                // 체력
    private int maxHp;             // 최대 체력
    private int mp;                // 마나
    private int maxMp;             // 최대 마나
    private int strength;          // 힘
    private int dexterity;         // 민첩
    private int intelligence;      // 지능
    private int luck;              // 행운
    private int meso;              // 메소 (게임 내 화폐)
    private Inventory inventory;   // 인벤토리

    // 기본 생성자
    public Player() {
        this.inventory = new Inventory();
        this.level = 1;
        this.exp = 0;
        this.maxExp = 100;
        this.hp = 50;
        this.maxHp = 50;
        this.mp = 30;
        this.maxMp = 30;
        this.strength = 10;
        this.dexterity = 10;
        this.intelligence = 10;
        this.luck = 10;
        this.meso = 1000;
    }

    // 모든 필드 생성자
    public Player(Long id, String name, String job) {
        this();
        this.id = id;
        this.name = name;
        this.job = job;
        initializeJobStats();
    }

    // 직업별 초기 스탯 설정
    private void initializeJobStats() {
        switch (job.toLowerCase()) {
            case "전사":
                this.strength = 15;
                this.dexterity = 8;
                this.intelligence = 5;
                this.luck = 7;
                this.hp = 80;
                this.maxHp = 80;
                this.mp = 20;
                this.maxMp = 20;
                break;
            case "궁수":
                this.strength = 8;
                this.dexterity = 15;
                this.intelligence = 5;
                this.luck = 12;
                this.hp = 60;
                this.maxHp = 60;
                this.mp = 25;
                this.maxMp = 25;
                break;
            case "마법사":
                this.strength = 5;
                this.dexterity = 8;
                this.intelligence = 15;
                this.luck = 10;
                this.hp = 40;
                this.maxHp = 40;
                this.mp = 60;
                this.maxMp = 60;
                break;
            default:
                break;
        }
    }

    // 레벨업 메서드
    public boolean levelUp() {
        if (exp >= maxExp) {
            level++;
            exp -= maxExp;
            maxExp = level * 100; // 레벨에 따라 필요한 경험치 증가
            
            // 스탯 증가
            strength += 2;
            dexterity += 2;
            intelligence += 2;
            luck += 1;
            
            // HP, MP 증가
            maxHp += 10;
            maxMp += 5;
            hp = maxHp; // 레벨업 시 체력 회복
            mp = maxMp; // 레벨업 시 마나 회복
            
            return true;
        }
        return false;
    }

    // 경험치 획득
    public void gainExp(int expAmount) {
        this.exp += expAmount;
        while (levelUp()) {
            // 레벨업이 가능한 동안 계속 레벨업
        }
    }

    // 아이템 착용 가능 여부 확인
    public boolean canEquipItem(Item item) {
        return level >= item.getRequiredLevel();
    }

    // 메소 사용
    public boolean useMeso(int amount) {
        if (meso >= amount) {
            meso -= amount;
            return true;
        }
        return false;
    }

    // 메소 획득
    public void gainMeso(int amount) {
        meso += amount;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }

    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }

    public int getExp() { return exp; }
    public void setExp(int exp) { this.exp = exp; }

    public int getMaxExp() { return maxExp; }
    public void setMaxExp(int maxExp) { this.maxExp = maxExp; }

    public int getHp() { return hp; }
    public void setHp(int hp) { this.hp = hp; }

    public int getMaxHp() { return maxHp; }
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; }

    public int getMp() { return mp; }
    public void setMp(int mp) { this.mp = mp; }

    public int getMaxMp() { return maxMp; }
    public void setMaxMp(int maxMp) { this.maxMp = maxMp; }

    public int getStrength() { return strength; }
    public void setStrength(int strength) { this.strength = strength; }

    public int getDexterity() { return dexterity; }
    public void setDexterity(int dexterity) { this.dexterity = dexterity; }

    public int getIntelligence() { return intelligence; }
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }

    public int getLuck() { return luck; }
    public void setLuck(int luck) { this.luck = luck; }

    public int getMeso() { return meso; }
    public void setMeso(int meso) { this.meso = meso; }

    public Inventory getInventory() { return inventory; }
    public void setInventory(Inventory inventory) { this.inventory = inventory; }
}
