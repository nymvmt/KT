class AngryBirdsGame {
    constructor() {
        this.canvas = document.getElementById('gameCanvas');
        this.ctx = this.canvas.getContext('2d');
        this.score = 0;
        this.birdsLeft = 3;
        this.level = 1;
        this.gameRunning = false; // 처음에는 게임이 실행되지 않음
        
        // Record system
        this.bestScore = localStorage.getItem('angryBirdsBestScore') || 0;
        this.bestLevel = localStorage.getItem('angryBirdsBestLevel') || 1;
        this.leaderboard = JSON.parse(localStorage.getItem('angryBirdsLeaderboard')) || [];
        
        // Fireworks for new records
        this.fireworks = [];
        this.showFireworks = false;
        this.fireworkDuration = 0;
        this.maxFireworkDuration = 3000; // 3초간 폭죽 효과
        
        // Game objects
        this.bird = null;
        this.slingshot = { x: 150, y: 400 }; // 300에서 400으로 낮춤
        this.blocks = [];
        this.pigs = [];
        this.projectiles = [];
        this.particles = [];
        
        // Mouse/touch handling for long press and trajectory
        this.isPressing = false;
        this.pressStartTime = 0;
        this.pressDuration = 0;
        this.maxPressTime = 2000; // 2초 최대
        this.pressStartPos = { x: 0, y: 0 };
        this.currentMousePos = { x: 0, y: 0 };
        this.showTrajectory = false;
        
        // Physics
        this.gravity = 0.5;
        this.groundY = 550;
        
        // Images
        this.images = {};
        this.loadImages();
        
        this.setupEventListeners();
        this.updateDisplay();
        
        // 게임 루프 시작 (시작 화면 표시를 위해)
        this.gameLoop();
    }
    
    loadImages() {
        const imageNames = ['bird', 'pig', 'background'];
        let loadedCount = 0;
        
        imageNames.forEach(name => {
            this.images[name] = new Image();
            this.images[name].onload = () => {
                loadedCount++;
                if (loadedCount === imageNames.length) {
                    this.createLevel();
                    this.updateDisplay();
                }
            };
            this.images[name].src = `${name === 'bird' ? 'angrybird.png' : name === 'pig' ? 'greenpig.png' : 'angrybird_background.jpg'}`;
        });
    }
    
    setupEventListeners() {
        document.getElementById('start-btn').addEventListener('click', () => this.startGame());
        document.getElementById('reset-btn').addEventListener('click', () => this.resetGame());
        document.getElementById('leaderboard-btn').addEventListener('click', () => this.showLeaderboard());
        
        // Mouse events for long press and trajectory
        this.canvas.addEventListener('mousedown', (e) => this.handleMouseDown(e));
        this.canvas.addEventListener('mousemove', (e) => this.handleMouseMove(e));
        this.canvas.addEventListener('mouseup', (e) => this.handleMouseUp(e));
        this.canvas.addEventListener('mouseleave', (e) => this.handleMouseUp(e));
        
        // Touch events for mobile
        this.canvas.addEventListener('touchstart', (e) => this.handleTouchStart(e));
        this.canvas.addEventListener('touchmove', (e) => this.handleTouchMove(e));
        this.canvas.addEventListener('touchend', (e) => this.handleTouchEnd(e));
        this.canvas.addEventListener('touchcancel', (e) => this.handleTouchEnd(e));
    }
    
    startGame() {
        console.log('게임 시작 버튼 클릭됨');
        this.gameRunning = true;
        this.createLevel(); // 게임 시작 시 레벨 다시 생성
        
        // 새의 개수를 장애물 개수만큼 설정 (최소 3개)
        this.birdsLeft = Math.max(3, this.blocks.length);
        
        this.updateDisplay();
        console.log('게임 시작! 새 위치:', this.bird);
        console.log('돼지 개수:', this.pigs.length);
        console.log('장애물 개수:', this.blocks.length);
        console.log('새 개수:', this.birdsLeft);
        console.log('게임 상태:', this.gameRunning);
        // 게임 루프는 이미 실행 중이므로 다시 시작하지 않음
    }
    
    resetGame() {
        this.score = 0;
        this.birdsLeft = 3; // 초기값은 3개로 설정
        this.level = 1;
        this.gameRunning = false;
        this.projectiles = [];
        this.particles = [];
        this.isPressing = false;
        this.pressDuration = 0;
        this.showTrajectory = false;
        this.createLevel();
        this.updateDisplay();
    }
    
    createLevel() {
        this.blocks = [];
        this.pigs = [];
        
        // 랜덤 장애물 생성
        this.blocks = this.generateRandomBlocks(this.level);
        
        // 랜덤 돼지 생성
        this.pigs = this.generateRandomPigs(this.level);
        
        // Create bird
        this.bird = {
            x: this.slingshot.x,
            y: this.slingshot.y + 80, // 더 많이 낮춤 (40에서 80으로)
            radius: 20,
            velocity: { x: 0, y: 0 },
            isLaunched: false
        };
        console.log('새 생성됨:', this.bird);
        console.log('랜덤 돼지 생성됨:', this.pigs);
        console.log('랜덤 장애물 생성됨:', this.blocks);
    }
    
    getLevelConfig() {
        const configs = [
            // Level 1
            {
                blocks: [
                    { x: 700, y: 330, width: 60, height: 20, health: 100 }, // 280에서 330으로
                    { x: 700, y: 310, width: 60, height: 20, health: 100 }, // 260에서 310으로
                    { x: 700, y: 290, width: 60, height: 20, health: 100 }  // 240에서 290으로
                ],
                pigs: [
                    { x: 700, y: 350, radius: 25, health: 100 } // 300에서 350으로
                ]
            },
            // Level 2
            {
                blocks: [
                    { x: 650, y: 330, width: 60, height: 20, health: 100 }, // 280에서 330으로
                    { x: 650, y: 310, width: 60, height: 20, health: 100 }, // 260에서 310으로
                    { x: 750, y: 330, width: 60, height: 20, health: 100 }, // 280에서 330으로
                    { x: 750, y: 310, width: 60, height: 20, health: 100 }, // 260에서 310으로
                    { x: 700, y: 290, width: 60, height: 20, health: 100 }  // 240에서 290으로
                ],
                pigs: [
                    { x: 650, y: 350, radius: 25, health: 100 }, // 300에서 350으로
                    { x: 750, y: 350, radius: 25, health: 100 }  // 300에서 350으로
                ]
            },
            // Level 3
            {
                blocks: [
                    { x: 600, y: 330, width: 60, height: 20, health: 100 }, // 280에서 330으로
                    { x: 600, y: 310, width: 60, height: 20, health: 100 }, // 260에서 310으로
                    { x: 700, y: 330, width: 60, height: 20, health: 100 }, // 280에서 330으로
                    { x: 700, y: 310, width: 60, height: 20, health: 100 }, // 260에서 310으로
                    { x: 800, y: 330, width: 60, height: 20, health: 100 }, // 280에서 330으로
                    { x: 800, y: 310, width: 60, height: 20, health: 100 }, // 260에서 310으로
                    { x: 650, y: 290, width: 60, height: 20, health: 100 }, // 240에서 290으로
                    { x: 750, y: 290, width: 60, height: 20, health: 100 }  // 240에서 290으로
                ],
                pigs: [
                    { x: 600, y: 350, radius: 25, health: 100 }, // 300에서 350으로
                    { x: 700, y: 350, radius: 25, health: 100 }, // 300에서 350으로
                    { x: 800, y: 350, radius: 25, health: 100 }  // 300에서 350으로
                ]
            }
        ];
        
        return configs[(this.level - 1) % configs.length];
    }
    
    // 랜덤 돼지 위치 생성 함수
    generateRandomPigs(level) {
        const pigs = [];
        const pigCount = level; // 레벨에 따라 돼지 개수 증가
        
        // 돼지가 나타날 수 있는 영역 정의
        const minX = 500;
        const maxX = 900;
        const minY = 300; // 250에서 300으로 낮춤
        const maxY = 400; // 350에서 400으로 낮춤
        
        for (let i = 0; i < pigCount; i++) {
            let attempts = 0;
            let validPosition = false;
            let x, y;
            
            // 다른 돼지와 겹치지 않는 위치 찾기
            while (!validPosition && attempts < 50) {
                x = Math.random() * (maxX - minX) + minX;
                y = Math.random() * (maxY - minY) + minY;
                
                // 다른 돼지와의 거리 확인
                validPosition = true;
                for (const pig of pigs) {
                    const distance = Math.sqrt((x - pig.x) ** 2 + (y - pig.y) ** 2);
                    if (distance < 80) { // 최소 거리 80픽셀
                        validPosition = false;
                        break;
                    }
                }
                attempts++;
            }
            
            pigs.push({
                x: x,
                y: y,
                radius: 25,
                health: 100
            });
        }
        
        return pigs;
    }
    
    // 랜덤 장애물 생성 함수
    generateRandomBlocks(level) {
        const blocks = [];
        const blockCount = level * 3; // 레벨에 따라 장애물 개수 증가
        
        // 장애물이 나타날 수 있는 영역 정의
        const minX = 400;
        const maxX = 950;
        const minY = 250; // 200에서 250으로 낮춤
        const maxY = 450; // 400에서 450으로 낮춤
        
        for (let i = 0; i < blockCount; i++) {
            let attempts = 0;
            let validPosition = false;
            let x, y, width, height;
            
            // 다른 장애물과 겹치지 않는 위치 찾기
            while (!validPosition && attempts < 50) {
                x = Math.random() * (maxX - minX) + minX;
                y = Math.random() * (maxY - minY) + minY;
                width = Math.random() * 40 + 40; // 40-80 픽셀
                height = Math.random() * 30 + 15; // 15-45 픽셀
                
                // 다른 장애물과의 겹침 확인
                validPosition = true;
                for (const block of blocks) {
                    if (x < block.x + block.width && x + width > block.x &&
                        y < block.y + block.height && y + height > block.y) {
                        validPosition = false;
                        break;
                    }
                }
                attempts++;
            }
            
            if (validPosition) {
                blocks.push({
                    x: x,
                    y: y,
                    width: width,
                    height: height,
                    health: 50, // 체력을 낮춰서 쉽게 파괴되도록
                    color: `hsl(${Math.random() * 60 + 20}, 70%, 40%)` // 랜덤 색상
                });
            }
        }
        
        return blocks;
    }
    
    handleMouseDown(e) {
        console.log('마우스 다운:', this.gameRunning, this.bird?.isLaunched);
        if (!this.gameRunning) {
            console.log('게임이 실행되지 않음');
            return;
        }
        if (this.bird?.isLaunched) {
            console.log('새가 이미 발사됨');
            return;
        }
        
        const rect = this.canvas.getBoundingClientRect();
        const x = e.clientX - rect.left;
        const y = e.clientY - rect.top;
        
        console.log('마우스 클릭:', { x, y, birdX: this.bird.x, birdY: this.bird.y });
        
        // 어디든 클릭하면 발사되도록 설정
        this.isPressing = true;
        this.pressStartTime = Date.now();
        this.pressStartPos = { x: this.bird.x, y: this.bird.y };
        // 실제 마우스 위치를 사용하여 당기는 방향 계산
        this.currentMousePos = { x, y };
        this.showTrajectory = true;
        console.log('슬링샷 활성화!');
    }
    
    handleMouseMove(e) {
        if (!this.isPressing) return;
        
        const rect = this.canvas.getBoundingClientRect();
        this.currentMousePos = {
            x: e.clientX - rect.left,
            y: e.clientY - rect.top
        };
    }
    
    handleMouseUp(e) {
        if (!this.isPressing) return;
        
        this.isPressing = false;
        this.pressDuration = Date.now() - this.pressStartTime;
        this.showTrajectory = false;
        console.log('슬링샷 떼기! 누른 시간:', this.pressDuration, 'ms');
        this.launchBird();
    }
    
    handleTouchStart(e) {
        e.preventDefault();
        const touch = e.touches[0];
        const rect = this.canvas.getBoundingClientRect();
        const x = touch.clientX - rect.left;
        const y = touch.clientY - rect.top;
        
        const distance = Math.sqrt((x - this.bird.x) ** 2 + (y - this.bird.y) ** 2);
        if (distance < 30) {
            this.isPressing = true;
            this.pressStartTime = Date.now();
            this.pressStartPos = { x, y };
            this.currentMousePos = { x, y };
            this.showTrajectory = true;
        }
    }
    
    handleTouchMove(e) {
        e.preventDefault();
        if (!this.isPressing) return;
        
        const touch = e.touches[0];
        const rect = this.canvas.getBoundingClientRect();
        this.currentMousePos = {
            x: touch.clientX - rect.left,
            y: touch.clientY - rect.top
        };
    }
    
    handleTouchEnd(e) {
        e.preventDefault();
        if (!this.isPressing) return;
        
        this.isPressing = false;
        this.pressDuration = Date.now() - this.pressStartTime;
        this.showTrajectory = false;
        this.launchBird();
    }
    
    launchBird() {
        if (this.birdsLeft <= 0) return;
        
        // Press duration determines power (0-2 seconds = 0-25 power) - 파워 증가
        const power = Math.min(25, (this.pressDuration / this.maxPressTime) * 25);
        
        // Calculate angle based on drag direction (180 degrees opposite)
        // 당기는 방향의 반대 방향으로 발사
        const dragAngle = Math.atan2(
            this.currentMousePos.y - this.slingshot.y,
            this.currentMousePos.x - this.slingshot.x
        );
        const launchAngle = dragAngle + Math.PI; // 180도 반대 방향
        
        this.bird.velocity.x = Math.cos(launchAngle) * power;
        this.bird.velocity.y = Math.sin(launchAngle) * power;
        this.bird.isLaunched = true;
        
        console.log('새 발사!', {
            power: power,
            dragAngle: dragAngle * 180 / Math.PI + '도',
            launchAngle: launchAngle * 180 / Math.PI + '도',
            velocity: this.bird.velocity,
            mousePos: this.currentMousePos
        });
        
        this.birdsLeft--;
        this.updateDisplay();
        
        // Add projectile
        this.projectiles.push({
            x: this.bird.x,
            y: this.bird.y,
            velocity: { x: this.bird.velocity.x, y: this.bird.velocity.y },
            radius: 20
        });
        
        // Reset press duration
        this.pressDuration = 0;
    }
    
    calculateTrajectory() {
        if (!this.showTrajectory) return [];
        
        const power = Math.min(25, (this.pressDuration / this.maxPressTime) * 25); // 파워 증가
        
        // Calculate angle based on drag direction (180 degrees opposite)
        const dragAngle = Math.atan2(
            this.currentMousePos.y - this.slingshot.y,
            this.currentMousePos.x - this.slingshot.x
        );
        const launchAngle = dragAngle + Math.PI; // 180도 반대 방향
        
        const velocityX = Math.cos(launchAngle) * power;
        const velocityY = Math.sin(launchAngle) * power;
        
        const trajectory = [];
        let x = this.bird.x;
        let y = this.bird.y;
        let vx = velocityX;
        let vy = velocityY;
        
        // 궤도 계산을 짧게 (50포인트)
        for (let i = 0; i < 50; i++) {
            trajectory.push({ x, y });
            x += vx;
            y += vy;
            vy += this.gravity;
            
            // 땅에 닿거나 화면 밖으로 나가면 중단
            if (y > this.groundY || x < 0 || x > this.canvas.width) break;
        }
        
        return trajectory;
    }
    
    update() {
        // 게임이 실행되지 않았으면 업데이트 건너뛰기
        if (!this.gameRunning) return;
        
        // Update press duration if currently pressing
        if (this.isPressing) {
            this.pressDuration = Date.now() - this.pressStartTime;
        }
        
        // Update projectiles
        this.projectiles.forEach((projectile, index) => {
            projectile.x += projectile.velocity.x;
            projectile.y += projectile.velocity.y;
            projectile.velocity.y += this.gravity;
            
            // Check ground collision
            if (projectile.y > this.groundY) {
                this.projectiles.splice(index, 1);
                this.createExplosion(projectile.x, projectile.y);
            }
            
            // Check block collisions
            this.blocks.forEach((block, blockIndex) => {
                if (this.checkCollision(projectile, block)) {
                    this.projectiles.splice(index, 1);
                    this.createExplosion(projectile.x, projectile.y);
                    this.damageBlock(blockIndex, 100); // 데미지를 50에서 100으로 증가
                }
            });
            
            // Check pig collisions
            this.pigs.forEach((pig, pigIndex) => {
                if (this.checkCollision(projectile, pig)) {
                    this.projectiles.splice(index, 1);
                    this.createExplosion(projectile.x, projectile.y);
                    this.damagePig(pigIndex, 100);
                }
            });
        });
        
        // Update particles
        this.particles.forEach((particle, index) => {
            particle.x += particle.vx;
            particle.y += particle.vy;
            particle.vy += this.gravity * 0.5;
            particle.life--;
            
            if (particle.life <= 0) {
                this.particles.splice(index, 1);
            }
        });
        
        // Update fireworks
        if (this.showFireworks) {
            this.fireworkDuration += 16; // 약 60fps
            
            this.fireworks.forEach((firework, index) => {
                firework.x += firework.vx;
                firework.y += firework.vy;
                firework.vy += this.gravity * 0.3;
                firework.life--;
                
                if (firework.life <= 0) {
                    this.fireworks.splice(index, 1);
                }
            });
            
            // 폭죽 효과 종료
            if (this.fireworkDuration >= this.maxFireworkDuration) {
                this.showFireworks = false;
                this.fireworks = [];
                this.fireworkDuration = 0;
            }
        }
        
        // Check level completion
        if (this.pigs.length === 0) {
            this.levelComplete();
        }
        
        // Check game over
        if (this.birdsLeft <= 0 && this.projectiles.length === 0 && this.pigs.length > 0) {
            this.gameOver();
        }
        
        // Reset bird if needed - 새가 떨어지면 다시 나오도록 수정
        if (this.projectiles.length === 0 && this.birdsLeft > 0) {
            this.bird.x = this.slingshot.x;
            this.bird.y = this.slingshot.y;
            this.bird.isLaunched = false;
            this.bird.velocity = { x: 0, y: 0 };
        }
    }
    
    checkCollision(projectile, object) {
        if (object.radius) {
            // Circular object (pig)
            const distance = Math.sqrt(
                (projectile.x - object.x) ** 2 + (projectile.y - object.y) ** 2
            );
            return distance < projectile.radius + object.radius;
        } else {
            // Rectangular object (block)
            return projectile.x + projectile.radius > object.x &&
                   projectile.x - projectile.radius < object.x + object.width &&
                   projectile.y + projectile.radius > object.y &&
                   projectile.y - projectile.radius < object.y + object.height;
        }
    }
    
    damageBlock(index, damage) {
        this.blocks[index].health -= damage;
        this.score -= 5; // 박스 맞추면 5점 페널티
        
        if (this.blocks[index].health <= 0) {
            this.createExplosion(
                this.blocks[index].x + this.blocks[index].width / 2,
                this.blocks[index].y + this.blocks[index].height / 2
            );
            this.blocks.splice(index, 1);
        }
        
        this.updateDisplay();
    }
    
    damagePig(index, damage) {
        this.pigs[index].health -= damage;
        this.score += 50;
        
        if (this.pigs[index].health <= 0) {
            this.createExplosion(this.pigs[index].x, this.pigs[index].y);
            this.pigs.splice(index, 1);
        }
        
        this.updateDisplay();
    }
    
    createExplosion(x, y) {
        for (let i = 0; i < 20; i++) {
            this.particles.push({
                x: x,
                y: y,
                vx: (Math.random() - 0.5) * 10,
                vy: (Math.random() - 0.5) * 10,
                life: 40,
                color: `hsl(${Math.random() * 360}, 100%, 50%)`
            });
        }
    }
    
    createFirework(x, y) {
        const colors = ['#FF0000', '#00FF00', '#0000FF', '#FFFF00', '#FF00FF', '#00FFFF', '#FFA500', '#FF69B4'];
        const particleCount = 50;
        
        for (let i = 0; i < particleCount; i++) {
            const angle = (Math.PI * 2 * i) / particleCount;
            const speed = 3 + Math.random() * 4;
            const color = colors[Math.floor(Math.random() * colors.length)];
            
            this.fireworks.push({
                x: x,
                y: y,
                vx: Math.cos(angle) * speed,
                vy: Math.sin(angle) * speed,
                life: 60 + Math.random() * 40,
                maxLife: 60 + Math.random() * 40,
                color: color,
                size: 2 + Math.random() * 3
            });
        }
    }
    
    createMultipleFireworks() {
        // 화면 여러 곳에 폭죽 효과 생성
        const positions = [
            { x: this.canvas.width * 0.2, y: this.canvas.height * 0.3 },
            { x: this.canvas.width * 0.8, y: this.canvas.height * 0.4 },
            { x: this.canvas.width * 0.5, y: this.canvas.height * 0.2 },
            { x: this.canvas.width * 0.3, y: this.canvas.height * 0.6 },
            { x: this.canvas.width * 0.7, y: this.canvas.height * 0.5 }
        ];
        
        positions.forEach(pos => {
            this.createFirework(pos.x, pos.y);
        });
    }
    
    addToLeaderboard(score, level, nickname) {
        const newRecord = {
            nickname: nickname,
            score: score,
            level: level,
            date: new Date().toLocaleDateString('ko-KR'),
            timestamp: Date.now()
        };
        
        this.leaderboard.push(newRecord);
        
        // 점수 순으로 정렬 (높은 점수가 위로)
        this.leaderboard.sort((a, b) => b.score - a.score);
        
        // 상위 10개만 유지
        this.leaderboard = this.leaderboard.slice(0, 10);
        
        // localStorage에 저장
        localStorage.setItem('angryBirdsLeaderboard', JSON.stringify(this.leaderboard));
    }
    
    showLeaderboard() {
        const overlay = document.createElement('div');
        overlay.className = 'leaderboard-overlay';
        overlay.innerHTML = `
            <div class="leaderboard-content">
                <h2>🏆 순위표 🏆</h2>
                <div class="leaderboard-table">
                    <div class="leaderboard-header">
                        <span>순위</span>
                        <span>닉네임</span>
                        <span>점수</span>
                        <span>레벨</span>
                        <span>날짜</span>
                    </div>
                    ${this.leaderboard.map((record, index) => `
                        <div class="leaderboard-row ${index < 3 ? 'top-three' : ''}">
                            <span class="rank">${index + 1}</span>
                            <span class="nickname">${record.nickname}</span>
                            <span class="score">${record.score}</span>
                            <span class="level">${record.level}</span>
                            <span class="date">${record.date}</span>
                        </div>
                    `).join('')}
                </div>
                <button onclick="this.parentElement.parentElement.remove()" class="game-btn">닫기</button>
            </div>
        `;
        document.body.appendChild(overlay);
    }
    
    showNicknameInput(score, level, callback) {
        const overlay = document.createElement('div');
        overlay.className = 'nickname-overlay';
        overlay.innerHTML = `
            <div class="nickname-content">
                <h2>🎉 새로운 기록 달성! 🎉</h2>
                <p>점수: ${score} | 레벨: ${level}</p>
                <div class="input-group">
                    <label for="nickname-input">닉네임을 입력하세요:</label>
                    <input type="text" id="nickname-input" maxlength="10" placeholder="닉네임 (최대 10자)" autofocus>
                </div>
                <div class="button-group">
                    <button id="save-record-btn" class="game-btn">저장</button>
                    <button id="skip-record-btn" class="game-btn">건너뛰기</button>
                </div>
            </div>
        `;
        document.body.appendChild(overlay);
        
        const input = document.getElementById('nickname-input');
        const saveBtn = document.getElementById('save-record-btn');
        const skipBtn = document.getElementById('skip-record-btn');
        
        // Enter 키로 저장
        input.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                saveRecord();
            }
        });
        
        // 저장 버튼 클릭
        saveBtn.addEventListener('click', saveRecord);
        
        // 건너뛰기 버튼 클릭
        skipBtn.addEventListener('click', () => {
            document.body.removeChild(overlay);
            if (callback) callback();
        });
        
        function saveRecord() {
            const nickname = input.value.trim();
            if (nickname.length > 0) {
                this.addToLeaderboard(score, level, nickname);
                document.body.removeChild(overlay);
                if (callback) callback();
            } else {
                alert('닉네임을 입력해주세요!');
                input.focus();
            }
        }
        
        // this 바인딩을 위해 함수를 수정
        saveBtn.onclick = () => {
            const nickname = input.value.trim();
            if (nickname.length > 0) {
                this.addToLeaderboard(score, level, nickname);
                document.body.removeChild(overlay);
                if (callback) callback();
            } else {
                alert('닉네임을 입력해주세요!');
                input.focus();
            }
        };
    }
    
    levelComplete() {
        this.level++;
        this.createLevel();
        
        // 새의 개수를 장애물 개수만큼 설정 (최소 3개)
        this.birdsLeft = Math.max(3, this.blocks.length);
        
        // Check for new records
        let newRecord = false;
        if (this.score > this.bestScore) {
            this.bestScore = this.score;
            localStorage.setItem('angryBirdsBestScore', this.bestScore);
            newRecord = true;
        }
        if (this.level > this.bestLevel) {
            this.bestLevel = this.level;
            localStorage.setItem('angryBirdsBestLevel', this.bestLevel);
            newRecord = true;
        }
        
        // Show fireworks for new records
        if (newRecord) {
            this.showFireworks = true;
            this.fireworkDuration = 0;
            this.createMultipleFireworks();
        }
        
        this.updateDisplay();
        
        // Show level complete message
        const message = document.createElement('div');
        message.className = 'level-complete';
        message.textContent = `레벨 ${this.level - 1} 완료!`;
        if (newRecord) {
            message.textContent += ' 🎉 새로운 기록!';
        }
        document.body.appendChild(message);
        
        setTimeout(() => {
            document.body.removeChild(message);
        }, 2000);
    }
    
    gameOver() {
        this.gameRunning = false;
        
        // Check for new records
        let newRecord = false;
        if (this.score > this.bestScore) {
            this.bestScore = this.score;
            localStorage.setItem('angryBirdsBestScore', this.bestScore);
            newRecord = true;
        }
        if (this.level > this.bestLevel) {
            this.bestLevel = this.level;
            localStorage.setItem('angryBirdsBestLevel', this.bestLevel);
            newRecord = true;
        }
        
        // Show fireworks for new records
        if (newRecord) {
            this.showFireworks = true;
            this.fireworkDuration = 0;
            this.createMultipleFireworks();
            
            // 닉네임 입력 받기
            this.showNicknameInput(this.score, this.level, () => {
                // 닉네임 입력 완료 후 게임 오버 화면 표시
                setTimeout(() => {
                    this.showGameOverScreen();
                }, 1000);
            });
        } else {
            this.showGameOverScreen();
        }
    }
    
    showGameOverScreen() {
        const overlay = document.createElement('div');
        overlay.className = 'game-over';
        overlay.innerHTML = `
            <div class="game-over-content">
                <h2>게임 오버!</h2>
                <p>점수: ${this.score}</p>
                <p>최고 점수: ${this.bestScore}</p>
                <p>레벨: ${this.level}</p>
                <p>최고 레벨: ${this.bestLevel}</p>
                <div class="button-group">
                    <button onclick="location.reload()" class="game-btn">다시 시작</button>
                    <button onclick="this.parentElement.parentElement.parentElement.remove(); game.showLeaderboard()" class="game-btn">순위표 보기</button>
                </div>
            </div>
        `;
        document.body.appendChild(overlay);
    }
    
    draw() {
        // Clear canvas
        this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
        
        // 게임이 시작되지 않았으면 검정색 시작 화면 표시
        if (!this.gameRunning) {
            // 검정색 배경
            this.ctx.fillStyle = '#000000';
            this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);
            
            // 시작 안내 텍스트
            this.ctx.fillStyle = '#FFFFFF';
            this.ctx.font = 'bold 48px Arial';
            this.ctx.textAlign = 'center';
            this.ctx.fillText('ANGRY BIRDS', this.canvas.width / 2, this.canvas.height / 2 - 50);
            
            this.ctx.font = '24px Arial';
            this.ctx.fillText('게임 시작 버튼을 눌러주세요!', this.canvas.width / 2, this.canvas.height / 2 + 20);
            
            this.ctx.font = '18px Arial';
            this.ctx.fillText('슬링샷을 당겨서 돼지들을 파괴하세요!', this.canvas.width / 2, this.canvas.height / 2 + 60);
            
            this.ctx.font = '16px Arial';
            this.ctx.fillText('새를 당기는 방향의 반대 방향으로 발사됩니다!', this.canvas.width / 2, this.canvas.height / 2 + 90);
            
            return; // 게임 오브젝트들을 그리지 않음
        }
        
        // Draw background image if loaded - 캔버스에 정확히 맞게 조정
        if (this.images.background && this.images.background.complete) {
            const img = this.images.background;
            
            // 캔버스 전체를 채우도록 이미지 크기 조정
            this.ctx.drawImage(img, 0, 0, this.canvas.width, this.canvas.height);
        } else {
            // Fallback background
            this.ctx.fillStyle = '#87CEEB';
            this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);
        }
        
        // Draw ground
        this.ctx.fillStyle = '#8FBC8F';
        this.ctx.fillRect(0, this.groundY, this.canvas.width, this.canvas.height - this.groundY);
        
        // Draw slingshot
        this.ctx.fillStyle = '#8B4513';
        this.ctx.fillRect(this.slingshot.x - 5, this.slingshot.y, 10, 50);
        
        // Draw trajectory
        if (this.showTrajectory) {
            const trajectory = this.calculateTrajectory();
            this.ctx.strokeStyle = 'rgba(255, 255, 255, 0.8)';
            this.ctx.lineWidth = 3;
            this.ctx.setLineDash([8, 4]); // 점선 간격 조정
            this.ctx.beginPath();
            trajectory.forEach((point, index) => {
                if (index === 0) {
                    this.ctx.moveTo(point.x, point.y);
                } else {
                    this.ctx.lineTo(point.x, point.y);
                }
            });
            this.ctx.stroke();
            this.ctx.setLineDash([]);
            
            // 궤도 끝점에 원 표시
            if (trajectory.length > 0) {
                const endPoint = trajectory[trajectory.length - 1];
                this.ctx.fillStyle = 'rgba(255, 255, 0, 0.8)';
                this.ctx.beginPath();
                this.ctx.arc(endPoint.x, endPoint.y, 5, 0, Math.PI * 2);
                this.ctx.fill();
            }
        }
        
        // Draw bird
        if (!this.bird.isLaunched) {
            if (this.images.bird && this.images.bird.complete) {
                // Draw bird image
                this.ctx.drawImage(
                    this.images.bird,
                    this.bird.x - this.bird.radius,
                    this.bird.y - this.bird.radius,
                    this.bird.radius * 2,
                    this.bird.radius * 2
                );
            } else {
                // Fallback circle
                this.ctx.fillStyle = '#FF0000';
                this.ctx.beginPath();
                this.ctx.arc(this.bird.x, this.bird.y, this.bird.radius, 0, Math.PI * 2);
                this.ctx.fill();
            }
            
            // Draw slingshot bands
            if (this.isPressing) {
                this.ctx.strokeStyle = '#8B4513';
                this.ctx.lineWidth = 3;
                this.ctx.beginPath();
                this.ctx.moveTo(this.slingshot.x - 5, this.slingshot.y);
                this.ctx.lineTo(this.bird.x, this.bird.y);
                this.ctx.stroke();
                
                this.ctx.beginPath();
                this.ctx.moveTo(this.slingshot.x + 5, this.slingshot.y);
                this.ctx.lineTo(this.bird.x, this.bird.y);
                this.ctx.stroke();
                
                // Draw power indicator
                const powerPercent = Math.min(1, this.pressDuration / this.maxPressTime);
                this.ctx.fillStyle = `hsl(${120 - powerPercent * 120}, 100%, 50%)`;
                this.ctx.fillRect(10, 10, 200 * powerPercent, 20);
                this.ctx.strokeStyle = '#000';
                this.ctx.strokeRect(10, 10, 200, 20);
                
                // Draw angle indicator
                const dragAngle = Math.atan2(
                    this.currentMousePos.y - this.slingshot.y,
                    this.currentMousePos.x - this.slingshot.x
                );
                const launchAngle = dragAngle + Math.PI; // 180도 반대 방향
                this.ctx.strokeStyle = '#FFD700';
                this.ctx.lineWidth = 2;
                this.ctx.beginPath();
                this.ctx.moveTo(this.slingshot.x, this.slingshot.y);
                this.ctx.lineTo(
                    this.slingshot.x + Math.cos(launchAngle) * 50,
                    this.slingshot.y + Math.sin(launchAngle) * 50
                );
                this.ctx.stroke();
            }
        }
        
        // Draw blocks
        this.blocks.forEach(block => {
            this.ctx.fillStyle = block.color;
            this.ctx.fillRect(block.x, block.y, block.width, block.height);
        });
        
        // Draw pigs
        this.pigs.forEach(pig => {
            if (this.images.pig && this.images.pig.complete) {
                // Draw pig image
                this.ctx.drawImage(
                    this.images.pig,
                    pig.x - pig.radius,
                    pig.y - pig.radius,
                    pig.radius * 2,
                    pig.radius * 2
                );
            } else {
                // Fallback circle
                this.ctx.fillStyle = '#90EE90';
                this.ctx.beginPath();
                this.ctx.arc(pig.x, pig.y, pig.radius, 0, Math.PI * 2);
                this.ctx.fill();
            }
        });
        
        // Draw projectiles
        this.projectiles.forEach(projectile => {
            if (this.images.bird && this.images.bird.complete) {
                // Draw bird image for projectile
                this.ctx.drawImage(
                    this.images.bird,
                    projectile.x - projectile.radius,
                    projectile.y - projectile.radius,
                    projectile.radius * 2,
                    projectile.radius * 2
                );
            } else {
                // Fallback circle
                this.ctx.fillStyle = '#FF0000';
                this.ctx.beginPath();
                this.ctx.arc(projectile.x, projectile.y, projectile.radius, 0, Math.PI * 2);
                this.ctx.fill();
            }
        });
        
        // Draw particles
        this.particles.forEach(particle => {
            this.ctx.fillStyle = particle.color;
            this.ctx.globalAlpha = particle.life / 40;
            this.ctx.beginPath();
            this.ctx.arc(particle.x, particle.y, 4, 0, Math.PI * 2);
            this.ctx.fill();
        });
        
        // Draw fireworks
        this.fireworks.forEach(firework => {
            this.ctx.fillStyle = firework.color;
            this.ctx.globalAlpha = firework.life / firework.maxLife;
            this.ctx.beginPath();
            this.ctx.arc(firework.x, firework.y, firework.size, 0, Math.PI * 2);
            this.ctx.fill();
        });
        
        this.ctx.globalAlpha = 1;
    }
    
    gameLoop() {
        this.update();
        this.draw();
        requestAnimationFrame(() => this.gameLoop());
    }
    
    updateDisplay() {
        document.getElementById('score').textContent = this.score;
        document.getElementById('best-score').textContent = this.bestScore;
        document.getElementById('birds-left').textContent = this.birdsLeft;
        document.getElementById('level').textContent = this.level;
        document.getElementById('best-level').textContent = this.bestLevel;
    }
}

// Initialize game when page loads
document.addEventListener('DOMContentLoaded', () => {
    window.game = new AngryBirdsGame();
}); 