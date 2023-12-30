package com.workshop.pokemon.repositories;

import com.workshop.pokemon.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Optional<Pokemon> findByName(String name);
    Optional<Pokemon> findByType(String type);
}
