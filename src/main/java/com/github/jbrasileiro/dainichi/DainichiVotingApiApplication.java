package com.github.jbrasileiro.dainichi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Import(RestTemplate.class)
@PropertySource({
	"classpath:/properties/url.properties",
	"classpath:/properties/custom.properties"
})
@SpringBootApplication
public class DainichiVotingApiApplication {

	public static void main(final String[] args) {
		SpringApplication.run(DainichiVotingApiApplication.class, args);
	}

}
