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
            background-color: #2c3e50;
            color: #ecf0f1;
        }
        .container {
            max-width: 700px;
            margin: 0 auto;
            padding: 30px;
            background-color: #34495e;
            border-radius: 10px;
        }
        h1 { color: #3498db; }
        .info-section {
            background-color: #2c3e50;
            padding: 15px;
            margin: 15px 0;
            border-left: 4px solid #3498db;
        }
        
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
            background-color: #3498db;
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
            background-color: #2980b9;
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
        <h1>📖 ${title}</h1>
        <p style="font-size: 18px;">${description}</p>

        <div class="info-section">
            <h3>🎯 학습 목표</h3>
            <ul>
                <li>Spring MVC의 기본 구조 이해</li>
                <li>DispatcherServlet의 역할 파악</li>
                <li>Controller-Service-View 흐름 체험</li>
                <li>JavaConfig 설정 방법 습득</li>
            </ul>
        </div>

        <div class="info-section">
            <h3>🏗️ 프로젝트 구조</h3>
            <pre>
src/main/java/
├── config/WebConfig.java          (설정)
├── controller/HelloController.java (요청 처리)
└── service/MessageService.java    (비즈니스 로직)

src/main/webapp/WEB-INF/views/
├── home.jsp    (홈페이지)
├── hello.jsp   (인사 페이지)
└── about.jsp   (이 페이지!)
            </pre>
        </div>

        <div style="text-align: center; margin-top: 20px;">
            <a href="/demo4-1.0-SNAPSHOT/" style="
                display: inline-block;
                margin: 5px;
                padding: 10px 20px;
                background-color: #3498db;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            ">🏠 홈으로 돌아가기</a>
            <a href="/demo4-1.0-SNAPSHOT/hello" style="
                display: inline-block;
                margin: 5px;
                padding: 10px 20px;
                background-color: #3498db;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            ">👋 인사하기</a>
            <a href="/demo4-1.0-SNAPSHOT/welcome" style="
                display: inline-block;
                margin: 5px;
                padding: 10px 20px;
                background-color: #3498db;
                color: white;
                text-decoration: none;
                border-radius: 5px;
            ">🎮 welcome</a>
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
                    container.style.backgroundColor = '#ffffff';
                    container.style.color = '#333333';
                    body.style.backgroundColor = '#ecf0f1';
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
    </script>
</body>
</html>
