package enset.ali.conferenceservice.controllers;

import enset.ali.conferenceservice.DTOs.ConferenceDTO;
import enset.ali.conferenceservice.exceptions.ConferenceNotFoundException;
import enset.ali.conferenceservice.services.ConferenceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/confrences")
@RestController
public class ConferenceRestController {
    private ConferenceService conferenceService;

    @GetMapping
    public List<ConferenceDTO> getConferences() {
        return conferenceService.getConferences();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConference(@PathVariable long id) throws ConferenceNotFoundException {
        conferenceService.deleteConference(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConferenceDTO> getTransferById(@PathVariable long id) throws ConferenceNotFoundException {
        ConferenceDTO conferenceDTO = conferenceService.getConferenceById(id);
        return ResponseEntity.ok(conferenceDTO);
    }
}

