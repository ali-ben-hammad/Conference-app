package enset.ali.transferservice.entities;


import enset.ali.transferservice.enums.TransferStatus;
import enset.ali.transferservice.models.Wallet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private UUID sourceWalletId;
    private UUID destinationWalletId;
    private double amount;

    @Enumerated(EnumType.STRING)
    private TransferStatus status;
}
