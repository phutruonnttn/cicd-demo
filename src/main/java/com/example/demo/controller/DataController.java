package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DataController {
    @GetMapping("/")
    public String home() {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>CI/CD Demo App</title>
                <style>
                    * { margin: 0; padding: 0; box-sizing: border-box; }
                    body { 
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                        min-height: 100vh;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                    }
                    .container {
                        background: white;
                        border-radius: 20px;
                        box-shadow: 0 20px 40px rgba(0,0,0,0.1);
                        padding: 40px;
                        max-width: 800px;
                        width: 90%;
                        text-align: center;
                    }
                    h1 { 
                        color: #333;
                        margin-bottom: 20px;
                        font-size: 2.5em;
                        background: linear-gradient(45deg, #667eea, #764ba2);
                        -webkit-background-clip: text;
                        -webkit-text-fill-color: transparent;
                    }
                    .subtitle {
                        color: #666;
                        margin-bottom: 30px;
                        font-size: 1.2em;
                    }
                    .features {
                        display: grid;
                        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
                        gap: 20px;
                        margin: 30px 0;
                    }
                    .feature {
                        background: #f8f9fa;
                        padding: 20px;
                        border-radius: 10px;
                        border-left: 4px solid #667eea;
                    }
                    .feature h3 {
                        color: #333;
                        margin-bottom: 10px;
                    }
                    .feature p {
                        color: #666;
                        font-size: 0.9em;
                    }
                    .buttons {
                        margin: 30px 0;
                    }
                    .btn {
                        display: inline-block;
                        padding: 12px 24px;
                        margin: 10px;
                        background: linear-gradient(45deg, #667eea, #764ba2);
                        color: white;
                        text-decoration: none;
                        border-radius: 25px;
                        transition: transform 0.3s ease;
                        border: none;
                        cursor: pointer;
                        font-size: 1em;
                    }
                    .btn:hover {
                        transform: translateY(-2px);
                        box-shadow: 0 10px 20px rgba(0,0,0,0.2);
                    }
                    .status {
                        background: #e8f5e8;
                        border: 1px solid #4caf50;
                        color: #2e7d32;
                        padding: 15px;
                        border-radius: 10px;
                        margin: 20px 0;
                    }
                    .footer {
                        margin-top: 30px;
                        color: #999;
                        font-size: 0.9em;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>🚀 CI/CD Demo App</h1>
                    <p class="subtitle">Modern Spring Boot Application with Full CI/CD Pipeline</p>
                    
                    <div class="status">
                        ✅ Application is running successfully!<br>
                        🔧 Built with Spring Boot 3.5.5 & Java 21<br>
                        🐳 Containerized with Docker<br>
                        🚀 Deployed on Railway
                    </div>
                    
                    <div class="features">
                        <div class="feature">
                            <h3>🔒 Security</h3>
                            <p>OWASP Dependency Check, ZAP Security Scan, Snyk Vulnerability Detection</p>
                        </div>
                        <div class="feature">
                            <h3>📊 Quality</h3>
                            <p>SonarCloud Analysis, JaCoCo Coverage, SpotBugs & Checkstyle</p>
                        </div>
                        <div class="feature">
                            <h3>🔄 CI/CD</h3>
                            <p>GitHub Actions, Automated Testing, Docker Build & Deploy</p>
                        </div>
                        <div class="feature">
                            <h3>📈 Monitoring</h3>
                            <p>SARIF Reports, GitHub Code Scanning, Artifact Storage</p>
                        </div>
                    </div>
                    
                    <div class="buttons">
                        <a href="/nations" class="btn">🌍 View Nations Data</a>
                        <a href="/currencies" class="btn">💰 View Currencies</a>
                        <a href="/version" class="btn">ℹ️ Version Info</a>
                        <a href="/health" class="btn">❤️ Health Check</a>
                    </div>
                    
                    <div class="footer">
                        <p>Built with ❤️ using Spring Boot, Maven, Docker & GitHub Actions</p>
                        <p>Pipeline includes: Test → Lint → Security Scan → Deploy</p>
                    </div>
                </div>
            </body>
            </html>
            """;
    }

    @GetMapping("/version")
    public String version() {
        return "The actual version is 1.0.0";
    }

    @GetMapping("/nations")
    public JsonNode getRandomNations() {
        var objectMapper = new ObjectMapper();
        var faker = new Faker();
        var nations = objectMapper.createArrayNode();
        for (var i = 0; i < 10; i++) {
            var nation = faker.nation();
            nations.add(objectMapper.createObjectNode()
                    .put("nationality", nation.nationality())
                    .put("capitalCity", nation.capitalCity())
                    .put("flag", nation.flag())
                    .put("language", nation.language()));
        }
        return nations;
    }

    @GetMapping("/currencies")
    public JsonNode getRandomCurrencies() {
        var objectMapper = new ObjectMapper();
        var faker = new Faker();
        var currencies = objectMapper.createArrayNode();
        for (var i = 0; i < 20; i++) {
            var currency = faker.currency();
            currencies.add(objectMapper.createObjectNode().put("name", currency.name())
                    .put("code", currency.code()));
        }
        return currencies;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("timestamp", String.valueOf(System.currentTimeMillis()));
        status.put("version", "1.0.0");
        status.put("environment", "production");
        status.put("uptime", "Running smoothly");
        return ResponseEntity.ok(status);
    }
}

