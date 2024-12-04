package enset.ali.transferservice.services;

import enset.ali.transferservice.DTOs.TransferDTO;
import enset.ali.transferservice.exceptions.InsufficientBalanceException;
import enset.ali.transferservice.exceptions.TransferNotFoundException;

import java.util.List;
import java.util.UUID;

public interface TransferService {
    List<TransferDTO> getTransfers();
    TransferDTO getTransferById(long id) throws TransferNotFoundException;
    TransferDTO transfer(UUID sourceWalletId, UUID destinationWalletId, double amount) throws InsufficientBalanceException;
    void deleteTransfer(long id) throws TransferNotFoundException;
}
