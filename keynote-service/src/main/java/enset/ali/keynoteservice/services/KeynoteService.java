package enset.ali.keynoteservice.services;

import enset.ali.keynoteservice.DTOs.KeynoteDTO;
import enset.ali.keynoteservice.exceptions.KeynoteNotFoundException;

import java.util.List;

public interface KeynoteService {
    List<KeynoteDTO> getKeynotes();
    KeynoteDTO getKeynoteById(long id) throws KeynoteNotFoundException;

    KeynoteDTO saveKeynote(KeynoteDTO keynoteDTO);

    void deleteKeynote(long id) throws KeynoteNotFoundException;
}
