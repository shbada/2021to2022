package hello.itemservice;

import hello.itemservice.config.*;
import hello.itemservice.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


//@Import(MemoryConfig.class) /* MemoryConfig 를 설정 파일로 사용 */
//@Import(JdbcTemplateV1Config.class) /* JdbcTemplateV1Config 를 설정 파일로 사용 */
//@Import(JdbcTemplateV2Config.class) /* JdbcTemplateV2Config 를 설정 파일로 사용 */
//@Import(JdbcTemplateV3Config.class) /* JdbcTemplateV3Config 를 설정 파일로 사용 */
@Import(MybatisConfig.class) /* MybatisConfig 를 설정 파일로 사용 */
/* scanBasePackages 지정 안하면 현재 위치 기준 하위가 모두 컴포넌트 대상이 됨 */
@SpringBootApplication(scanBasePackages = "hello.itemservice.web")
@Slf4j
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	@Bean
	@Profile("local") /* 특정 프로필의 경우에만 해당 스프링 빈을 등록한다. */
	public TestDataInit testDataInit(ItemRepository itemRepository) {
		return new TestDataInit(itemRepository);
	}

	/* application.properties 로 옮긴다. */
//	@Bean
//	@Profile("test") /* 특정 프로필의 경우에만 해당 스프링 빈을 등록한다. */
//	public DataSource dataSource(ItemRepository itemRepository) {
//		log.info("메모리 데이터베이스 초기화");
//		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//		driverManagerDataSource.setDriverClassName("org.h2.Driver");
//		// DB_CLOSE_DELAY=-1 : 임베디드 모드에서는 데이터 베이스 커넥션 연결이 모두 끊어지면 데이터베이스도 종료되는데, 이를 방지한다.
//		driverManagerDataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1"); // 메모리 DB
//		driverManagerDataSource.setUsername("sa");
//		driverManagerDataSource.setPassword("");
//
//		return driverManagerDataSource;
//	}
}
