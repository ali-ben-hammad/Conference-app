package enset.ali.walletservice.controllers;

import enset.ali.walletservice.DTOs.WalletDTO;
import enset.ali.walletservice.exceptions.ClientNotFoundException;
import enset.ali.walletservice.exceptions.WalletNotFoundException;
import enset.ali.walletservice.services.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/api/wallets")
@RestController
public class WalletRestController {
    private WalletService walletService;

    @GetMapping
    public List<WalletDTO> getWallets() {
        return walletService.getWallets();
    }

    @PostMapping("/save")
    public ResponseEntity<WalletDTO> saveWallet(
            @RequestBody WalletDTO walletDTO,
            @RequestParam long clientId) throws ClientNotFoundException {

        WalletDTO savedWallet = walletService.saveWallet(walletDTO, clientId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWallet);
    }

    @PutMapping("/{id}/updateBlance")
    public ResponseEntity<WalletDTO> updateWallet(
            @PathVariable UUID id,
            @RequestParam double balance) throws WalletNotFoundException {

        WalletDTO updatedWallet = walletService.updateBalance(id, balance);
        return ResponseEntity.ok(updatedWallet);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteWallet(@PathVariable UUID id) throws WalletNotFoundException {
        walletService.deleteWallet(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletDTO> getWalletById(@PathVariable UUID id) throws WalletNotFoundException {
        WalletDTO walletDTO = walletService.getWalletById(id);
        return ResponseEntity.ok(walletDTO);
    }
}

