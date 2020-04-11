package com.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@SpringBootApplication
@EnableAutoConfiguration
public class SpringProfileExampleApplication {

	private static Environment env;
	public static void main(String[] args) {
		// VM Arfguments  -Dspring.profiles.active=local,dev
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME,"local,test");
		ConfigurableEnvironment environment = SpringApplication.run(SpringProfileExampleApplication.class, args).getEnvironment();
		env = environment;
	}

	@GetMapping("/getAppName")
	public ResponseEntity<String> getApplicationName(){
		String[] activeProfiles = env.getActiveProfiles();
		System.out.println(Arrays.toString(activeProfiles));
		return ResponseEntity.ok(env.getProperty("spring.application.name"));
	}

}
