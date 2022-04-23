package io.security.basicsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class SecurityController {
    /**
     * 루트 경로
     * spring security dependency 추가 : 즉시 보안이 설정된다.
     * 기본 계정
     * user : user
     * password : console 에 출력되는 비밀번호
     * @return
     */
    @GetMapping("/")
    public String index(HttpSession httpSession) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        /**
         * 인증시, SecurityContextHolder 에 저장된다.
         * SecurityContextHolder.getContext().setAuthentication(authResult);
         */
        // securityContext 객체는 인증 성공시, httpSession 에도 저장이 된다.
        // 인증된 유저가 인증 이후에 접근시, httpSession 의 객체를 가지고와서 다시금 threadLocal 에 다시 저장한다.
        // 특정 KEY 로 객체를 꺼내온다.
        SecurityContext context = (SecurityContext) httpSession.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        Authentication sessionAuthentication = context.getAuthentication();

        return "home";
    }

    @GetMapping("/thread")
    public String thread() {
        // Main thread vs 자식 thread 간의 threadLocal 이 다르다.
        // 그래서 securityContext 가 thread local 별로 저장되는 구조라면 둘은 공유 불가능하다.
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        // 불러올 수 있는가?
                        // 현재 모드 : MODE_THREADLOCAL (default)
                        // authentication 가 null 이다.
                        // 메인스레드의 threadLocal 에 담겨져있고, 자식스레드 안에는 인증객체가 없기 때문이다.
                        // 그래서 모드를 바꿔야한다.
                        // 예시) MultiSecurityConfig.java > SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
                        // 위 모드로 바꾸면, 공유가 가능해지면서 아래 객체에 메인스레드에 저장된 토큰객체를 가져온다.
                        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    }
                }

        ).start();

        return "thread";
    }

    /**
     * 로그인 페이지
     * @return
     */
//    @GetMapping("/loginPage")
//    public String loginPage() {
//        return "loginPage";
//    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }


    @GetMapping("/admin/pay")
    public String adminPay() {
        return "adminPay";
    }


    @GetMapping("/admin/list")
    public String adminList() {
        return "adminList";
    }

    @GetMapping("/denied")
    public String denied() {
        return "denied";
    }

}
