package hr.OSSAirline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "hr.OSSAirline.repositories")
@ComponentScan(basePackages = "hr.OSSAirline.controllers")
@ComponentScan(basePackages = "hr.OSSAirline.repositories")
@ComponentScan(basePackages = "hr.OSSAirline.services")
@EntityScan("hr.OSSAirline.models")

public class OssAirlineApplication {


	public static void main(String[] args) {
		SpringApplication.run(OssAirlineApplication.class, args);
	}
}
