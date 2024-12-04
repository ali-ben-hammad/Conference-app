package enset.ali.walletservice.services;

import enset.ali.walletservice.DTOs.ClientDTO;
import enset.ali.walletservice.exceptions.ClientNotFoundException;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getClients();
    ClientDTO getClientById(long id) throws ClientNotFoundException;
    ClientDTO saveClient(ClientDTO clientDTO);
    ClientDTO updateClient(long id, ClientDTO clientDTO) throws ClientNotFoundException;
    void deleteClient(long id) throws ClientNotFoundException;
}
