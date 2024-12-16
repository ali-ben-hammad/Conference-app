package enset.ali.conferenceservice.mappers;

import enset.ali.conferenceservice.DTOs.ConferenceDTO;
import enset.ali.conferenceservice.entities.Conference;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConferenceMapper {
    ConferenceDTO toConferenceDTO(Conference conference);
    Conference toConference(ConferenceDTO conferenceDTO);
}
