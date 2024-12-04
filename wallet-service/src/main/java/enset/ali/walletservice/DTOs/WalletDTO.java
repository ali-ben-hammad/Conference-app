package enset.ali.walletservice.DTOs;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class WalletDTO {
    private UUID id;
    private double balance;
    private String currency;
}
