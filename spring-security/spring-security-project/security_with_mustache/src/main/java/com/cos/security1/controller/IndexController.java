package com.cos.security1.controller;

import com.cos.security1.config.auth.PrincipalDetails;
import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 인덱스
     * @return
     */
    @GetMapping({ "", "/" })
    public @ResponseBody String index() {
        return "인덱스 페이지입니다.";
    }

    /**
     * 세션 정보 확인
     * @param principal
     * @return
     */
    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principal) {
        System.out.println("Principal : " + principal);

        // iterator 순차 출력 해보기
        Iterator<? extends GrantedAuthority> iter = principal.getAuthorities().iterator();
        while (iter.hasNext()) {
            GrantedAuthority auth = iter.next();
            System.out.println(auth.getAuthority());
        }

        return "유저 페이지입니다.";
    }

    /**
     * 어드민 페이지
     * @return
     */
    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "어드민 페이지입니다.";
    }

    /**
     * 매니저 페이지
     * @return
     */
    /* 권한 제어, securityConfig @EnableGlobalMethodSecurity */
    //@PostAuthorize("hasRole('ROLE_MANAGER')") // 함수가 종료된 후 권한 제어
    //@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')) // 함수 실행 전 권한 제어
    @Secured("ROLE_MANAGER") /* 권한 제어, securityConfig @EnableGlobalMethodSecurity */
    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "매니저 페이지입니다.";
    }

    /**
     * 로그인 페이지
     * 만약 security 설정이 없다면, /login url 은 시큐리티가 낚아챔. (다른 login 페이지로 이동시킴)
     * securityConfig 파일 생성 후 위 낚아챘던 현상이 사라짐. 그럼 login 설정이 필요해짐.
     *
     * login -> loginForm 으로 변경함 (loginForm이 더 맞아보여서)
     * @return
     */
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    /**
     * 회원가입 페이지
     * @return
     */
    @GetMapping("/join")
    public String join() {
        return "join";
    }

    /**
     * 회원가입
     * @param user
     * @return
     */
    @PostMapping("/joinProc")
    public String joinProc(User user) {
        System.out.println("회원가입 진행 : " + user);

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        userRepository.save(user);

        return "redirect:/";
    }
}