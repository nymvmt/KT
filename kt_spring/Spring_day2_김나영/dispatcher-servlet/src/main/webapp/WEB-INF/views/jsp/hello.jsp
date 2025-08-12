<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello Spring MVC</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 50px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 30px;
            background-color: rgba(255,255,255,0.1);
            border-radius: 15px;
            backdrop-filter: blur(10px);
        }
        h1 { text-align: center; font-size: 2.5em; }
        .message-box {
            background-color: rgba(255,255,255,0.2);
            padding: 20px;
            border-radius: 10px;
            margin: 20px 0;
        }
        .back-link {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #ff6b6b;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .back-link:hover { background-color: #ff5252; }
        
        /* 테마 변경 컨트롤 */
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
        .btn {
            background-color: #ff6b6b;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
            margin: 2px;
            font-size: 12px;
            transition: all 0.3s ease;
        }
        .btn:hover {
            background-color: #ff5252;
        }
        .theme-btn {
            background-color: #4CAF50;
        }
        .theme-btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <!-- 테마 변경 컨트롤 (우측 상단) -->
    <div class="theme-controls">
        <h4>🎨 테마</h4>
        <button class="btn theme-btn" onclick="changeTheme('light')">☀️</button>
        <button class="btn theme-btn" onclick="changeTheme('dark')">🌙</button>
        <button class="btn theme-btn" onclick="changeTheme('rainbow')">🌈</button>
    </div>

    <div class="container">
        <h1>👋 Hello Spring MVC!</h1>

        <div class="message-box">
            <h3>📧 메시지:</h3>
            <p style="font-size: 20px;">${message}</p>
        </div>

        <div class="message-box">
            <h3>⏰ 현재 시간:</h3>
            <p>${currentTime}</p>
        </div>

        <div class="message-box">
            <h3>🔍 처리 과정:</h3>
            <ol>
                <li>/hello 요청이 들어왔습니다</li>
                <li>DispatcherServlet이 HelloController로 전달했습니다</li>
                <li>MessageService에서 메시지를 생성했습니다</li>
                <li>Model에 데이터를 담아 hello.jsp로 전달했습니다</li>
                <li>이 페이지가 완성되었습니다! 🎉</li>
            </ol>
        </div>

        <div style="text-align: center; margin-top: 20px;">
            <a href="/demo4-1.0-SNAPSHOT/" class="back-link">🏠 홈으로 돌아가기</a>
            <a href="/demo4-1.0-SNAPSHOT/about" class="back-link" style="margin-left: 10px;">📖 소개 보기</a>
            <a href="/demo4-1.0-SNAPSHOT/welcome" class="back-link" style="margin-left: 10px;">🎮 welcome</a>
        </div>
    </div>

    <script>
        // 테마 변경 함수
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
                    container.style.backgroundColor = 'rgba(255,255,255,0.9)';
                    container.style.color = '#333333';
                    body.style.background = 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)';
                    break;
                case 'dark':
                    container.style.backgroundColor = 'rgba(44,62,80,0.9)';
                    container.style.color = '#ecf0f1';
                    body.style.background = 'linear-gradient(135deg, #2c3e50 0%, #34495e 100%)';
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
    </script>
</body>
</html>
