package co.com.aca.ventas.e_commerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	/*@Autowired
	private PasswordEncoder passwordEncoder;


	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> {
			System.out.println(passwordEncoder.encode("1234"));
		};
	}*/
}
