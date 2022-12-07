package com.example.asyncmethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class GitHubLookupService {

	private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);

	private final RestTemplate restTemplate;

	@Autowired
	private Executor taskExecutor;

	public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Async
	public CompletableFuture<User> findUser(String user) throws InterruptedException {
		logger.info("Looking up " + user);
		String threadName = Thread.currentThread().getName();

		/* 스레드 이름과 데이터 값을 출력한다. */
		System.out.printf(" -> Thread Name : %s, Stream Value : %s\n", threadName, user);
		ThreadPoolTaskExecutor tpe = (ThreadPoolTaskExecutor) taskExecutor;
		logger.info("getActiveCount : " + tpe.getActiveCount());
		logger.info("getQueue : " + tpe.getThreadPoolExecutor().getQueue().size());

		String url = String.format("https://api.github.com/users/%s", user);
		User results = restTemplate.getForObject(url, User.class);

		// Artificial delay of 1s for demonstration purposes
		Thread.sleep(1000L);

		return CompletableFuture.completedFuture(results);
	}

}
