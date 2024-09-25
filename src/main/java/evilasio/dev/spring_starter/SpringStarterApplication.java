package evilasio.dev.spring_starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import evilasio.dev.spring_starter.seeder.DefaultSeeder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringStarterApplication {

	private final DefaultSeeder defaultSeeder;

	public static void main(String[] args) {
		SpringApplication.run(SpringStarterApplication.class, args);
	}

	@PostConstruct
	public void onStart(){
		defaultSeeder.seedAdmin();
	}
}
