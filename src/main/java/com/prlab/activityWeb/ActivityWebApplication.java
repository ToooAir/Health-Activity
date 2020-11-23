package com.prlab.activityWeb;

import com.prlab.activityWeb.controller.initData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories("com.prlab.activityWeb.model.repository")
@EntityScan("com.prlab.activityWeb.model")
@SpringBootApplication
public class ActivityWebApplication {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ActivityWebApplication.class, args);
		context.getBean(initData.class).exampleInit();
	}

}
