package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventory {
    private static final int DEFAULT_INVENTORY_SIZE = 24; // 기본 인벤토리 크기
    private List<InventorySlot> slots;
    private int maxSize;
    
    // 기본 생성자
    public Inventory() {
        this(DEFAULT_INVENTORY_SIZE);
    }
    
    // 크기를 지정한 생성자
    public Inventory(int maxSize) {
        this.maxSize = maxSize;
        this.slots = new ArrayList<>();
        
        // 슬롯 초기화
        for (int i = 0; i < maxSize; i++) {
            slots.add(new InventorySlot(i + 1));
        }
    }
    
    // 아이템 추가 (구매 시)
    public boolean addItem(Item item, int quantity) {
        if (item == null || quantity <= 0) {
            return false;
        }
        
        // 같은 아이템이 있는 슬롯 찾기
        Optional<InventorySlot> existingSlot = slots.stream()
                .filter(slot -> !slot.isEmpty() && slot.getItem().getId().equals(item.getId()))
                .findFirst();
        
        if (existingSlot.isPresent()) {
            // 기존 슬롯에 수량 추가
            return existingSlot.get().addQuantity(quantity);
        } else {
            // 빈 슬롯 찾기
            Optional<InventorySlot> emptySlot = slots.stream()
                    .filter(InventorySlot::isEmpty)
                    .findFirst();
            
            if (emptySlot.isPresent()) {
                // 빈 슬롯에 아이템 추가
                InventorySlot slot = emptySlot.get();
                slot.setItem(item);
                slot.setQuantity(quantity);
                return true;
            }
        }
        
        return false; // 인벤토리가 가득 참
    }
    
    // 아이템 제거 (판매 시)
    public boolean removeItem(Long itemId, int quantity) {
        if (itemId == null || quantity <= 0) {
            return false;
        }
        
        Optional<InventorySlot> slot = slots.stream()
                .filter(s -> !s.isEmpty() && s.getItem().getId().equals(itemId))
                .findFirst();
        
        if (slot.isPresent()) {
            return slot.get().removeQuantity(quantity);
        }
        
        return false;
    }
    
    // 특정 아이템 찾기
    public Optional<InventorySlot> findItem(Long itemId) {
        return slots.stream()
                .filter(slot -> !slot.isEmpty() && slot.getItem().getId().equals(itemId))
                .findFirst();
    }
    
    // 아이템 이름으로 찾기
    public Optional<InventorySlot> findItemByName(String itemName) {
        return slots.stream()
                .filter(slot -> !slot.isEmpty() && slot.getItem().getName().equals(itemName))
                .findFirst();
    }
    
    // 아이템 수량 확인
    public int getItemQuantity(Long itemId) {
        Optional<InventorySlot> slot = findItem(itemId);
        return slot.map(InventorySlot::getQuantity).orElse(0);
    }
    
    // 아이템 사용 (소비 아이템 사용 또는 착용)
    public boolean useItem(Long itemId) {
        if (itemId == null) {
            return false;
        }
        
        Optional<InventorySlot> slot = findItem(itemId);
        if (slot.isPresent()) {
            InventorySlot inventorySlot = slot.get();
            Item item = inventorySlot.getItem();
            
            // 소비 아이템인 경우 (포션 등)
            if ("소비아이템".equals(item.getItemType())) {
                // 수량 1 감소
                if (inventorySlot.getQuantity() > 1) {
                    inventorySlot.setQuantity(inventorySlot.getQuantity() - 1);
                } else {
                    // 수량이 1이면 슬롯을 비움
                    inventorySlot.setItem(null);
                    inventorySlot.setQuantity(0);
                }
                return true;
            }
            // 장비 아이템인 경우 (착용 로직은 Player 클래스에서 처리)
            else if ("한손검".equals(item.getItemType()) || "활".equals(item.getItemType()) || 
                     "지팡이".equals(item.getItemType()) || "방어구".equals(item.getItemType())) {
                // 장비 아이템은 사용해도 인벤토리에서 사라지지 않음
                // 실제 착용 로직은 Player 클래스에서 처리
                return true;
            }
        }
        
        return false;
    }
    
    // 인벤토리 가득 찼는지 확인
    public boolean isFull() {
        return slots.stream().noneMatch(InventorySlot::isEmpty);
    }
    
    // 빈 슬롯 개수
    public int getEmptySlotCount() {
        return (int) slots.stream().filter(InventorySlot::isEmpty).count();
    }
    
    // 사용 중인 슬롯 개수
    public int getUsedSlotCount() {
        return maxSize - getEmptySlotCount();
    }
    
    // 인벤토리 정리 (빈 슬롯을 뒤로 이동)
    public void organize() {
        List<InventorySlot> organizedSlots = new ArrayList<>();
        
        // 아이템이 있는 슬롯들을 앞으로
        slots.stream()
                .filter(slot -> !slot.isEmpty())
                .forEach(organizedSlots::add);
        
        // 빈 슬롯들을 뒤로
        int emptyCount = maxSize - organizedSlots.size();
        for (int i = 0; i < emptyCount; i++) {
            organizedSlots.add(new InventorySlot(organizedSlots.size() + 1));
        }
        
        // 슬롯 번호 재정렬
        for (int i = 0; i < organizedSlots.size(); i++) {
            organizedSlots.get(i).setSlotNumber(i + 1);
        }
        
        this.slots = organizedSlots;
    }
    
    // 특정 슬롯 잠금/해제
    public boolean setSlotLocked(int slotNumber, boolean locked) {
        if (slotNumber < 1 || slotNumber > maxSize) {
            return false;
        }
        
        InventorySlot slot = slots.get(slotNumber - 1);
        slot.setLocked(locked);
        return true;
    }
    
    // 인벤토리 크기 확장
    public boolean expandInventory(int additionalSlots) {
        if (additionalSlots <= 0) {
            return false;
        }
        
        int newSize = maxSize + additionalSlots;
        for (int i = maxSize + 1; i <= newSize; i++) {
            slots.add(new InventorySlot(i));
        }
        
        maxSize = newSize;
        return true;
    }
    
    // 모든 아이템 목록 반환
    public List<InventorySlot> getAllItems() {
        return slots.stream()
                .filter(slot -> !slot.isEmpty())
                .toList();
    }
    
    // 특정 타입의 아이템들 반환
    public List<InventorySlot> getItemsByType(String itemType) {
        return slots.stream()
                .filter(slot -> !slot.isEmpty() && slot.getItem().getItemType().equals(itemType))
                .toList();
    }
    
    // 인벤토리 정보 출력
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventory [Size: ").append(maxSize)
          .append(", Used: ").append(getUsedSlotCount())
          .append(", Empty: ").append(getEmptySlotCount()).append("]\n");
        
        for (InventorySlot slot : slots) {
            sb.append(slot.toString()).append("\n");
        }
        
        return sb.toString();
    }
    
    // Getter & Setter
    public List<InventorySlot> getSlots() {
        return slots;
    }
    
    public void setSlots(List<InventorySlot> slots) {
        this.slots = slots;
    }
    
    public int getMaxSize() {
        return maxSize;
    }
    
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}