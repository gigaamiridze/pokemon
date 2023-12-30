package com.workshop.pokemon.services.impl;

import com.workshop.pokemon.dto.PokemonDto;
import com.workshop.pokemon.exceptions.PokemonNotFoundException;
import com.workshop.pokemon.models.Pokemon;
import com.workshop.pokemon.repositories.PokemonRepository;
import com.workshop.pokemon.services.PokemonService;
import com.workshop.pokemon.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ResponseEntity<ApiResponse> getAllPokemon() {
        try {
            List<Pokemon> pokemons = pokemonRepository.findAll();
            return ResponseEntity.ok(new ApiResponse(true, "Pokemons retrieved successfully", pokemons));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error retrieving pokemons", null));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getPokemonById(Long pokemonId) {
        try {
            Pokemon pokemon = pokemonRepository.findById(pokemonId)
                    .orElseThrow(() -> new PokemonNotFoundException("Pokemon not found with id: " + pokemonId));

            return ResponseEntity.ok(new ApiResponse(true, "Pokemon retrieved successfully", pokemon));
        } catch (PokemonNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Error retrieving todo with id: " + pokemonId, null));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> createPokemon(PokemonDto pokemonDto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> updatePokemon(Long pokemonId, PokemonDto pokemonDto) {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponse> deletePokemonById(Long pokemonId) {
        return null;
    }
}