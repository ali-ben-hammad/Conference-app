package enset.ali.walletservice.mappers;

import enset.ali.walletservice.DTOs.ClientDTO;
import enset.ali.walletservice.entities.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper{
    ClientDTO toClientDTO(Client client);
    Client toClient(ClientDTO clientDTO);
}
