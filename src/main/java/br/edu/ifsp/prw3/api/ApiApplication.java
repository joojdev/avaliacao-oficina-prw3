package br.edu.ifsp.prw3.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
// Serializa o Page através de um DTO estável (evita o aviso de serialização do PageImpl)
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class ApiApplication {

	public static void main(String[] args) {
		// Integrantes da dupla:
		// João Vitor Piovezan
		// Marcos Vinicius Narçay Stanquini
		SpringApplication.run(ApiApplication.class, args);
	}

}
