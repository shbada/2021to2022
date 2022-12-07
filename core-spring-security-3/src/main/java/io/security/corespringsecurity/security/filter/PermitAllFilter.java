package io.security.corespringsecurity.security.filter;

import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FilterSecurityInterceptor > invoke() 는 이제 이 필터의 invoke()로 넘어온다.
 * (SecurityConfig.java > customFilterSecurityInterceptor() 설정했음)
 *
 * beforeInvocation() 메서드도 우리가 구현한 메서드를 수행한다.
 *
 * 부모의 인가처리 전에, 우리가 설정해놓은 클래스로 바로 인가처리를 하지않도록 한다.
 */
public class PermitAllFilter extends FilterSecurityInterceptor {
    private static final String FILTER_APPLIED = "__spring_security_filterSecurityInterceptor_filterApplied";
    private boolean observeOncePerRequest = true;

    private List<RequestMatcher> permitAllRequestMatchers = new ArrayList<>();

    public PermitAllFilter(String... permitAllResources) {
        /* 권한 체크하지 않는 url 을 리스트에 담는다. */
        for (String resource : permitAllResources) {
            permitAllRequestMatchers.add(new AntPathRequestMatcher(resource));
        }
    }

    @Override
    protected InterceptorStatusToken beforeInvocation(Object object) {
        boolean permitAll = false;

        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        for (RequestMatcher requestMatcher : permitAllRequestMatchers) {
            if (requestMatcher.matches(request)) { // 만족시 인가처리 할 필요가 없다.
                permitAll = true;
                break;
            }
        }

        if (permitAll) {
            return null; // 시큐리티 내부에서 null 이면 권한처리하지 않음
        }

        return super.beforeInvocation(object);
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        if (fi.getRequest() != null && fi.getRequest().getAttribute("__spring_security_filterSecurityInterceptor_filterApplied") != null && this.observeOncePerRequest) {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } else {
            if (fi.getRequest() != null && this.observeOncePerRequest) {
                fi.getRequest().setAttribute(FILTER_APPLIED, Boolean.TRUE);
            }

            InterceptorStatusToken token = beforeInvocation(fi);

            try {
                fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            } finally {
                super.finallyInvocation(token);
            }

            super.afterInvocation(token, (Object)null);
        }

    }

    public boolean isObserveOncePerRequest() {
        return this.observeOncePerRequest;
    }

    public void setObserveOncePerRequest(boolean observeOncePerRequest) {
        this.observeOncePerRequest = observeOncePerRequest;
    }
}
