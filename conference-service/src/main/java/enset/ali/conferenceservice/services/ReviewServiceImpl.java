package enset.ali.conferenceservice.services;

import enset.ali.conferenceservice.DTOs.ReviewDTO;
import enset.ali.conferenceservice.entities.Review;
import enset.ali.conferenceservice.exceptions.ReviewNotFoundException;
import enset.ali.conferenceservice.fiegn.KeynoteRestClient;
import enset.ali.conferenceservice.mappers.ReviewMapper;
import enset.ali.conferenceservice.repositories.ReviewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private ReviewMapper reviewMapper;
    private final KeynoteRestClient keynoteRestClient;
    @Override
    public List<ReviewDTO> getReviews() {
        List<ReviewDTO> reviewDTOS = reviewRepository
                .findAll().stream()
                .map(reviewMapper::toReviewDTO)
                .toList();
        return reviewDTOS;
    }

    @Override
    public ReviewDTO getReviewById(long id) throws ReviewNotFoundException {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Transfer with ID " + id + " not found"));
        return reviewMapper.toReviewDTO(review);
    }

    @Override
    public void deleteReview(long id) throws ReviewNotFoundException {
        log.info("deleting transfer with id: {}", id);
        if (!reviewRepository.existsById(id)) throw new ReviewNotFoundException("Review with ID " + id + " not found");
        reviewRepository.deleteById(id);
    }
}
