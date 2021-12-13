package hello.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringmvcApplication.class, args);
    }

    /**
     * response
     *
     * - 정적 리소스
     * 예) 웹 브라우저에 정적인 HTML, css, js을 제공할 때는, 정적 리소스를 사용한다.
     * 다음 디렉토리에 리소스를 넣어두면 스프링 부트가 정적 리소스로 서비스를 제공한다
     * /static , /public , /resources , /META-INF/resources
     *
     * - 뷰 템플릿 사용
     * 예) 웹 브라우저에 동적인 HTML을 제공할 때는 뷰 템플릿을 사용한다.
     * src/main/resources/templates
     * src/main/resources/templates/response/hello.html
     *
     * - HTTP 메시지 사용
     * HTTP API를 제공하는 경우에는 HTML이 아니라 데이터를 전달해야 하므로,
     * HTTP 메시지 바디에 JSON 같은 형식으로 데이터를 실어 보낸다.
     */
}
