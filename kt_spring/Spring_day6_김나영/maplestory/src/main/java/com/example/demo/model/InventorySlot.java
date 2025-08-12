package com.example.demo.model;

public class InventorySlot {
    private int slotNumber;    // 슬롯 번호
    private Item item;         // 아이템
    private int quantity;      // 수량
    private boolean isLocked;  // 슬롯 잠금 여부

    // 기본 생성자
    public InventorySlot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.item = null;
        this.quantity = 0;
        this.isLocked = false;
    }

    // 아이템과 수량을 포함한 생성자
    public InventorySlot(int slotNumber, Item item, int quantity) {
        this.slotNumber = slotNumber;
        this.item = item;
        this.quantity = quantity;
        this.isLocked = false;
    }

    // 슬롯이 비어있는지 확인
    public boolean isEmpty() {
        return item == null || quantity <= 0;
    }

    // 슬롯에 아이템 추가 (수량 증가)
    public boolean addQuantity(int amount) {
        if (item == null || amount <= 0) return false;
        
        quantity += amount;
        return true;
    }

    // 슬롯에서 아이템 제거 (수량 감소)
    public boolean removeQuantity(int amount) {
        if (item == null || amount <= 0 || quantity < amount) return false;
        
        quantity -= amount;
        if (quantity <= 0) {
            item = null;
            quantity = 0;
        }
        return true;
    }

    // 슬롯 비우기
    public void clear() {
        this.item = null;
        this.quantity = 0;
    }

    // 슬롯 잠금/해제
    public void setLocked(boolean locked) {
        this.isLocked = locked;
    }

    // 슬롯이 잠겨있는지 확인
    public boolean isLocked() {
        return isLocked;
    }

    // Getter & Setter
    public int getSlotNumber() { return slotNumber; }
    public void setSlotNumber(int slotNumber) { this.slotNumber = slotNumber; }

    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    // 슬롯 정보 출력
    @Override
    public String toString() {
        if (item == null) {
            return String.format("Slot[%d]: Empty", slotNumber);
        } else {
            return String.format("Slot[%d]: %s x%d", slotNumber, item.getName(), quantity);
        }
    }
}
