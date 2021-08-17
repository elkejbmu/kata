package my.example.kata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KataApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(KataApplication.class);
		application.setBanner(new KataBanner());
		application.run(args);

	}

}
