package enset.ali.keynoteservice;

import enset.ali.keynoteservice.entities.Keynote;
import enset.ali.keynoteservice.repositories.KeynoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class KeynoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KeynoteServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(KeynoteRepository keynoteRepository) {
        return args -> {
            List.of("Ali", "Mohamed", "Hassan", "Omar").forEach(name -> {
                keynoteRepository.save(Keynote.builder().
                        nom(name).
                        prenom(name).
                        fonction("Speaker").
                        email(name + "@gmail.com").build());
            });
        };
    }

}
