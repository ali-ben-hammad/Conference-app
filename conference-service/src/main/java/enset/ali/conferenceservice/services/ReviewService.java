package enset.ali.conferenceservice.services;

import enset.ali.conferenceservice.DTOs.ReviewDTO;
import enset.ali.conferenceservice.exceptions.ReviewNotFoundException;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getReviews();
    ReviewDTO getReviewById(long id) throws ReviewNotFoundException;
    void deleteReview(long id) throws ReviewNotFoundException;
}
