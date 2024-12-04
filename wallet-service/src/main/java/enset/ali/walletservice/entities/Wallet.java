package enset.ali.walletservice.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Wallet {
    @Id
    private UUID id;
    private double balance;
    private Date createdAt;
    private String currency;
    @ManyToOne
    private Client client;
}
