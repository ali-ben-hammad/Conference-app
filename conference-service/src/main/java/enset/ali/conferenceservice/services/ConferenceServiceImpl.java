package enset.ali.conferenceservice.services;

import enset.ali.conferenceservice.DTOs.ConferenceDTO;
import enset.ali.conferenceservice.entities.Conference;
import enset.ali.conferenceservice.exceptions.ConferenceNotFoundException;
import enset.ali.conferenceservice.fiegn.KeynoteRestClient;
import enset.ali.conferenceservice.mappers.ConferenceMapper;
import enset.ali.conferenceservice.repositories.ConferenceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ConferenceServiceImpl implements ConferenceService {
    private ConferenceRepository conferenceRepository;
    private ConferenceMapper conferenceMapper;
    private final KeynoteRestClient keynoteRestClient;
    @Override
    public List<ConferenceDTO> getConferences() {
        List<ConferenceDTO> conferenceDTOS = conferenceRepository
                .findAll().stream()
                .map(conferenceMapper::toConferenceDTO)
                .toList();
        return conferenceDTOS;
    }

    @Override
    public ConferenceDTO getConferenceById(long id) throws ConferenceNotFoundException {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() -> new ConferenceNotFoundException("Transfer with ID " + id + " not found"));
        return conferenceMapper.toConferenceDTO(conference);
    }

    @Override
    public void deleteConference(long id) throws ConferenceNotFoundException {
        log.info("deleting transfer with id: {}", id);
        if (!conferenceRepository.existsById(id)) throw new ConferenceNotFoundException("Transfer with ID " + id + " not found");
        conferenceRepository.deleteById(id);
    }
}
