package spring.designpatterns.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pokeapi", url = "https://pokeapi.co/api/v2/pokemon")
public interface PokeApiService {
    
    @GetMapping("/{id}")
    Object getPokemonBase(@PathVariable("id") int pokemonNumber);

}
