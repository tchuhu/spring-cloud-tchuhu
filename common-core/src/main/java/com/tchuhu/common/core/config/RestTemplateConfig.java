package com.tchuhu.common.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author tchuhu
 * @date 2019/2/1
 * RestTemplate
 */
//@Configuration
public class RestTemplateConfig {
	public RestTemplateConfig() {
		System.out.println("==========");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
