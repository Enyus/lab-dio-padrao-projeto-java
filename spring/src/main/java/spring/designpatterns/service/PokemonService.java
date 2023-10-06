package spring.designpatterns.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import spring.designpatterns.model.Pokemon;
import spring.designpatterns.model.Stat;

@Service
public class PokemonService {
    
    @Autowired
    private PokeApiService pokeApiService;

    @Autowired
    private Gson gson;

    public Object getPokemonByNumber(String pokemonIdentifier){
        // Resultado da API transformado num Object:
        Object jsonObject = gson.toJson(pokeApiService.getPokemonBase(pokemonIdentifier));
        String jsonString = jsonObject.toString();
        JSONObject pokeApiResult = new JSONObject(jsonString);

        // Pegando o número do pokemon
        int number = pokeApiResult.getInt("id");
        
        // Pegando o nome do pokemon:
        String name = pokeApiResult.getString("name");
        
        // Pegando os tipos do pokemon:
        JSONArray typesApi = pokeApiResult.getJSONArray("types");
        ArrayList<String> types = new ArrayList<String>();
        for(int i = 0; i < typesApi.length(); i++) {
            JSONObject innerTypeObject = new JSONObject(typesApi.get(i).toString());
            JSONObject innerTypeObjectDescription = innerTypeObject.getJSONObject("type");
            String effectiveType = innerTypeObjectDescription.getString("name");
            types.add(effectiveType);
        };

        // Pegando a imagem do pokemon:
        JSONObject sprites = pokeApiResult.getJSONObject("sprites");
        JSONObject otherSprites = sprites.getJSONObject("other");
        JSONObject dreamWorld = otherSprites.getJSONObject("dream_world");
        String imgUrl = dreamWorld.getString("front_default");

        // Pegando a altura do pokemon:
        int height = pokeApiResult.getInt("height");

        // Pegando o peso do pokemon:
        int weight = pokeApiResult.getInt("weight");

        // Pegando as habilidades do pokemon:
        ArrayList<String> abilities = new ArrayList<String>();
        JSONArray abilitiesAPI = pokeApiResult.getJSONArray("abilities");
        for(int i = 0; i < abilitiesAPI.length() ; i++){
            JSONObject innerAbilityObject = new JSONObject(abilitiesAPI.get(i).toString());
            JSONObject effectiveAbility = innerAbilityObject.getJSONObject("ability");
            String ability = effectiveAbility.getString("name");
            abilities.add(ability);
        }

        // Pegando os Stats do Pokemon:
        ArrayList<Stat> stats = new ArrayList<Stat>();
        JSONArray statsApi = pokeApiResult.getJSONArray("stats");
        for(int i = 0; i < statsApi.length(); i++){
            JSONObject statApi = new JSONObject(statsApi.get(i).toString());
            int statValue = statApi.getInt("base_stat");
            JSONObject statApiInnerObject = statApi.getJSONObject("stat");
            String statName = statApiInnerObject.getString("name");
            Stat stat = new Stat(statName, statValue);
            stats.add(stat);
        }

        // TODO Pegar as evoluções do pokemon


        // Agrupando em um só objeto:
        Pokemon pokemon = new Pokemon(number, name, types, imgUrl, height, weight, abilities, stats);
        
        return pokemon;
    }
}
