// // Warrior.java (전사)
// package com.example.game;

// public class Warrior {
//     private String name = "용감한 전사";
//     private int health = 100;
//     private int attack = 20;

//     public void introduce() {
//         System.out.println("⚔️ " + name + "입니다! (체력:" + health + ", 공격력:" + attack + ")");
//     }

//     public void attack() {
//         System.out.println("⚔️ 검으로 강력하게 공격합니다! 데미지: " + attack);
//     }

//     public void defendSkill() {
//         System.out.println("🛡️ 방패로 막습니다! 데미지를 50% 감소시킵니다!");
//     }
// }

// Warrior.java (전사 - 무기 의존성 있음)
package com.example.game;

public class Warrior {
    private Weapon weapon;
    private String name;

    // 생성자로 무기를 주입받음
    public Warrior(Weapon weapon) {
        this.weapon = weapon;
        this.name = "용감한 전사";
        System.out.println("🔧 " + name + "가 " + weapon.getName() + "을(를) 장착했습니다!");
    }

    public void introduce() {
        System.out.println("⚔️ " + name + "입니다! 장비: " + weapon.getName());
    }

    public void fight() {
        System.out.println("💨 " + name + "의 공격!");
        weapon.attack();
    }
}

