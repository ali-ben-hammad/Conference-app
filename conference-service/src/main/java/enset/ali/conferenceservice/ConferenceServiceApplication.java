package enset.ali.conferenceservice;

import enset.ali.conferenceservice.entities.Conference;
import enset.ali.conferenceservice.entities.Review;
import enset.ali.conferenceservice.enums.ConferenceType;
import enset.ali.conferenceservice.repositories.ConferenceRepository;
import enset.ali.conferenceservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class ConferenceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConferenceServiceApplication.class, args);
	}

	@Bean
public CommandLineRunner commandLineRunner(ConferenceRepository conferenceRepository, ReviewRepository reviewRepository) {
    return args -> {
        // Creating some conferences
        Conference conference1 = new Conference();
        conference1.setTitre("Tech Innovations 2024");
        conference1.setDate(new Date());
        conference1.setDuree(3.5);
        conference1.setNombreInscrits(120);
        conference1.setScore(4.5);
        conference1.setStatus(ConferenceType.ACADEMIQUE);

        Conference conference2 = new Conference();
        conference2.setTitre("AI & Future Technologies");
        conference2.setDate(new Date());
        conference2.setDuree(2.0);
        conference2.setNombreInscrits(200);
        conference2.setScore(4.8);
        conference2.setStatus(ConferenceType.COMMERCIALE);

        // Saving the conferences first
        conferenceRepository.saveAll(Arrays.asList(conference1, conference2));

        // Creating reviews for the first conference
        Review review1 = new Review();
        review1.setDate(new Date());
        review1.setText("Amazing conference with great speakers!");
        review1.setNote(5);
        review1.setConference(conference1);

        Review review2 = new Review();
        review2.setDate(new Date());
        review2.setText("Very informative but could be shorter.");
        review2.setNote(4);
        review2.setConference(conference1);

        // Creating reviews for the second conference
        Review review3 = new Review();
        review3.setDate(new Date());
        review3.setText("Exceptional content and organization.");
        review3.setNote(5);
        review3.setConference(conference2);

        // Saving reviews explicitly using ReviewRepository
        reviewRepository.saveAll(Arrays.asList(review1, review2, review3));

        System.out.println("Sample data added to the database.");
    };
}

}
