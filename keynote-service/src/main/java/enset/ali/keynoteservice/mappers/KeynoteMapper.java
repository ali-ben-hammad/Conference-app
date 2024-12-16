package enset.ali.keynoteservice.mappers;

import enset.ali.keynoteservice.DTOs.KeynoteDTO;
import enset.ali.keynoteservice.entities.Keynote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KeynoteMapper {
    KeynoteDTO toKeynoteDTO(Keynote keynote);
    Keynote toKeynote(KeynoteDTO keynoteDTO);
}

