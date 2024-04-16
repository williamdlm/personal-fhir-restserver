package com.github.williamdlm.personalfhirserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;

@SpringBootApplication(exclude = {ElasticsearchRestClientAutoConfiguration.class})
public class PersonalFhirServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalFhirServerApplication.class, args);
	}

}
