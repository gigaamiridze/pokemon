package com.workshop.pokemon.controllers;

import com.workshop.pokemon.services.impl.PokemonServiceImpl;
import com.workshop.pokemon.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPokemonById(@PathVariable("id") Long pokemonId) {
        return pokemonService.getPokemonById(pokemonId);
    }
}
