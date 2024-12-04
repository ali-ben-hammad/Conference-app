package enset.ali.transferservice.repositories;

import enset.ali.transferservice.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
