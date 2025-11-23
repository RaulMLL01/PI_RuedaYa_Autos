package edu.dwes.PI_Raul_Lara_Back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class PiRaulLaraBackApplication {

	public static void main(String[] args) {
		String[][] users = {
				{ "admin", "admin123" },
				{ "operador", "ope1234" },
				{ "comprador0", "v5678" },
				{ "comprador1", "c1234" },
				{ "comprador2", "c5678" }
		};
		SpringApplication.run(PiRaulLaraBackApplication.class, args);
	}

}
