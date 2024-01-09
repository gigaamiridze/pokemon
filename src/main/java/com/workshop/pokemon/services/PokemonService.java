package com.workshop.pokemon.services;

import com.workshop.pokemon.dto.PokemonDto;
import com.workshop.pokemon.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface PokemonService {
    ResponseEntity<ApiResponse> getAllPokemon(int pageNo, int pageSize);
    ResponseEntity<ApiResponse> getPokemonById(Long pokemonId);
    ResponseEntity<ApiResponse> createPokemon(PokemonDto pokemonDto);
    ResponseEntity<ApiResponse> updatePokemon(Long pokemonId, PokemonDto pokemonDto);
    ResponseEntity<ApiResponse> deletePokemonById(Long pokemonId);
}
