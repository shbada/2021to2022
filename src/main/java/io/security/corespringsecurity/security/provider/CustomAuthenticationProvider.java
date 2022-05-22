package io.security.corespringsecurity.security.provider;

import io.security.corespringsecurity.security.common.FormWebAuthenticationDetails;
import io.security.corespringsecurity.security.service.AccountContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 검증을 위한 구현
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 입력한 데이터
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        AccountContext accountContext = (AccountContext) userDetailsService.loadUserByUsername(username);

        // accountContext.getAccount() : db data
        if (!passwordEncoder.matches(password, accountContext.getAccount().getPassword())) {
            throw new BadCredentialsException("BadCredentialsException");
        }

        // security Filter 에서 setDetails() 가 호출되면서 요청때 오는 부가정보들을 set 하기 때문에, 아래와 같이 꺼낼 수 있다.
        // FormWebAuthenticationDetails 에서 설정한 secretKey 가져오기
        FormWebAuthenticationDetails formWebAuthenticationDetails = (FormWebAuthenticationDetails) authentication.getDetails();
        String secretKey = formWebAuthenticationDetails.getSecretKey();

        if (secretKey == null || !"secret".equals(secretKey)) {
            // 인증 실패하기
            throw new InsufficientAuthenticationException("check secretKey");
        }


        // 인증 성공시
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(accountContext.getAccount(), null, accountContext.getAuthorities());

        return authenticationToken;
    }

    /**
     * 현재 파라미터로 전달되는 authentication 타입과 authenticationProvider 토큰 타입 일치여부
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        // 일치할때 CustomAuthenticationProvider 가 인증을 처리한다.
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
