package ua.com.clinicaltrials;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ua.com.clinicaltrials.repositories"})
@ComponentScan(basePackages = {"ua.com.clinicaltrials", "ua.com.clinicaltrials.domain", "ua.com.clinicaltrials.repositories", "ua.com.clinicaltrials.services", "ua.com.clinicaltrials.validator"})
@EntityScan(basePackages = {"ua.com.clinicaltrials.domain"})
public class SpringBootMvcJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMvcJspApplication.class, args);
	}
}
