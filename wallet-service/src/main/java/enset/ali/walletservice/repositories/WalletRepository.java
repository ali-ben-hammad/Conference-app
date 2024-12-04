package enset.ali.walletservice.repositories;

import enset.ali.walletservice.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
