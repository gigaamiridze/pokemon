package com.workshop.pokemon.services.impl;

import com.workshop.pokemon.exceptions.PokemonNotFoundException;
import com.workshop.pokemon.models.Pokemon;
import com.workshop.pokemon.repositories.PokemonRepository;
import com.workshop.pokemon.services.PokemonService;
import com.workshop.pokemon.utils.ApiResponse;
import com.workshop.pokemon.utils.PokemonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ResponseEntity<ApiResponse> getAllPokemon(int pageNo, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);

            PokemonResponse pokemonResponse = new PokemonResponse();

            pokemonResponse.setContent(pokemons.getContent());
            pokemonResponse.setPageNo(pokemons.getNumber());
            pokemonResponse.setPageSize(pokemons.getSize());
            pokemonResponse.setTotalPages(pokemons.getTotalPages());
            pokemonResponse.setTotalElements(pokemons.getTotalElements());
            pokemonResponse.setLast(pokemons.isLast());

            return ResponseEntity.ok(new ApiResponse(true, "Pokemons retrieved successfully", pokemonResponse));
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
                    .body(new ApiResponse(false, "Error retrieving pokemon with id: " + pokemonId, null));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> createPokemon(Pokemon pokemon) {
        try {
            Pokemon createdPokemon = pokemonRepository.save(pokemon);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse(true, "Pokemon created successfully", createdPokemon));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error creating pokemon", null));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> updatePokemon(Long pokemonId, Pokemon pokemonDetails) {
        try {
            Pokemon pokemon = pokemonRepository.findById(pokemonId)
                    .orElseThrow(() -> new PokemonNotFoundException("Pokemon not found with id: " + pokemonId));

            pokemon.setName(pokemonDetails.getName());
            pokemon.setType(pokemonDetails.getType());

            Pokemon updatedPokemon = pokemonRepository.save(pokemon);
            return ResponseEntity.ok(new ApiResponse(true, "Pokemon updated successfully", updatedPokemon));
        } catch (PokemonNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Error updating pokemon with id: " + pokemonId, null));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> deletePokemonById(Long pokemonId) {
        try {
            Pokemon pokemon = pokemonRepository.findById(pokemonId)
                    .orElseThrow(() -> new PokemonNotFoundException("Pokemon not found with id: " + pokemonId));
            pokemonRepository.delete(pokemon);

            return ResponseEntity.ok(new ApiResponse(true, "Pokemon deleted successfully", pokemon));
        } catch (PokemonNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Error deleting pokemon with id: " + pokemonId, null));
        }
    }
}
