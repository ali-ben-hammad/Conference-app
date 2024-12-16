package enset.ali.conferenceservice.fiegn;

import enset.ali.conferenceservice.entities.Conference;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "wallet-service")
public interface WalletRestClient {
    @GetMapping("/api/keynotes/{id}")
    Conference getWalletById(@PathVariable UUID id);

    @PutMapping("/api/wallets/{id}/updateBlance")
    Conference updateWalletBalance(@PathVariable("id") UUID id, @RequestParam double balance);
}
