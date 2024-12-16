package enset.ali.conferenceservice.DTOs;

import enset.ali.conferenceservice.enums.ConferenceType;
import lombok.Data;

import java.util.Date;

@Data
public class ConferenceDTO {
    private Long id;
    private String titre;
    private Date date;
    private double duree;
    private int nombreInscrits;
    private double score;
    private ConferenceType status;
}
