package enset.ali.conferenceservice.services;

import enset.ali.conferenceservice.DTOs.ConferenceDTO;
import enset.ali.conferenceservice.exceptions.ConferenceNotFoundException;

import java.util.List;

public interface ConferenceService {
    List<ConferenceDTO> getConferences();
    ConferenceDTO getConferenceById(long id) throws ConferenceNotFoundException;
    void deleteConference(long id) throws ConferenceNotFoundException;
}
