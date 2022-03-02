package com.spring.cloud.scmproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //@Configuration, @EnableAutoConfiguration and @ComponentScan
public class ScmProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScmProducerApplication.class, args);
	}

}
