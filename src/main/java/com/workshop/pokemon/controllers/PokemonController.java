package com.workshop.pokemon.controllers;

import com.workshop.pokemon.dto.PokemonDto;
import com.workshop.pokemon.services.impl.PokemonServiceImpl;
import com.workshop.pokemon.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse> getAllPokemon(
            @RequestParam(value = "pageNo", defaultValue = "", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "", required = false) int pageSize
    ) {
        return pokemonService.getAllPokemon(pageNo, pageSize);
    }

    @GetMapping("/{pokemonId}")
    public ResponseEntity<ApiResponse> getPokemonById(@PathVariable Long pokemonId) {
        return pokemonService.getPokemonById(pokemonId);
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> createPokemon(@RequestBody PokemonDto pokemonDto) {
        return pokemonService.createPokemon(pokemonDto);
    }

    @PutMapping("/{pokemonId}")
    public ResponseEntity<ApiResponse> updatePokemon(@PathVariable Long pokemonId, @RequestBody PokemonDto pokemonDto) {
        return pokemonService.updatePokemon(pokemonId, pokemonDto);
    }

    @DeleteMapping("/{pokemonId}")
    public ResponseEntity<ApiResponse> deletePokemonById(@PathVariable Long pokemonId) {
        return pokemonService.deletePokemonById(pokemonId);
    }
}
