package enset.ali.walletservice.services;

import enset.ali.walletservice.DTOs.ClientDTO;
import enset.ali.walletservice.exceptions.ClientNotFoundException;
import enset.ali.walletservice.mappers.ClientMapper;
import enset.ali.walletservice.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;
    @Override
    public List<ClientDTO> getClients() {
        List<ClientDTO> clientDTOS = clientRepository
                .findAll().stream()
                .map(clientMapper::toClientDTO)
                .toList();
        return clientDTOS;
    }

    @Override
    public ClientDTO getClientById(long id) throws ClientNotFoundException {
        return clientMapper.toClientDTO(clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client with ID " + id + " not found")));
    }

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        log.info("saving new client");
        return clientMapper.toClientDTO(clientRepository.save(clientMapper.toClient(clientDTO)));
    }

    @Override
    public ClientDTO updateClient(long id, ClientDTO clientDTO) throws ClientNotFoundException {
        log.info("updating client with id: {}", id);
        if (!clientRepository.existsById(id)) throw new ClientNotFoundException("Client with ID " + id + " not found");
        return clientMapper.toClientDTO(clientRepository.save(clientMapper.toClient(clientDTO)));
    }

    @Override
    public void deleteClient(long id) throws ClientNotFoundException {
        log.info("deleting client with id: {}", id);
        if (!clientRepository.existsById(id)) throw new ClientNotFoundException("Client with ID " + id + " not found");
        clientRepository.deleteById(id);
    }
}
