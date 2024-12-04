package enset.ali.walletservice.mappers;

import enset.ali.walletservice.DTOs.WalletDTO;
import enset.ali.walletservice.entities.Wallet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletDTO toWalletDTO(Wallet wallet);
    Wallet toWallet(WalletDTO walletDTO);
}

