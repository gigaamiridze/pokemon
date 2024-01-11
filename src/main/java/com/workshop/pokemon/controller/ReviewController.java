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

    @GetMapping("/pokemon/{pokemonId}/reviews")
    public ResponseEntity<ApiResponse> getReviewsByPokemonId(@PathVariable Long pokemonId) {
        return reviewService.getReviewsByPokemonId(pokemonId);
    }

    @PostMapping("/pokemon/{pokemonId}/review")
    public ResponseEntity<ApiResponse> createReview(@PathVariable Long pokemonId, @RequestBody ReviewDto reviewDto) {
        return reviewService.createReview(pokemonId, reviewDto);
    }
}
