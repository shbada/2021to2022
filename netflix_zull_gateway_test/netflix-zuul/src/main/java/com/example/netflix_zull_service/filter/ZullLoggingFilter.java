package com.example.netflix_zull_service.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.RequestContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component /* 빈 등록 */
@Slf4j /* Lombok 을 사용하므로 어노테이션으로 가능 */
public class ZullLoggingFilter extends ZuulFilter {
    /* @Slf4j 대체 */
    // Logger logger = LoggerFactory.getLogger(ZullLoggingFilter.class);

    /**
     * pre (사용자의 요청시, 먼저 실행되는 메소드)
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        log.info("******** printing logs: ");

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        log.info("******** printing logs: " + request.getRequestURI()); // 사용자의 요청정보
        // logger.debug(); // check
        // logger.info(); // message
        // logger.error(); // error

        return null;
    }

    @Override
    public String filterType() {
        return "pre"; /* 사전 필터인지, 사후 필터인지 */
    }

    @Override
    public int filterOrder() {
        return 1; /* 하나밖에 없으므로 1 */
    }

    @Override
    public boolean shouldFilter() {
        return true; /* filter 로 사용하겠다는 의미 */
    }
}
