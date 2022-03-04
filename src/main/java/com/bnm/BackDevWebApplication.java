package com.bnm;

import com.bnm.entity.CarteForm;
import com.bnm.entity.CarteMagic;
import com.bnm.repository.CarteMagicRepository;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

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
	public CommandLineRunner seeder(CarteMagicRepository repository) {
		return args -> {
			Set<String> creature = new HashSet<>();
			creature.add("Creture");
			repository.save(new CarteMagic(new CarteForm(
					"Archangel Avacyn",
					"{3}{W}{W}",
					5,
					"White",
					creature,
					"Flash\\nFlying, vigilance\\nWhen Archangel Avacyn enters the battlefield, creatures you control gain indestructible until end of turn.\\nWhen a non-Angel creature you control dies, transform Archangel Avacyn at the beginning of the next upkeep.",
					"4",
					"4",
					"https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=411061&type=card"
			)));
		};
	}
}