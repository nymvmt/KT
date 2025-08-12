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
            background-color: #4CAF50;
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
            background-color: #45a049;
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
        <h1>🌱 ${title}</h1>
        <p style="font-size: 18px;">${message}</p>

        <div class="navigation">
            <h3>페이지 둘러보기:</h3>
            <a href="/demo4-1.0-SNAPSHOT/hello">👋 인사하기</a>
            <a href="/demo4-1.0-SNAPSHOT/about">📖 소개 보기</a>
            <a href="/demo4-1.0-SNAPSHOT/welcome">🎮 welcome</a>
        </div>

        <hr>
        <p><strong>💡 학습 포인트:</strong></p>
        <ul>
            <li>이 페이지는 "/" 경로로 접근했습니다</li>
            <li>HelloController의 home() 메서드가 처리했습니다</li>
            <li>Model을 통해 제목과 메시지를 전달받았습니다</li>
        </ul>
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
    </script>
</body>
</html>
