package enset.ali.transferservice.DTOs;

import enset.ali.transferservice.enums.TransferStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class TransferDTO {
    private Long id;
    private UUID sourceWalletId;
    private UUID destinationWalletId;
    private double amount;
    private TransferStatus status;
}
