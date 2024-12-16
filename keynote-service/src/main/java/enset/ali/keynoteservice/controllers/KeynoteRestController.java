package enset.ali.keynoteservice.controllers;

import enset.ali.keynoteservice.DTOs.KeynoteDTO;
import enset.ali.keynoteservice.exceptions.KeynoteNotFoundException;
import enset.ali.keynoteservice.services.KeynoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/keynotes")
@RestController
public class KeynoteRestController {
    private KeynoteService keynoteService;

    @GetMapping
    public List<KeynoteDTO> getKeynotes() {
        return keynoteService.getKeynotes();
    }

    @PostMapping("/save")
    public ResponseEntity<KeynoteDTO> saveKeynote(
            @RequestBody KeynoteDTO keynoteDTO){
        KeynoteDTO savedKeynote = keynoteService.saveKeynote(keynoteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedKeynote);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteKeynote(@PathVariable long id) throws KeynoteNotFoundException {
        keynoteService.deleteKeynote(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeynoteDTO> getKeynoteById(@PathVariable long id) throws KeynoteNotFoundException {
        KeynoteDTO keynoteDTO = keynoteService.getKeynoteById(id);
        return ResponseEntity.ok(keynoteDTO);
    }
}

