package com.lpu.todo;

import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoApplication {

	
	public static void main(String[] args) {
//		Properties props = new Properties();
//		props.load(new FileReader("application.properties"));
//		props.get("server.port");
		
		SpringApplication.run(TodoApplication.class, args);
	}

}
