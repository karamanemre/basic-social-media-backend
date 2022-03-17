package com.emrekaraman.springsocial;

import com.emrekaraman.springsocial.business.abstracts.UserService;
import com.emrekaraman.springsocial.business.dtos.UserDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BasicSocialMediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicSocialMediaApplication.class, args);
	}

//	@Bean
//	CommandLineRunner createInitialUsers(UserService userService){
//		return (args) -> {
//			UserDto user = new UserDto();
//			user.setUserName("user");
//			user.setFullName("Kullanıcı 1");
//			user.setPassword("Aa123**");
//			userService.add(user);
//		};
//	}
}
