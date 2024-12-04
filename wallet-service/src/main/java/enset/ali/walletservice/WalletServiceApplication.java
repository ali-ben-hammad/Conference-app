package enset.ali.walletservice;

import enset.ali.walletservice.entities.Client;
import enset.ali.walletservice.entities.Wallet;
import enset.ali.walletservice.repositories.ClientRepository;
import enset.ali.walletservice.repositories.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class WalletServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ClientRepository clientRepository, WalletRepository walletRepository) {
        return args -> {
            List.of("Ali", "Mohamed", "Hassan", "Omar").forEach(name -> {
                clientRepository.save(Client.builder().name(name).email(name + "@gmail.com").build());
            });

            clientRepository.findAll().forEach(
                    client -> {
                        walletRepository.save(Wallet.builder()
                                .id(UUID.randomUUID())
                                .balance((double) Math.round(Math.random() * 10000 * 100) / 100)
                                .currency(Math.random() > 0.5 ? "USD" : "EUR")
                                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                                .client(client)
                                .build());
                    }
            );
        };
    }


}
