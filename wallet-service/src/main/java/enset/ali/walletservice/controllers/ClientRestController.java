package enset.ali.walletservice.controllers;

import enset.ali.walletservice.DTOs.ClientDTO;
import enset.ali.walletservice.exceptions.ClientNotFoundException;
import enset.ali.walletservice.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/clients")
@RestController
public class ClientRestController {
    private ClientService clientService;

    @GetMapping
    public List<ClientDTO> getClients() {
        return clientService.getClients();
    }

    @PostMapping("/save")
    public ResponseEntity<ClientDTO> saveClient(
            @RequestBody ClientDTO clientDTO) {
        ClientDTO savedClient = clientService.saveClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PutMapping("/update")
    public ResponseEntity<ClientDTO> updateClient(
            @PathVariable long id,
            @RequestBody ClientDTO clientDTO) throws ClientNotFoundException {
        ClientDTO updatedClient = clientService.updateClient(id, clientDTO);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable long id) throws ClientNotFoundException {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable long id) throws ClientNotFoundException {
        ClientDTO clientDTO = clientService.getClientById(id);
        return ResponseEntity.ok(clientDTO);
    }
}

