package enset.ali.walletservice.services;

import enset.ali.walletservice.DTOs.WalletDTO;
import enset.ali.walletservice.exceptions.ClientNotFoundException;
import enset.ali.walletservice.exceptions.WalletNotFoundException;

import java.util.List;
import java.util.UUID;

public interface WalletService {
    List<WalletDTO> getWallets();
    WalletDTO getWalletById(UUID id) throws WalletNotFoundException;

    WalletDTO saveWallet(WalletDTO walletDTO, long clientId) throws ClientNotFoundException;

    WalletDTO updateBalance(UUID id, double balance) throws WalletNotFoundException;
    void deleteWallet(UUID id) throws WalletNotFoundException;
}
