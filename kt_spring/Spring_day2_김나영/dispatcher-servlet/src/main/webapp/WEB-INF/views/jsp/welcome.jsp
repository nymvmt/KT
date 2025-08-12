<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
            background-color: #f0f8ff;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        h1 { color: #2e8b57; }
        .navigation { margin: 20px 0; }
        .navigation a {
            display: inline-block;
            margin: 10px;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .navigation a:hover { background-color: #45a049; }
        
        /* 🎨 인터랙티브 기능 스타일 */
        .interactive-section {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px;
            border-radius: 15px;
            margin: 20px 0;
        }
        .btn {
            background-color: #ff6b6b;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 25px;
            cursor: pointer;
            margin: 5px;
            font-size: 14px;
            transition: all 0.3s ease;
        }
        .btn:hover {
            background-color: #ff5252;
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
        }
        .counter-display {
            font-size: 24px;
            font-weight: bold;
            text-align: center;
            margin: 10px 0;
        }
        .theme-btn {
            background-color: #4CAF50;
        }
        .theme-btn:hover {
            background-color: #45a049;
        }
        .time-display {
            font-size: 18px;
            text-align: center;
            padding: 10px;
            background-color: rgba(255,255,255,0.2);
            border-radius: 10px;
            margin: 10px 0;
        }
        .bounce {
            animation: bounce 0.5s;
        }
        @keyframes bounce {
            0%, 20%, 60%, 100% { transform: translateY(0); }
            40% { transform: translateY(-10px); }
            80% { transform: translateY(-5px); }
        }
        /* 테마 변경 버튼 우측 상단 */
        .theme-controls {
            position: fixed;
            top: 20px;
            right: 20px;
            z-index: 1000;
            background: rgba(255,255,255,0.9);
            padding: 10px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .theme-controls h4 {
            margin: 0 0 10px 0;
            font-size: 14px;
            text-align: center;
        }
    </style>
</head>
<body>
    <!-- 테마 변경 컨트롤 (우측 상단) -->
    <div class="theme-controls">
        <h4>🎨 테마</h4>
        <button class="btn theme-btn" onclick="changeTheme('light')" style="font-size: 12px; padding: 5px 10px;">☀️</button>
        <button class="btn theme-btn" onclick="changeTheme('dark')" style="font-size: 12px; padding: 5px 10px;">🌙</button>
        <button class="btn theme-btn" onclick="changeTheme('rainbow')" style="font-size: 12px; padding: 5px 10px;">🌈</button>
    </div>

    <div class="container">
        <h1>🌱 ${title}</h1>
        <p style="font-size: 18px;">${description}</p>

        <!-- 실시간 시계 -->
        <div class="time-display" id="realTimeClock">
            현재 시간: 로딩 중...
        </div>

        <!-- 🌟 새로운 기능: 오늘 요일 메시지 + 랜덤 인사말 -->
        <div style="background-color: #e7f3ff; padding: 15px; border-radius: 8px; margin: 20px 0; border-left: 4px solid #2196F3;">
            <h3 style="color: #1976D2; margin: 0 0 10px 0;">📅 오늘의 메시지</h3>
            <p style="font-size: 16px; margin: 0 0 10px 0; color: #424242;">${todayMessage}</p>
            <p id="randomGreeting" style="font-size: 16px; margin: 0; color: #e91e63; font-weight: bold;">🎲 새로운 인사말을 받아보세요!</p>
            <button class="btn" onclick="generateRandomGreeting()" style="margin-top: 10px;">새로운 인사말</button>
        </div>

        <div class="navigation">
            <h3>페이지 둘러보기:</h3>
            <a href="/demo4-1.0-SNAPSHOT/hello">👋 인사하기</a>
            <a href="/demo4-1.0-SNAPSHOT/about">📖 소개 보기</a>
        </div>

        <hr>
        
        <!-- 🎮 인터랙티브 기능들 -->
        <div class="interactive-section">          
            <!-- 좋아요 카운터 -->
            <div style="text-align: center; margin: 20px 0;">
                <h4>❤️ 좋아요</h4>
                <div class="counter-display" id="clickCounter">0</div>
                <button class="btn" onclick="incrementCounter()">❤️ 좋아요!</button>
                <button class="btn" onclick="resetCounter()">리셋</button>
            </div>
        </div>
        
        <hr>
        <p><strong>💡 welcome </strong></p>
        <ul>
            <li>환영합니다</li>
            <li>첫번째 실습입니다</li>
        </ul>
    </div>

    <!-- 🚀 JavaScript 인터랙티브 기능들 -->
    <script>
        // 좋아요 카운터
        let likeCount = 0;
        
        function incrementCounter() {
            likeCount++;
            document.getElementById('clickCounter').textContent = '❤️ ' + likeCount;
            // 애니메이션 효과
            document.getElementById('clickCounter').classList.add('bounce');
            setTimeout(() => {
                document.getElementById('clickCounter').classList.remove('bounce');
            }, 500);
        }
        
        function resetCounter() {
            likeCount = 0;
            document.getElementById('clickCounter').textContent = '❤️ 0';
        }
        
        // 랜덤 인사말 생성기
        const greetings = [
            "🌟 오늘 정말 멋져 보이세요!",
            "🚀 당신은 할 수 있어요!",
            "💝 행복한 하루 되세요!",
            "🎉 당신은 특별한 사람이에요!",
            "🌈 모든 일이 잘 될 거예요!",
            "⭐ 오늘도 파이팅!",
            "🎊 당신의 미소가 세상을 밝혀요!",
            "🌺 오늘 하루도 화이팅!",
            "🎯 목표를 향해 나아가세요!",
            "🦋 당신은 아름다운 사람이에요!"
        ];
        
        function generateRandomGreeting() {
            const randomIndex = Math.floor(Math.random() * greetings.length);
            const greetingElement = document.getElementById('randomGreeting');
            greetingElement.textContent = greetings[randomIndex];
            greetingElement.style.animation = 'none';
            setTimeout(() => {
                greetingElement.style.animation = 'bounce 0.5s';
            }, 10);
        }
        
        // 실시간 시계
        function updateClock() {
            const now = new Date();
            const timeString = now.toLocaleString('ko-KR', {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit',
                hour: '2-digit',
                minute: '2-digit',
                second: '2-digit'
            });
            document.getElementById('realTimeClock').textContent = '🕐 현재 시간: ' + timeString;
        }
        
        // 1초마다 시계 업데이트
        setInterval(updateClock, 1000);
        updateClock(); // 페이지 로드 시 즉시 실행
        
        // 테마 변경
        function changeTheme(theme) {
            const container = document.querySelector('.container');
            const body = document.body;
            
            // 모든 배경 스타일 초기화
            container.style.background = '';
            container.style.backgroundColor = '';
            body.style.background = '';
            body.style.backgroundColor = '';
            
            switch(theme) {
                case 'light':
                    container.style.backgroundColor = '#ffffff';
                    container.style.color = '#333333';
                    body.style.backgroundColor = '#f0f8ff';
                    break;
                case 'dark':
                    container.style.backgroundColor = '#2c3e50';
                    container.style.color = '#ecf0f1';
                    body.style.backgroundColor = '#34495e';
                    break;
                case 'rainbow':
                    container.style.background = 'linear-gradient(45deg, #ff9a9e 0%, #fecfef 50%, #fecfef 100%)';
                    container.style.color = '#2c3e50';
                    body.style.background = 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)';
                    break;
            }
            
            // 애니메이션 효과
            container.style.transition = 'all 0.5s ease';
            body.style.transition = 'all 0.5s ease';
        }
        
        // 페이지 로드 시 초기화
        window.addEventListener('load', function() {
            // 좋아요 카운터 초기화
            document.getElementById('clickCounter').textContent = '❤️ 0';
        });
    </script>
</body>
</html>
