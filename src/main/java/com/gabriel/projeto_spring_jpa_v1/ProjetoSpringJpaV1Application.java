package com.gabriel.projeto_spring_jpa_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjetoSpringJpaV1Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringJpaV1Application.class, args);
	}

}
