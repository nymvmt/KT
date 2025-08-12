package com.example.demo.controller;

import com.example.demo.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    // 플레이어 목록 (실제로는 DB 사용)
    private List<Player> players = new ArrayList<>();
    private Long nextPlayerId = 1L;

    // 생성자에서 샘플 플레이어 생성
    public PlayerController() {
        // 샘플 플레이어들 생성
        createSamplePlayers();
    }

    private void createSamplePlayers() {
        Player warrior = new Player(nextPlayerId++, "용사킹", "전사");
        Player archer = new Player(nextPlayerId++, "궁수여신", "궁수");
        Player mage = new Player(nextPlayerId++, "마법왕자", "마법사");

        // 샘플 아이템들을 인벤토리에 추가
        addSampleItemsToPlayer(warrior);
        addSampleItemsToPlayer(archer);
        addSampleItemsToPlayer(mage);

        players.add(warrior);
        players.add(archer);
        players.add(mage);
    }

    private void addSampleItemsToPlayer(Player player) {
        // 기본 아이템들을 인벤토리에 추가
        Item sword = new Item(1L, "나무 검", 1000, "한손검", 12, 1, "초보 모험가를 위한 기본 검");
        Item potion = new Item(10L, "빨간 포션", 100, "소비아이템", 0, 1, "HP를 회복시키는 포션");
        
        player.getInventory().addItem(sword, 1);
        player.getInventory().addItem(potion, 5);
    }

    // GET - 모든 플레이어 조회
    @GetMapping
    public List<Player> getAllPlayers() {
        return players;
    }

    // GET - 특정 플레이어 조회
    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable Long id) {
        return players.stream()
                .filter(player -> player.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // POST - 새로운 플레이어 생성
    @PostMapping
    public Player createPlayer(@RequestBody Player player) {
        player.setId(nextPlayerId++);
        players.add(player);
        return player;
    }

    // PUT - 플레이어 정보 수정
    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable Long id, @RequestBody Player updatedPlayer) {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            if (player.getId().equals(id)) {
                updatedPlayer.setId(id);
                updatedPlayer.setInventory(player.getInventory()); // 인벤토리는 유지
                players.set(i, updatedPlayer);
                return updatedPlayer;
            }
        }
        return null;
    }

    // DELETE - 플레이어 삭제
    @DeleteMapping("/{id}")
    public boolean deletePlayer(@PathVariable Long id) {
        return players.removeIf(player -> player.getId().equals(id));
    }

    // GET - 플레이어 인벤토리 조회
    @GetMapping("/{id}/inventory")
    public Inventory getPlayerInventory(@PathVariable Long id) {
        Player player = getPlayer(id);
        return player != null ? player.getInventory() : null;
    }

    // POST - 플레이어 인벤토리에 아이템 추가
    @PostMapping("/{id}/inventory/items")
    public boolean addItemToInventory(@PathVariable Long id, 
                                   @RequestParam Long itemId, 
                                   @RequestParam int quantity) {
        Player player = getPlayer(id);
        if (player == null) return false;

        // 실제로는 상점에서 아이템을 가져와야 함
        Item item = new Item(itemId, "새로운 아이템", 0, "기타", 0, 1, "새로 추가된 아이템");
        return player.getInventory().addItem(item, quantity);
    }

    // DELETE - 플레이어 인벤토리에서 아이템 제거
    @DeleteMapping("/{id}/inventory/items/{itemId}")
    public boolean removeItemFromInventory(@PathVariable Long id, 
                                        @PathVariable Long itemId, 
                                        @RequestParam int quantity) {
        Player player = getPlayer(id);
        if (player == null) return false;

        return player.getInventory().removeItem(itemId, quantity);
    }

    // POST - 플레이어 경험치 획득
    @PostMapping("/{id}/exp")
    public Player gainExperience(@PathVariable Long id, @RequestParam int expAmount) {
        Player player = getPlayer(id);
        if (player == null) return null;

        player.gainExp(expAmount);
        return player;
    }

    // POST - 플레이어 메소 획득/사용
    @PostMapping("/{id}/meso")
    public Player updateMeso(@PathVariable Long id, @RequestParam int amount) {
        Player player = getPlayer(id);
        if (player == null) return null;

        if (amount > 0) {
            player.gainMeso(amount);
        } else {
            player.useMeso(-amount);
        }
        return player;
    }

    // GET - 플레이어 스탯 정보
    @GetMapping("/{id}/stats")
    public PlayerStats getPlayerStats(@PathVariable Long id) {
        Player player = getPlayer(id);
        if (player == null) return null;

        return new PlayerStats(player);
    }

    // POST - 플레이어 아이템 사용
    @PostMapping("/{id}/inventory/use/{itemId}")
    public boolean useItem(@PathVariable Long id, @PathVariable Long itemId) {
        Player player = getPlayer(id);
        if (player == null) return false;

        return player.getInventory().useItem(itemId);
    }

    // GET - 플레이어가 착용 가능한 아이템 목록
    @GetMapping("/{id}/equippable-items")
    public List<Item> getEquippableItems(@PathVariable Long id) {
        Player player = getPlayer(id);
        if (player == null) return new ArrayList<>();

        // 실제로는 상점 아이템들과 비교해야 함
        List<Item> equippableItems = new ArrayList<>();
        // 여기서 상점 아이템들을 가져와서 레벨 체크
        return equippableItems;
    }

    // PlayerStats 내부 클래스 (플레이어 스탯 정보 전용)
    public static class PlayerStats {
        private String name;
        private String job;
        private int level;
        private int exp;
        private int maxExp;
        private int hp;
        private int maxHp;
        private int mp;
        private int maxMp;
        private int strength;
        private int dexterity;
        private int intelligence;
        private int luck;
        private int meso;
        private int inventoryUsed;
        private int inventoryMax;

        public PlayerStats(Player player) {
            this.name = player.getName();
            this.job = player.getJob();
            this.level = player.getLevel();
            this.exp = player.getExp();
            this.maxExp = player.getMaxExp();
            this.hp = player.getHp();
            this.maxHp = player.getMaxHp();
            this.mp = player.getMp();
            this.maxMp = player.getMaxMp();
            this.strength = player.getStrength();
            this.dexterity = player.getDexterity();
            this.intelligence = player.getIntelligence();
            this.luck = player.getLuck();
            this.meso = player.getMeso();
            this.inventoryUsed = player.getInventory().getUsedSlotCount();
            this.inventoryMax = player.getInventory().getMaxSize();
        }

        // Getter methods
        public String getName() { return name; }
        public String getJob() { return job; }
        public int getLevel() { return level; }
        public int getExp() { return exp; }
        public int getMaxExp() { return maxExp; }
        public int getHp() { return hp; }
        public int getMaxHp() { return maxHp; }
        public int getMp() { return mp; }
        public int getMaxMp() { return maxMp; }
        public int getStrength() { return strength; }
        public int getDexterity() { return dexterity; }
        public int getIntelligence() { return intelligence; }
        public int getLuck() { return luck; }
        public int getMeso() { return meso; }
        public int getInventoryUsed() { return inventoryUsed; }
        public int getInventoryMax() { return inventoryMax; }
    }
}
