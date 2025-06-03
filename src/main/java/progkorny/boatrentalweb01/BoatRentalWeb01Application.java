package progkorny.boatrentalweb01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Ez az annotáció jelzi, hogy ez egy Spring Boot alkalmazás, és engedélyezi az automatikus konfigurációt, komponens-keresést, stb.
public class BoatRentalWeb01Application {

	public static void main(String[] args) {
		// A SpringApplication.run elindítja a Spring Boot alkalmazást,
		// betölti a kontextust, inicializálja a bean-eket, és elindítja a beágyazott szervert (pl. Tomcat),
		// hogy a webalkalmazás elérhető legyen.
		SpringApplication.run(BoatRentalWeb01Application.class, args);
	}

}
