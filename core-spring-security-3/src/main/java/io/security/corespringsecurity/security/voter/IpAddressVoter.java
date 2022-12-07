package io.security.corespringsecurity.security.voter;

import io.security.corespringsecurity.security.service.SecurityResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Collection;
import java.util.List;

/*
AffirmativeBased.java > decide()

여러 Voter 중에 하나라도 만족시, 바로 권한 접근을 허용한다.
 */
@RequiredArgsConstructor
public class IpAddressVoter implements AccessDecisionVoter<Object> {

    private final SecurityResourceService securityResourceService;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 심의
     * @param authentication (인증정보 : 현재 사용자 정보)
     * @param object (request 요청 정보) : FilterInvocation
     * @param attributes (자원 접근에 필요한 권한 정보) : FilterInvocation 메타데이터 정보
     * @return
     */
    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {

        /* 사용자의 ip 주소를 얻을 수 있다. */
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String remoteAddress = details.getRemoteAddress();

        List<String> accessIpList = securityResourceService.getAccessIpList();

        int result = ACCESS_DENIED;

        for (String ipAddress : accessIpList){
            if (remoteAddress.equals(ipAddress)) {
                /* 심의를 이어나간다. GRANTED 를 주면 접근 허용이 즉시 되므로 ABSTAIN 을 주자.*/
                return ACCESS_ABSTAIN;
            }
        }

        /* 허용되지 않은 IP의 경우 에러 발생 */
        if (result == ACCESS_DENIED) {
            throw new AccessDeniedException("Invalid IpAddress");
        }

        return result;
    }
}
