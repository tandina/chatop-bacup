package com.chatopreview.api;

import com.chatopreview.api.api.entities.Role;
import com.chatopreview.api.api.entities.User;
import com.chatopreview.api.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	public void run(String... args){
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if(null == adminAccount){
			User user = new User();


	user.setEmail("admin@admin.com");
		user.setName("admin");
		user.setRole(Role.ADMIN);
		user.setPassword(new BCryptPasswordEncoder().encode("admin"));
		userRepository.save(user);
		}
	}

}
