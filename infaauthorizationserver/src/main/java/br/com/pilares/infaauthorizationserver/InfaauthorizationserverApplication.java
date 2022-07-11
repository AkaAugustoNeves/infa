package br.com.pilares.infaauthorizationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class InfaauthorizationserverApplication {

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		System.out.println(passwordEncoder.encode("infasenhabase")); 
		SpringApplication.run(InfaauthorizationserverApplication.class, args);
	}

}
