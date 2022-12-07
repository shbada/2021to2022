package com.api.plan.config.jwt;

import com.api.plan.config.auth.PrincipalDetails;
import com.api.plan.api.entity.Member;
import com.api.plan.api.repository.MemberRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 서버가 클라이언트에서 요청과 함께 보내온 JWT 토큰의 유효성을 체크해야한다.
 *
 * security가 filter을 가지고 있는데 그 필터중에 BasicAuthenticationFilter 라는 것이 있다.
 * 권한이나 인증이 필요한 특정 주소를 요청했을때 위 필터를 무조건 타게되어있다.
 * 만약에 권한이 인증이 필요한 주소가 아니라면 이 필터를 타지않는다.
 */
// 인가
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    /**
     * 인증이나 권한이 필요한 주소 요청이 있을때 해당 필터를 타게된다.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        /* header */
        String header = request.getHeader(JwtEnum.HEADER_STRING.getValue());

        // hedaer 가 있는지 체크
        if(header == null || !header.startsWith(JwtEnum.TOKEN_PREFIX.getValue())) {
            chain.doFilter(request, response);
            return;
        }

        System.out.println("header : "+header);

        // JWT 토큰을 검증해서 정상적인 사용자인지 체크
        String token = request.getHeader(JwtEnum.HEADER_STRING.getValue())
                .replace(JwtEnum.TOKEN_PREFIX.getValue(), "");

        // 토큰 검증 (이게 인증이기 때문에 AuthenticationManager도 필요 없음)
        String memberId = JWT.require(Algorithm.HMAC512(JwtEnum.SECRET.getValue())).build()
                .verify(token)
                .getClaim("memberId").asString();

        // 서명이 정상적으로 완료됨을 의미
        if (memberId != null) {
            Member member = memberRepository.findByMemberId(memberId);

            // 인증은 토큰 검증시 끝. 인증을 하기 위해서가 아닌 스프링 시큐리티가 수행해주는 권한 처리를 위해
            // 아래와 같이 토큰을 만들어서 Authentication 객체를 강제로 만들고 그걸 세션에 저장!
            PrincipalDetails principalDetails = new PrincipalDetails(member);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            principalDetails, //나중에 컨트롤러에서 DI해서 쓸 때 사용하기 편함.
                            null, // 패스워드는 모르니까 null 처리, 어차피 지금 인증하는게 아니니까!!
                            principalDetails.getAuthorities());

            // 내가 SecurityContext에 직접 접근해서 세션을 만들때 자동으로 UserDetailsService에 있는
            // loadByUsername이 호출됨.
            // 강제로 시큐리티의 세션에 접근하여 값 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

}
