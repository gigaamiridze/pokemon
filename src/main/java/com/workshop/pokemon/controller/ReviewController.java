package com.workshop.pokemon.controller;

import com.workshop.pokemon.dto.ApiResponse;
import com.workshop.pokemon.dto.ReviewDto;
import com.workshop.pokemon.service.impl.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewServiceImpl reviewService;

    @Autowired
    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<ApiResponse> getReviewById(@PathVariable Long pokemonId, @PathVariable Long reviewId) {
        return reviewService.getReviewById(pokemonId, reviewId);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews")
    public ResponseEntity<ApiResponse> getReviewsByPokemonId(@PathVariable Long pokemonId) {
        return reviewService.getReviewsByPokemonId(pokemonId);
    }

    @PostMapping("/pokemon/{pokemonId}/review")
    public ResponseEntity<ApiResponse> createReview(@PathVariable Long pokemonId, @RequestBody ReviewDto reviewDto) {
        return reviewService.createReview(pokemonId, reviewDto);
    }

    @PutMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<ApiResponse> updateReview(
            @PathVariable Long pokemonId,
            @PathVariable Long reviewId,
            @RequestBody ReviewDto reviewDto
            ) {
        return reviewService.updateReview(pokemonId, reviewId, reviewDto);
    }

    @DeleteMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Long pokemonId, @PathVariable Long reviewId) {
        return reviewService.deleteReview(pokemonId, reviewId);
    }
}
