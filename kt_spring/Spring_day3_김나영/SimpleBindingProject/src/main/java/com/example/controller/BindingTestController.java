package com.example.controller;

import com.example.model.Character;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 🧪 완전한 바인딩 테스트 시스템
 */
@Controller
@RequestMapping("/binding-test")
public class BindingTestController {

    /**
     * 바인딩 테스트 메인 페이지
     */
    @GetMapping("/")
    public String testHome() {
        return "binding-test/index";
    }

    /**
     * 🔬 단계별 바인딩 과정 시뮬레이션
     */
    @PostMapping("/step-by-step")
    public String stepByStepBinding(
            @ModelAttribute Character character,
            BindingResult bindingResult,
            HttpServletRequest request,
            Model model) {

        System.out.println("🔬 === 단계별 바인딩 과정 시뮬레이션 ===");

        // 1단계: Raw HTTP 파라미터 확인
        System.out.println("1️⃣ HTTP 요청 파라미터들:");
        Map<String, String[]> params = request.getParameterMap();
        params.forEach((key, values) -> {
            System.out.println("  " + key + " = " + java.util.Arrays.toString(values));
        });

        // 2단계: Spring이 생성한 객체 확인
        System.out.println("2️⃣ Spring이 생성한 객체:");
        System.out.println("  객체 타입: " + character.getClass().getSimpleName());
        System.out.println("  해시코드: " + character.hashCode());

        // 3단계: 바인딩 결과 분석
        System.out.println("3️⃣ 바인딩 결과 분석:");
        analyzeBindingResults(character, bindingResult);

        // 4단계: 타입 변환 분석
        System.out.println("4️⃣ 타입 변환 분석:");
        analyzeTypeConversions(request, character);

        System.out.println("🔬 === 시뮬레이션 완료 ===");

        // 뷰에 데이터 전달
        model.addAttribute("character", character);
        model.addAttribute("bindingResult", bindingResult);
        model.addAttribute("httpParams", params);
        model.addAttribute("stepByStep", true);

        return "binding-test/result";
    }

    /**
     * 🚨 에러 시나리오 테스트
     */
    @PostMapping("/error-scenarios")
    public String testErrorScenarios(
            @ModelAttribute Character character,
            BindingResult bindingResult,
            @RequestParam String scenario,
            Model model) {

        System.out.println("🚨 에러 시나리오 테스트: " + scenario);

        // 시나리오별 에러 분석
        Map<String, String> errorAnalysis = new HashMap<>();

        if (bindingResult.hasErrors()) {
            System.out.println("❌ 발견된 에러들:");

            bindingResult.getFieldErrors().forEach(error -> {
                String fieldName = error.getField();
                String errorType = error.getCode();
                Object rejectedValue = error.getRejectedValue();

                System.out.println("  필드: " + fieldName);
                System.out.println("  에러 타입: " + errorType);
                System.out.println("  거부된 값: " + rejectedValue);
                System.out.println("  ---");

                // 에러 분석 결과 저장
                String analysis = analyzeError(fieldName, errorType, rejectedValue);
                errorAnalysis.put(fieldName, analysis);
            });
        } else {
            System.out.println("✅ 에러 없음 - 모든 바인딩 성공!");
        }

        model.addAttribute("character", character);
        model.addAttribute("bindingResult", bindingResult);
        model.addAttribute("errorAnalysis", errorAnalysis);
        model.addAttribute("scenario", scenario);

        return "binding-test/error-result";
    }

    /**
     * 바인딩 결과 상세 분석
     */
    private void analyzeBindingResults(Character character, BindingResult bindingResult) {
        // 성공한 필드들
        System.out.println("  ✅ 성공한 필드들:");
        if (character.getName() != null) {
            System.out.println("    name: " + character.getName());
        }
        if (character.getJob() != null) {
            System.out.println("    job: " + character.getJob());
        }
        System.out.println("    level: " + character.getLevel());
        System.out.println("    health: " + character.getHealth());
        System.out.println("    mana: " + character.getMana());
        System.out.println("    premium: " + character.isPremium());

        // 실패한 필드들
        if (bindingResult.hasErrors()) {
            System.out.println("  ❌ 실패한 필드들:");
            bindingResult.getFieldErrors().forEach(error -> {
                System.out.println("    " + error.getField() + ": " + error.getDefaultMessage());
            });
        }
    }

    /**
     * 타입 변환 과정 분석
     */
    private void analyzeTypeConversions(HttpServletRequest request, Character character) {
        // String → int 변환
        String levelParam = request.getParameter("level");
        System.out.println("  level: \"" + levelParam + "\" → " + character.getLevel());

        String healthParam = request.getParameter("health");
        System.out.println("  health: \"" + healthParam + "\" → " + character.getHealth());

        String manaParam = request.getParameter("mana");
        System.out.println("  mana: \"" + manaParam + "\" → " + character.getMana());

        // String → boolean 변환
        String premiumParam = request.getParameter("premium");
        System.out.println("  premium: \"" + premiumParam + "\" → " + character.isPremium());

        // String[] → List<String> 변환
        String[] skillsParams = request.getParameterValues("skills");
        System.out.println("  skills: " + java.util.Arrays.toString(skillsParams) + " → " + character.getSkills());
    }

    /**
     * 에러 타입별 상세 분석
     */
    private String analyzeError(String fieldName, String errorType, Object rejectedValue) {
        StringBuilder analysis = new StringBuilder();

        analysis.append("필드: ").append(fieldName).append("\n");
        analysis.append("에러 타입: ").append(errorType).append("\n");
        analysis.append("입력값: ").append(rejectedValue).append("\n");

        switch (errorType) {
            case "typeMismatch":
                analysis.append("원인: 타입 변환 실패\n");
                analysis.append("해결: 올바른 형식으로 입력");
                break;
            case "required":
                analysis.append("원인: 필수 필드 누락\n");
                analysis.append("해결: 값을 입력해주세요");
                break;
            default:
                analysis.append("원인: 알 수 없는 에러\n");
                analysis.append("해결: 입력값을 확인해주세요");
        }

        return analysis.toString();
    }
}
