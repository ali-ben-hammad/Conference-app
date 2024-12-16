package enset.ali.conferenceservice.repositories;

import enset.ali.conferenceservice.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
