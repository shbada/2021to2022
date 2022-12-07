package io.security.corespringsecurity.security.metadatasource;

import io.security.corespringsecurity.security.service.SecurityResourceService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /* requestMap 작성 : key(요청정보), value(권한정보 리스트) */
    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap = new LinkedHashMap<>();
    private SecurityResourceService securityResourceService;

    public UrlFilterInvocationSecurityMetadataSource(LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap, SecurityResourceService securityResourceService) {
        this.requestMap = requestMap;
        this.securityResourceService = securityResourceService;
    }

    /**
     * 얘를 호출할때 파라미터에 FilterInvocation, MethodInvocation 이 들어올 수 있기 때문에 Object Type이다.
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        /* 타입캐스팅 */
        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        /* 권한 정보 임시 추가 */
        requestMap.put(new AntPathRequestMatcher("/mypage"), Arrays.asList(new SecurityConfig("ROLE_USER")));

        if (requestMap != null) {
            /* RequestMatcher : DB에서 가져온 요청 정보가 담겨져있을 것 */
            for (Map.Entry<RequestMatcher, List<ConfigAttribute>> entry : requestMap.entrySet()) {
                RequestMatcher matcher = entry.getKey();

                /* 요청 정보가 일치한게 있다면, getValue()해서 권한 정보를 리턴한다. */
                if (matcher.matches(request)) {
                    return entry.getValue();
                }
            }
        }

        /* null을 리턴하면 AbstractSecurityInterceptor 에서 인가처리 로직이 더이상 수행되지 않음
          -> 권한 심사 없이 다음 Filter 로 실행함
        */
        return null;
    }

    /** 아래 두개는 당장 쓰진 않음. DefaultFilterInvocationSecurityMetadataSource.java 에서 코드를 가져오자. */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<>();

        for (Map.Entry<RequestMatcher, List<ConfigAttribute>> entry : requestMap
                .entrySet()) {
            allAttributes.addAll(entry.getValue());
        }

        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    public void reload() {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceMap = securityResourceService.getResourceList();
        Iterator<Map.Entry<RequestMatcher, List<ConfigAttribute>>> iterator = resourceMap.entrySet().iterator();

        resourceMap.clear();

        while (iterator.hasNext()) {
            Map.Entry<RequestMatcher, List<ConfigAttribute>> entry = iterator.next();
            requestMap.put(entry.getKey(), entry.getValue());
        }
    }
}
