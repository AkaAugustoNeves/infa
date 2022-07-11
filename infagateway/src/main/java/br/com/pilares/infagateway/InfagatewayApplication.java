package br.com.pilares.infagateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class InfagatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfagatewayApplication.class, args);
	}

}
