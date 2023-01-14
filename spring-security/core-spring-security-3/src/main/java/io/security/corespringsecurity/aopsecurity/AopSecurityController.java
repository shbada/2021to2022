package io.security.corespringsecurity.aopsecurity;

import io.security.corespringsecurity.domain.dto.AccountDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/*
    메서드 설정방식 초기화
    GlobalMethodSecurityConfiguration.java > methodSecurityMetadataSource()
    PrePostAnnotationSecurityMetadataSource.java > attrs : 권한정보를 가지고있음 (hasRole('ROLE_USER') and #account.username == principal.username)
    DelegatingMethodSecurityMetadataSource.java : 권한 정보를 Map에 저장
    AbstractFallbackMethodSecurityMetadataSource.java > @Secured("ROLE_USER") 이라는게 다른 메서드에 있었을때 해당 권한 처리
 */
@Controller
public class AopSecurityController {
    /*
        아래 메서드 검사
        MethodSecurityInterceptor.java
        AbstractSecurityInterceptor.java
        - PrePostAnnotationSecurityMetadataSource.java
        - SecuredAnnotationSecurityMetadataSource.java
     */
    @GetMapping("/preAuthorize") // URL 방식은 통과. DB에 해당 url에 대한 권한 정보를 셋팅하지 않았으므로
    @PreAuthorize("hasRole('ROLE_USER') and #account.username == principal.username") // 매개변수 account 객체 참조
    public String preAuthorize(AccountDto account, Model model, Principal principal) {
        model.addAttribute("method", "Success @PreAuthorize");
        return "aop/method";
    }
}
