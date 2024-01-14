package com.workshop.pokemon.service;

import com.workshop.pokemon.dto.ApiResponse;
import com.workshop.pokemon.dto.ReviewDto;
import org.springframework.http.ResponseEntity;

public interface ReviewService {
    ResponseEntity<ApiResponse> getReviewById(Long pokemonId, Long reviewId);
    ResponseEntity<ApiResponse> getReviewsByPokemonId(Long pokemonId);
    ResponseEntity<ApiResponse> createReview(Long pokemonId, ReviewDto reviewDto);
    ResponseEntity<ApiResponse> updateReview(Long pokemonId, Long reviewId, ReviewDto reviewDto);
    ResponseEntity<ApiResponse> deleteReview(Long pokemonId, Long reviewId);
}
