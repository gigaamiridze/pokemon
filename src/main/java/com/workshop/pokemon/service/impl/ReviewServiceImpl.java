package com.workshop.pokemon.service.impl;

import com.workshop.pokemon.dto.ApiResponse;
import com.workshop.pokemon.dto.ReviewDto;
import com.workshop.pokemon.exception.PokemonNotFoundException;
import com.workshop.pokemon.exception.ReviewNotFoundException;
import com.workshop.pokemon.mapper.ReviewMapper;
import com.workshop.pokemon.model.Pokemon;
import com.workshop.pokemon.model.Review;
import com.workshop.pokemon.repository.PokemonRepository;
import com.workshop.pokemon.repository.ReviewRepository;
import com.workshop.pokemon.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final PokemonRepository pokemonRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public ResponseEntity<ApiResponse> createReview(Long pokemonId, ReviewDto reviewDto) {
        try {
            Review review = reviewMapper.mapToEntity(reviewDto);
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

    @Override
    public ResponseEntity<ApiResponse> getReviewsByPokemonId(Long pokemonId) {
        try {
            List<Review> reviews = reviewRepository.findByPokemonId(pokemonId);
            List<ReviewDto> reviewDtos = reviews.stream().map(review -> reviewMapper.mapToDto(review)).collect(Collectors.toList());
            return ResponseEntity.ok(new ApiResponse(true, "Reviews retrieved successfully", reviewDtos));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error retrieving reviews", null));
        }
    }
}
