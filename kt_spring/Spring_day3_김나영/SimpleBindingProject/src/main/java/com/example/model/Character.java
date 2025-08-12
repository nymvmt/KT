package com.example.model;

import java.util.List;

/**
 * 🎮 게임 캐릭터 클래스 - 바인딩 테스트용
 */
public class Character {

    private String name;        // 캐릭터 이름
    private String job;         // 직업
    private int level;          // 레벨
    private int health;         // 체력
    private int mana;           // 마나
    private boolean premium;    // 프리미엄 계정 여부
    private String gender;      // 성별
    private List<String> skills; // 스킬 목록

    // 기본 생성자 (Spring 필수!)
    public Character() {
        System.out.println("🎮 Character 객체 생성됨!");
    }

    // Getter & Setter 메서드들
    public String getName() { return name; }
    public void setName(String name) {
        System.out.println("👤 캐릭터명 설정: " + name);
        this.name = name;
    }

    public String getJob() { return job; }
    public void setJob(String job) {
        System.out.println("⚔️ 직업 설정: " + job);
        this.job = job;
    }

    public int getLevel() { return level; }
    public void setLevel(int level) {
        System.out.println("📊 레벨 설정: " + level);
        this.level = level;
    }

    public int getHealth() { return health; }
    public void setHealth(int health) {
        System.out.println("❤️ 체력 설정: " + health);
        this.health = health;
    }

    public int getMana() { return mana; }
    public void setMana(int mana) {
        System.out.println("💙 마나 설정: " + mana);
        this.mana = mana;
    }

    public boolean isPremium() { return premium; }
    public void setPremium(boolean premium) {
        System.out.println("💎 프리미엄 설정: " + premium);
        this.premium = premium;
    }

    public String getGender() { return gender; }
    public void setGender(String gender) {
        System.out.println("👤 성별 설정: " + gender);
        this.gender = gender;
    }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) {
        System.out.println("🎯 스킬 설정: " + skills);
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Character{name='" + name + "', job='" + job + 
               "', level=" + level + ", health=" + health + 
               ", mana=" + mana + ", premium=" + premium + 
               ", gender='" + gender + "', skills=" + skills + "}";
    }
}