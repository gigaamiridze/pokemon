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
    public ResponseEntity<ApiResponse> getReviewById(Long pokemonId, Long reviewId) {
        try {
            Pokemon pokemon = pokemonRepository.findById(pokemonId)
                    .orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
            Review review = reviewRepository.findById(reviewId)
                    .orElseThrow(() -> new ReviewNotFoundException("Review with associated pokemon not found"));

            if (!review.getPokemon().getId().equals(pokemon.getId())) {
                throw new ReviewNotFoundException("This review does not belong to a pokemon");
            }

            return ResponseEntity.ok(new ApiResponse(true, "Review retrieved successfully", reviewMapper.mapToDto(review)));
        } catch (ReviewNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Error retrieving review with id: " + reviewId, null));
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
    public ResponseEntity<ApiResponse> updateReview(Long pokemonId, Long reviewId, ReviewDto reviewDto) {
        try {
            Pokemon pokemon = pokemonRepository.findById(pokemonId)
                    .orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
            Review review = reviewRepository.findById(reviewId)
                    .orElseThrow(() -> new ReviewNotFoundException("Review with associated pokemon not found"));

            if (!review.getPokemon().getId().equals(pokemon.getId())) {
                throw new ReviewNotFoundException("This review does not belong to a pokemon");
            }

            review.setTitle(reviewDto.getTitle());
            review.setContent(reviewDto.getContent());
            review.setStar(reviewDto.getStar());

            Review updatedReview = reviewRepository.save(review);

            return ResponseEntity.ok(new ApiResponse(true, "Review retrieved successfully", reviewMapper.mapToDto(updatedReview)));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Error updating review", null));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> deleteReview(Long pokemonId, Long reviewId) {
        try {
            Pokemon pokemon = pokemonRepository.findById(pokemonId)
                    .orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
            Review review = reviewRepository.findById(reviewId)
                    .orElseThrow(() -> new ReviewNotFoundException("Review with associated pokemon not found"));

            if (!review.getPokemon().getId().equals(pokemon.getId())) {
                throw new ReviewNotFoundException("This review does not belong to a pokemon");
            }

            reviewRepository.delete(review);

            return ResponseEntity.ok(new ApiResponse(true, "Review deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Error deleting review", null));
        }
    }
}
