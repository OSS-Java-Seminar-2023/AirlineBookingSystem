package hr.OSSAirline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {
		"hr.OSSAirline.controllers",
		"hr.OSSAirline.repositories",
		"hr.OSSAirline.services",
		"hr.OSSAirline.mappers",
		"hr.OSSAirline.dto"
})
@EntityScan("hr.OSSAirline.models")
@EnableScheduling
@EnableAsync
public class OssAirlineApplication {


	public static void main(String[] args) {
		SpringApplication.run(OssAirlineApplication.class, args);
	}
}
