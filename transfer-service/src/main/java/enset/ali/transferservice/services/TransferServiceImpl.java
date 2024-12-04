package enset.ali.transferservice.services;

import enset.ali.transferservice.DTOs.TransferDTO;
import enset.ali.transferservice.entities.Transfer;
import enset.ali.transferservice.enums.TransferStatus;
import enset.ali.transferservice.exceptions.InsufficientBalanceException;
import enset.ali.transferservice.exceptions.TransferNotFoundException;
import enset.ali.transferservice.fiegn.WalletRestClient;
import enset.ali.transferservice.mappers.TransferMapper;
import enset.ali.transferservice.models.Wallet;
import enset.ali.transferservice.repositories.TransferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class TransferServiceImpl implements TransferService {
    private TransferRepository transferRepository;
    private TransferMapper transferMapper;
    private final WalletRestClient walletRestClient;
    @Override
    public List<TransferDTO> getTransfers() {
        List<TransferDTO> transferDTOS = transferRepository
                .findAll().stream()
                .map(transferMapper::toTransferDTO)
                .toList();
        return transferDTOS;
    }

    @Override
    public TransferDTO getTransferById(long id) throws TransferNotFoundException {
        return transferMapper.toTransferDTO(transferRepository.findById(id).orElseThrow(() -> new TransferNotFoundException("Transfer with ID " + id + " not found")));
    }

    @Override
    public TransferDTO transfer(UUID sourceWalletId, UUID destinationWalletId, double amount) throws InsufficientBalanceException {
        log.info("saving new transfer");
        Wallet sourceWallet = walletRestClient.getWalletById(sourceWalletId);
        Wallet destinationWallet = walletRestClient.getWalletById(destinationWalletId);

        // Check if the source wallet has sufficient balance
        if (sourceWallet.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance in source wallet.");
        }
        log.info("source wallet balance: {}", sourceWallet.getBalance());

        // Calculate new balances
        double newSourceBalance = sourceWallet.getBalance() - amount;
        double newDestinationBalance = destinationWallet.getBalance() + amount;

        // Update wallet balances using Feign client
        walletRestClient.updateWalletBalance(sourceWalletId, newSourceBalance);
        walletRestClient.updateWalletBalance(destinationWalletId, newDestinationBalance);

        log.info("source wallet new balance: {}", newSourceBalance);

        // Create and save the transfer record
        Transfer transfer = Transfer.builder()
                .sourceWalletId(sourceWalletId)
                .destinationWalletId(destinationWalletId)
                .amount(amount)
                .date(new Date())
                .status(TransferStatus.VALIDATED)
                .build();

        transfer = transferRepository.save(transfer);
        return transferMapper.toTransferDTO(transfer);
    }

    @Override
    public void deleteTransfer(long id) throws TransferNotFoundException {
        log.info("deleting transfer with id: {}", id);
        if (!transferRepository.existsById(id)) throw new TransferNotFoundException("Transfer with ID " + id + " not found");
        transferRepository.deleteById(id);
    }
}
