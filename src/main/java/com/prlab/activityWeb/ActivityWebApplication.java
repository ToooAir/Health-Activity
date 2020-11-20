package com.prlab.activityWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.prlab.activityWeb.model.repository")
@EntityScan("com.prlab.activityWeb.model")
@SpringBootApplication
public class ActivityWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivityWebApplication.class, args);
	}

}
