package com.my.maintest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.my.maintest.common.paging.PagingComponent;

@Configuration
public class Config {

	
	
	
	@Bean
	PagingComponent pagingComponent() {
		
		return new  PagingComponent();
	}
	
}
