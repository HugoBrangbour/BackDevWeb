package com.bnm;

import com.bnm.entity.User;
import com.bnm.repository.UserRepository;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Bean;

/**
 * The type Demo application.
 */
@SpringBootApplication
public class BackDevWebApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackDevWebApplication.class, args);
	}

	/**
	 * Demo command line runner.
	 *
	 * @param repository the repository
	 * @return the command line runner
	 */
	@Bean
	public CommandLineRunner seeder(UserRepository repository) {
		return args -> {
			// save a few users
			repository.save(new User("Bauer"));
			repository.save(new User("O'Brian"));
			repository.save(new User("Bauer"));
			repository.save(new User("Palmer"));
			repository.save(new User("Dessler"));
		};
	}
}