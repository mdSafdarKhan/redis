package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringBootRedisDemoApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRedisDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Before: " + userRepository.findAll());
		User user1 = new User("Safdar", 1000L);
		User user2 = new User("Farhan", 6000L);
		User user3 = new User("Kamran", 12000L);

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		System.out.println("After: " + userRepository.findAll());
		System.out.println("Users loaded...");
	}
}
