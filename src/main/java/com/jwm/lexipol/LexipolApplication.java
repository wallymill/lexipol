package com.jwm.lexipol;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SpringBootApplication
@EnableAsync
public class LexipolApplication {

  @Autowired
  private BeanFactory beanFactory;


  public static void main(String[] args) {
    SpringApplication.run(LexipolApplication.class, args);
  }

  @Bean(name = "multipartResolver")
  public CommonsMultipartResolver commonsMultipartResolver() {
    return new CommonsMultipartResolver();
  }


  @Bean
  public Executor executor() {
		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setCorePoolSize(1);
		threadPoolTaskExecutor.setMaxPoolSize(1);
		threadPoolTaskExecutor.initialize();
		return new LazyTraceExecutor(beanFactory, threadPoolTaskExecutor);
	}

  }
