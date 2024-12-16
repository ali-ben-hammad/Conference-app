package enset.ali.conferenceservice.repositories;

import enset.ali.conferenceservice.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, Long> {
}
