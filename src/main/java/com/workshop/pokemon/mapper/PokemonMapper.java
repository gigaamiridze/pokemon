package com.workshop.pokemon.mapper;

import com.workshop.pokemon.dto.PokemonDto;
import com.workshop.pokemon.model.Pokemon;
import org.springframework.stereotype.Service;

@Service
public class PokemonMapper {
    public PokemonDto mapToDto(Pokemon pokemon) {
        PokemonDto pokemonDto = new PokemonDto(pokemon.getId(), pokemon.getName(), pokemon.getType());
        return pokemonDto;
    }

    public Pokemon mapToEntity(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon(pokemonDto.getName(), pokemonDto.getType());
        return pokemon;
    }
}
