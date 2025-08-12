-- H2 데이터베이스 스키마
CREATE TABLE IF NOT EXISTS product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price INT NOT NULL,
    stock INT NOT NULL DEFAULT 0
);

-- 초기 데이터 삽입
INSERT INTO product (name, description, price, stock) VALUES 
('노트북', '고성능 게이밍 노트북', 1500000, 10),
('아이패드 프로', '애플펜슬 프로 호환', 1200000, 15),
('아이패드 미니', '가성비 최고의 선택', 800000, 20),
('스마트폰', '필요한 기능을 합리적인 가격으로', 600000, 25);