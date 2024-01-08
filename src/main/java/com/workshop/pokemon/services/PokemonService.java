package com.workshop.pokemon.services;

import com.workshop.pokemon.models.Pokemon;
import com.workshop.pokemon.utils.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface PokemonService {
    ResponseEntity<ApiResponse> getAllPokemon(int pageNo, int pageSize);
    ResponseEntity<ApiResponse> getPokemonById(Long pokemonId);
    ResponseEntity<ApiResponse> createPokemon(Pokemon pokemon);
    ResponseEntity<ApiResponse> updatePokemon(Long pokemonId, Pokemon pokemon);
    ResponseEntity<ApiResponse> deletePokemonById(Long pokemonId);
}
