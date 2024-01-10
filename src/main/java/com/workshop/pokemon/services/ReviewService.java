package com.workshop.pokemon.services;

import com.workshop.pokemon.dto.ApiResponse;
import com.workshop.pokemon.dto.ReviewDto;
import org.springframework.http.ResponseEntity;

public interface ReviewService {
    ResponseEntity<ApiResponse> createReview(Long pokemonId, ReviewDto reviewDto);
}
