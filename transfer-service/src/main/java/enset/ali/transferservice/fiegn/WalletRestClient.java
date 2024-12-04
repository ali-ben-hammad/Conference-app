package enset.ali.transferservice.fiegn;

import enset.ali.transferservice.models.Wallet;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "wallet-service")
public interface WalletRestClient {
    @GetMapping("/api/wallets/{id}")
    Wallet getWalletById(@PathVariable UUID id);

    @PutMapping("/api/wallets/{id}/updateBlance")
    Wallet updateWalletBalance(@PathVariable("id") UUID id, @RequestParam double balance);
}
