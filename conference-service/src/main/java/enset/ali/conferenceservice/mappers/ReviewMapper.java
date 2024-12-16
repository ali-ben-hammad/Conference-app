package enset.ali.conferenceservice.mappers;

import enset.ali.conferenceservice.DTOs.ReviewDTO;
import enset.ali.conferenceservice.entities.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDTO toReviewDTO(Review review);
    Review toReview(ReviewDTO reviewDTO);
}
