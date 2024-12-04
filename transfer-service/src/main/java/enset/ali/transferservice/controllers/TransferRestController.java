package enset.ali.transferservice.controllers;

import enset.ali.transferservice.DTOs.TransferDTO;
import enset.ali.transferservice.exceptions.InsufficientBalanceException;
import enset.ali.transferservice.exceptions.TransferNotFoundException;
import enset.ali.transferservice.services.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/api/transfers")
@RestController
public class TransferRestController {
    private TransferService transferService;

    @GetMapping
    public List<TransferDTO> getTransfers() {
        return transferService.getTransfers();
    }

    @PostMapping
    public ResponseEntity<TransferDTO> transfer(
            @RequestParam UUID sourceWalletId,
            @RequestParam UUID destinationWalletId,
            @RequestParam double amount) throws InsufficientBalanceException {

        TransferDTO transfer = transferService.transfer(sourceWalletId, destinationWalletId, amount);
        return ResponseEntity.ok(transfer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTransfer(@PathVariable long id) throws TransferNotFoundException {
        transferService.deleteTransfer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferDTO> getTransferById(@PathVariable long id) throws TransferNotFoundException {
        TransferDTO transferDTO = transferService.getTransferById(id);
        return ResponseEntity.ok(transferDTO);
    }
}

