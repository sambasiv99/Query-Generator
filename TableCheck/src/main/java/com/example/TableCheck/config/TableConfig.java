package com.example.TableCheck.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TableConfig {
	
	@Value("${BackEndService.base.url}")
	private String BEServiceBaseUrl;
	
	@Bean
	public WebClient webClient()
	{
		return WebClient.builder().baseUrl(BEServiceBaseUrl).build();
	}

}
