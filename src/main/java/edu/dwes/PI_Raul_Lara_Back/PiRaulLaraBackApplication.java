package edu.dwes.PI_Raul_Lara_Back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication()
public class PiRaulLaraBackApplication {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String[][] users = {
				{ "admin", "admin123" },
				{ "operador", "ope1234" },
				{ "comprador0", "v5678" },
				{ "comprador1", "c1234" },
				{ "comprador2", "c5678" }
		};

		for (String[] user : users) {
			String username = user[0];
			String password = user[1];
			String hash = encoder.encode(password);
			System.out.println("UPDATE usuario SET passwd='" + hash + "' WHERE username='" + username + "';");
		}
		SpringApplication.run(PiRaulLaraBackApplication.class, args);

	}

}
