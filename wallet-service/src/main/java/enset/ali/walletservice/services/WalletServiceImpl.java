package enset.ali.walletservice.services;

import enset.ali.walletservice.DTOs.WalletDTO;
import enset.ali.walletservice.entities.Client;
import enset.ali.walletservice.entities.Wallet;
import enset.ali.walletservice.exceptions.ClientNotFoundException;
import enset.ali.walletservice.exceptions.WalletNotFoundException;
import enset.ali.walletservice.mappers.WalletMapper;
import enset.ali.walletservice.repositories.ClientRepository;
import enset.ali.walletservice.repositories.WalletRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {
    private WalletMapper walletMapper;
    private WalletRepository walletRepository;
    private ClientRepository clientRepository;


    @Override
    public List<WalletDTO> getWallets() {
        return walletRepository.findAll()
                .stream()
                .map(walletMapper::toWalletDTO)
                .toList();
    }

    @Override
    public WalletDTO getWalletById(UUID id) throws WalletNotFoundException {
        return walletMapper.toWalletDTO(walletRepository.findById(id).orElseThrow(() -> new WalletNotFoundException("Wallet with ID " + id + " not found")));
    }

    @Override
    public WalletDTO saveWallet(WalletDTO walletDTO, long clientId) throws ClientNotFoundException {
        log.info("Saving new wallet for client with ID: {}", clientId);
        Client client = clientRepository.findById(clientId).orElseThrow(() ->new ClientNotFoundException("Client with" + clientId + "not found"));

        Wallet wallet = walletMapper.toWallet(walletDTO);
        wallet.setId(UUID.randomUUID());
        wallet.setCreatedAt(new Date());
        wallet.setClient(client);
        return walletMapper.toWalletDTO(walletRepository.save(wallet));
    }


    @Override
    public WalletDTO updateBalance(UUID id, double balance) throws WalletNotFoundException {
        log.info("updating wallet with id: {}", id);
        Wallet wallet = walletRepository.findById(id).orElseThrow(()-> new WalletNotFoundException("Wallet with ID " + id + " not found"));
        wallet.setBalance(balance);
        return walletMapper.toWalletDTO(walletRepository.save(wallet));
    }

    @Override
    public void deleteWallet(UUID id) throws WalletNotFoundException {
        log.info("deleting wallet with id: {}", id);
        if(!walletRepository.existsById(id)) throw new WalletNotFoundException("Wallet not found");
        walletRepository.deleteById(id);
    }
}
