package com.diego.appCurso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})		//Así, excluyo la seguridad default que viene con Spring Security
public class AppCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppCursoApplication.class, args);
	}

}
