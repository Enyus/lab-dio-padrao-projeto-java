package spring.designpatterns.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pokeapi", url = "https://pokeapi.co/api/v2")
public interface PokeApiService {
    
    @GetMapping("/pokemon/{id}")
    Object getPokemonBase(@PathVariable("id") String pokemonIdentifier);

    @GetMapping("pokemon-species/{species}")
    Object getPokemonSpecies(@PathVariable("species") String pokemonSpecies);

    @GetMapping("evolution-chain/{id}")
    Object getEvolutionChain(@PathVariable("id") String evolutionChainIdentifier);
}
