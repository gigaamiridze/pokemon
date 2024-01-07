package com.workshop.pokemon.controllers;

import com.workshop.pokemon.models.Pokemon;
import com.workshop.pokemon.services.impl.PokemonServiceImpl;
import com.workshop.pokemon.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pokemon")
public class PokemonController {

    private final PokemonServiceImpl pokemonService;

    @Autowired
    public PokemonController(PokemonServiceImpl pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllPokemon() {
        return pokemonService.getAllPokemon();
    }

    @GetMapping("/{pokemonId}")
    public ResponseEntity<ApiResponse> getPokemonById(@PathVariable Long pokemonId) {
        return pokemonService.getPokemonById(pokemonId);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> createPokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.createPokemon(pokemon);
    }

    @PutMapping("/{pokemonId}")
    public ResponseEntity<ApiResponse> updatePokemon(@PathVariable Long pokemonId, @RequestBody Pokemon pokemonDetails) {
        return pokemonService.updatePokemon(pokemonId, pokemonDetails);
    }

    @DeleteMapping("/{pokemonId}")
    public ResponseEntity<ApiResponse> deletePokemonById(@PathVariable Long pokemonId) {
        return pokemonService.deletePokemonById(pokemonId);
    }
}
