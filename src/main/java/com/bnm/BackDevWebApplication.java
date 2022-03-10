package com.bnm;

import com.bnm.entity.CarteForm;
import com.bnm.entity.CarteMagic;
import com.bnm.repository.CarteMagicRepository;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Bean;

import java.util.*;

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
	//Sonar suggère de réduire la compléxité cognitive, la message a été vu.
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
					"Flash\nFlying, vigilance\nWhen Archangel Avacyn enters the battlefield, creatures you control gain indestructible until end of turn.\nWhen a non-Angel creature you control dies, transform Archangel Avacyn at the beginning of the next upkeep.",
					"4",
					"4",
					"https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=411061&type=card"
			)));

			List<String> keyWord = generateMotCle();
			for(int i = 1; i <= 100; i++){
				StringBuilder mana = new StringBuilder("");
				//On va avoir entre 1 et 4 d'une certaine couleur
				//Il est conseillé par Sonar d'utiliser la bibliothèque random de java.util plutôt que celle de Math
				java.util.Random r = new Random();
				int modulo = r.nextInt(4) + 1;
				String couleur = "";
				if(i%5 == 0 ){
					mana.append("{W}".repeat(modulo));
					couleur = "White";
				}else if(i%5 == 1 ){
					mana.append("{B}".repeat(modulo));
					couleur = "Black";
				}else if(i%5 == 2 ){
					mana.append("{G}".repeat(modulo));
					couleur = "Green";
				}else if(i%5 == 3 ){
					mana.append("{R}".repeat(modulo));
					couleur = "Red";
				}else {
					mana.append("{U}".repeat(modulo));
					couleur = "Blue";
				}

				//On génère un chiffre entre 1 et 5 pour le coût a ajouter
				int cost = r.nextInt(5) + 1;
				mana.insert(0,"{" + cost + "}");
				cost += modulo;

				//Dans cette partie on génère les types de cartes :
				//1 planeswalker (force vide)
				// pour 2 instant / sorceries (force et endu vide)
				// pour 2 creatures dont 30% artifact en plus
				Set<String> type = new HashSet<>();
				String power = "";
				String toughness = "";
				StringBuilder text = new StringBuilder();
				if(r.nextInt(5) == 0) {
					type.add("Planeswalker");
					toughness = String.valueOf(r.nextInt(7) + 1);
					text.append("[+1]: Do something meh cool.\n[−3]: Do something cool.\n[−14]: You win.");
				}else if(r.nextInt(5) == 1){
					type.add("Sorcery");
					text.append("Do something super cool and give ").append(keyWord.get(r.nextInt(keyWord.size()))).append(" and ").append(keyWord.get(r.nextInt(keyWord.size()))).append(" to a creature");
				}else if(r.nextInt(5) == 2){
					type.add("Instant");
					text.append("Do something super cool and give ").append(keyWord.get(r.nextInt(keyWord.size()))).append(" to a creature");
				}else {
					type.add("Creature");
					if(r.nextInt(10)+1 <= 3 ){
						type.add("Artifact");
						power = "*";
						toughness = "*";
						text.append(" This carte power and toughness are each equal to the number of ");
						switch(couleur){
							case "White" :
								text.append("creatures you control.\n");
								break;
							case "Black" :
								text.append("creature cards in all graveyards.\n");
								break;
							case "Green" :
								text.append("lands you control.\n");
								break;
							case "Red" :
								text.append("sorcery cards in all graveyards.\n");
								break;
							case "Blue" :
								text.append("instant cards in all graveyards.\n");
								break;
							default:
								text.append("cards in your hand");
						}
					}else{
						power = String.valueOf(r.nextInt(10) +1);
						toughness = String.valueOf(r.nextInt(10) +1);
					}
					text.append(keyWord.get(r.nextInt(keyWord.size()))).append(" ").append(keyWord.get(r.nextInt(keyWord.size()))).append(" ").append(keyWord.get(r.nextInt(keyWord.size())));

				}

				repository.save(new CarteMagic(new CarteForm(
						"Carte Numéro " + (i+1),
						mana.toString(),
						cost,
						couleur,
						type,
						text.toString(),
						power,
						toughness,
						"https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=132565&type=card"
				)));
			}
		};
	}

	//Une lsite des keyword les plus communs
	private List<String> generateMotCle(){
		List<String> liste = new ArrayList<>();
		liste.add("Attach");
		liste.add("Counter");
		liste.add("Exile");
		liste.add("Fight");
		liste.add("Mill");
		liste.add("Sacrifice");
		liste.add("Scry");
		liste.add("Tap");
		liste.add("Deathtouch");
		liste.add("Defender");
		liste.add("Double strike");
		liste.add("Enchant");
		liste.add("Equip");
		liste.add("First strike");
		liste.add("Flash");
		liste.add("Flying");
		liste.add("Haste");
		liste.add("Hexproof");
		liste.add("Indestructible");
		liste.add("Lifelink");
		liste.add("Menace");
		liste.add("Protection");
		liste.add("Prowess");
		liste.add("Reach");
		liste.add("Trample");
		liste.add("Vigilance");
		return liste;
	}
}