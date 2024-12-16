package enset.ali.conferenceservice.controllers;

import enset.ali.conferenceservice.DTOs.ReviewDTO;
import enset.ali.conferenceservice.exceptions.ReviewNotFoundException;
import enset.ali.conferenceservice.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/reviews")
@RestController
public class ReviewRestController {
    private ReviewService reviewService;

    @GetMapping
    public List<ReviewDTO> getReviews() {
        return reviewService.getReviews();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable long id) throws ReviewNotFoundException {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getTransferById(@PathVariable long id) throws ReviewNotFoundException {
        ReviewDTO reviewDTO = reviewService.getReviewById(id);
        return ResponseEntity.ok(reviewDTO);
    }
}

