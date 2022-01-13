package com.springbasic.tutorial.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springbasic.tutorial.service.AmazonMailService;
import com.springbasic.tutorial.service.MailService;

@Configuration
@ConditionalOnClass(MailService.class)
public class MailServiceAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public MailService getMailService() {
		return new AmazonMailService();
	}
}
