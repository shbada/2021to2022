package io.security.corespringsecurity.controller.user;

import io.security.corespringsecurity.domain.Account;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    @GetMapping(value="/login")
    public String login(@RequestParam(required = false, value = "error") String error,
                        @RequestParam(required = false, value = "exception") String exception,
                        Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "/login";
    }

    /**
     * GET 방식의 logout 을 생성할때
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 인증 객체는 SecurityContextHolder 안에 있다.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 인증 객체 존재할 경우
        if (auth != null) {
            // 로그아웃 수행
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login"; // 로그인 화면으로 리다이렉트
    }

    @GetMapping("/denied")
    public String accessDenied(@RequestParam(value = "exception", required = false) String exception, Model model) {
        // 인증 객체는 SecurityContextHolder 안에 있다.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = (Account) auth.getPrincipal();

        model.addAttribute("username", account.getUsername());
        model.addAttribute("exception", exception);

        return "user/login/denied";
    }
}
