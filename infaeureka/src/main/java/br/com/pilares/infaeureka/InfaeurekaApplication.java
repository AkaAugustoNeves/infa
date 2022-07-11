package br.com.pilares.infaeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class InfaeurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfaeurekaApplication.class, args);
	}

}
