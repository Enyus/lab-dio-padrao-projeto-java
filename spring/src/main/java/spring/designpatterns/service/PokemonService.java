package spring.designpatterns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {
    
    @Autowired
    private PokeApiService pokeApiService;

    public Object getPokemonByNumber(int number){
        return pokeApiService.getPokemonBase(number);
    }
}
