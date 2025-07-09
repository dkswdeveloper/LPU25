package com.lpu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;

import com.lpu.algo.MyNextPasswordHasher;
import com.lpu.algo.MySimplePasswordHasher;
import com.lpu.algo.PasswordHasher;

@Configuration
@ImportResource("classpath:beans.xml")
public class MyBeansConfiguration {
	
	@Bean
	PasswordHasher nextPassswordHasher()
	{
		return new MyNextPasswordHasher(); //if some class not written by you
	}
	@Bean 
	PasswordHasher simplePasswordHasher()
	{
		return new MySimplePasswordHasher();
	}
	

}
