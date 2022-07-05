package hello.itemservice;

import hello.itemservice.config.*;
import hello.itemservice.repository.ItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;


//@Import(MemoryConfig.class) /* MemoryConfig 를 설정 파일로 사용 */
//@Import(JdbcTemplateV1Config.class) /* JdbcTemplateV1Config 를 설정 파일로 사용 */
//@Import(JdbcTemplateV2Config.class) /* JdbcTemplateV2Config 를 설정 파일로 사용 */
@Import(JdbcTemplateV3Config.class) /* JdbcTemplateV3Config 를 설정 파일로 사용 */
/* scanBasePackages 지정 안하면 현재 위치 기준 하위가 모두 컴포넌트 대상이 됨 */
@SpringBootApplication(scanBasePackages = "hello.itemservice.web")
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	@Bean
	@Profile("local") /* 특정 프로필의 경우에만 해당 스프링 빈을 등록한다. */
	public TestDataInit testDataInit(ItemRepository itemRepository) {
		return new TestDataInit(itemRepository);
	}

}
