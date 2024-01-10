package com.workshop.pokemon.mapper;

import com.workshop.pokemon.dto.ReviewDto;
import com.workshop.pokemon.model.Review;
import org.springframework.stereotype.Service;

@Service
public class ReviewMapper {
    public ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto(review.getId(), review.getTitle(), review.getContent(), review.getStar());
        return reviewDto;
    }

    public Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review(reviewDto.getTitle(), reviewDto.getContent(), reviewDto.getStar());
        return review;
    }
}
