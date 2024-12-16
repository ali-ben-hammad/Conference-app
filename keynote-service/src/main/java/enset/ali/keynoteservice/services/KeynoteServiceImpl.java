package enset.ali.keynoteservice.services;

import enset.ali.keynoteservice.DTOs.KeynoteDTO;
import enset.ali.keynoteservice.entities.Keynote;
import enset.ali.keynoteservice.exceptions.KeynoteNotFoundException;
import enset.ali.keynoteservice.mappers.KeynoteMapper;
import enset.ali.keynoteservice.repositories.KeynoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class KeynoteServiceImpl implements KeynoteService {
    private KeynoteMapper keynoteMapper;
    private KeynoteRepository keynoteRepository;


    @Override
    public List<KeynoteDTO> getKeynotes() {
        return keynoteRepository.findAll()
                .stream()
                .map(keynoteMapper::toKeynoteDTO)
                .toList();
    }

    @Override
    public KeynoteDTO getKeynoteById(long id) throws KeynoteNotFoundException {
        return keynoteMapper.toKeynoteDTO(keynoteRepository.findById(id).orElseThrow(() -> new KeynoteNotFoundException("Keynote with ID " + id + " not found")));
    }

    @Override
    public KeynoteDTO saveKeynote(KeynoteDTO keynoteDTO) {
        Keynote keynote = keynoteMapper.toKeynote(keynoteDTO);
        return keynoteMapper.toKeynoteDTO(keynoteRepository.save(keynote));
    }


    @Override
    public void deleteKeynote(long id) throws KeynoteNotFoundException {
        log.info("deleting Keynote with id: {}", id);
        if(!keynoteRepository.existsById(id)) throw new KeynoteNotFoundException("Keynote not found");
        keynoteRepository.deleteById(id);
    }
}
