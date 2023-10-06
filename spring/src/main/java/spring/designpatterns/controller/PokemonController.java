package spring.designpatterns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.designpatterns.service.PokemonService;

@RestController
@RequestMapping("pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/{number}")
    public ResponseEntity<Object> getPokemonByNumber(@PathVariable("number") String pokemonIdentifier){
        return ResponseEntity.ok(pokemonService.getPokemonByNumber(pokemonIdentifier));
    }
    
}
