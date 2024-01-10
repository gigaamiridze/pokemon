package com.workshop.pokemon.services.impl;

import com.workshop.pokemon.dto.ApiResponse;
import com.workshop.pokemon.dto.ReviewDto;
import com.workshop.pokemon.exceptions.PokemonNotFoundException;
import com.workshop.pokemon.models.Pokemon;
import com.workshop.pokemon.models.Review;
import com.workshop.pokemon.repositories.PokemonRepository;
import com.workshop.pokemon.repositories.ReviewRepository;
import com.workshop.pokemon.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ResponseEntity<ApiResponse> createReview(Long pokemonId, ReviewDto reviewDto) {
        try {
            Review review = mapToEntity(reviewDto);
            Pokemon pokemon = pokemonRepository.findById(pokemonId)
                    .orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
            review.setPokemon(pokemon);
            Review createdReview = reviewRepository.save(review);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, "Review created successfully", createdReview));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error creating review", null));
        }
    }

    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto(review.getId(), review.getTitle(), review.getContent(), review.getStar());
        return reviewDto;
    }

    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review(reviewDto.getTitle(), reviewDto.getContent(), reviewDto.getStar());
        return review;
    }
}
