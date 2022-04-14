package io.security.basicsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String index() {
        return "home";
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
