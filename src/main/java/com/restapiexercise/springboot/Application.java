/**
 * 
 */
package com.restapiexercise.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.restapiexercise.springboot.service.FileUploadService;
import com.restapiexercise.springboot.service.FileUploadProperties;

/**
 * @author deepikabolla
 *
 */
@SpringBootApplication
@Configuration
@ComponentScan(basePackages={"com.restapiexercise.springboot"})
@EnableConfigurationProperties(FileUploadProperties.class)
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
    CommandLineRunner init(FileUploadService fileUploadService) {
        return (args) -> {
        	fileUploadService.deleteAll();
        	fileUploadService.init();
        };
    }

}
