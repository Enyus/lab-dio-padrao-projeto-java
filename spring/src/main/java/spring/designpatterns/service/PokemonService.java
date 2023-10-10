package spring.designpatterns.service;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import spring.designpatterns.model.EvolutionChain;
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

        // Pegar as evoluções do pokemon
        ArrayList<EvolutionChain> evolutionChain = new ArrayList<EvolutionChain>();

        String numberString = String.valueOf(number);
        Object speciesJsonObject = gson.toJson(pokeApiService.getPokemonSpecies(numberString));
        String speciesJsonString = speciesJsonObject.toString();
        JSONObject pokeSpeciesApiResult = new JSONObject(speciesJsonString);
        JSONObject evoChainApiObject = pokeSpeciesApiResult.getJSONObject("evolution_chain");
        String evoChainUrl = evoChainApiObject.getString("url");
        String evoChainIdWithBar = evoChainUrl.replace("https://pokeapi.co/api/v2/evolution-chain/", "");
        String evoChainId = evoChainIdWithBar.replace("/", "");

        Object evoChainJsonObject = gson.toJson(pokeApiService.getEvolutionChain(evoChainId));
        String evoChainJsonString = evoChainJsonObject.toString();
        JSONObject evoChainApiResult = new JSONObject(evoChainJsonString);
        JSONObject evoChainChain = evoChainApiResult.getJSONObject("chain");

        JSONObject evoChainSpecies = evoChainChain.getJSONObject("species");
        String basePokemonName = evoChainSpecies.getString("name");

        Object jsonObjectBasePokemon = gson.toJson(pokeApiService.getPokemonBase(basePokemonName));
        String jsonStringBasePokemon = jsonObjectBasePokemon.toString();
        JSONObject pokeApiResultBasePokemon = new JSONObject(jsonStringBasePokemon);
        JSONObject spritesBasePokemon = pokeApiResultBasePokemon.getJSONObject("sprites");
        JSONObject otherSpritesBasePokemon = spritesBasePokemon.getJSONObject("other");
        JSONObject dreamWorldBasePokemon = otherSpritesBasePokemon.getJSONObject("dream_world");
        String imgUrlBasePokemon = dreamWorldBasePokemon.getString("front_default");

        JSONArray evolvesTo = evoChainChain.getJSONArray("evolves_to");
        if(evolvesTo.length() > 0) {
            JSONObject firstEvo = evolvesTo.getJSONObject(0);
            JSONObject firstEvoSpecies = firstEvo.getJSONObject("species");
            String firstEvoName = firstEvoSpecies.getString("name");

            Object jsonObjectFirstEvo = gson.toJson(pokeApiService.getPokemonBase(firstEvoName));
            String jsonStringFirstEvo = jsonObjectFirstEvo.toString();
            JSONObject pokeApiResultFirstEvo = new JSONObject(jsonStringFirstEvo);
            JSONObject spritesFirstEvo = pokeApiResultFirstEvo.getJSONObject("sprites");
            JSONObject otherSpritesFirstEvo = spritesFirstEvo.getJSONObject("other");
            JSONObject dreamWorldFirstEvo = otherSpritesFirstEvo.getJSONObject("dream_world");
            String imgUrlFirstEvo = dreamWorldFirstEvo.getString("front_default");
            EvolutionChain firstEvoChain = new EvolutionChain(basePokemonName, imgUrlBasePokemon, firstEvoName, imgUrlFirstEvo);
            evolutionChain.add(firstEvoChain);

            JSONArray secondEvolvesTo = firstEvo.getJSONArray("evolves_to");
            if(secondEvolvesTo.length() > 0) {
                JSONObject secondEvo = secondEvolvesTo.getJSONObject(0);
                JSONObject secondEvoSpecies = secondEvo.getJSONObject("species");
                String secondEvoName = secondEvoSpecies.getString("name");

                Object jsonObjectSecondEvo = gson.toJson(pokeApiService.getPokemonBase(secondEvoName));
                String jsonStringSecondEvo = jsonObjectSecondEvo.toString();
                JSONObject pokeApiResultSecondEvo = new JSONObject(jsonStringSecondEvo);
                JSONObject spritesSecondEvo = pokeApiResultSecondEvo.getJSONObject("sprites");
                JSONObject otherSpritesSecondEvo = spritesSecondEvo.getJSONObject("other");
                JSONObject dreamWorldSecondEvo = otherSpritesSecondEvo.getJSONObject("dream_world");
                String imgUrlSecondEvo = dreamWorldSecondEvo.getString("front_default");
                EvolutionChain SecondEvoChain = new EvolutionChain(firstEvoName, imgUrlFirstEvo, secondEvoName, imgUrlSecondEvo);
                evolutionChain.add(SecondEvoChain);
            }
        }

        // Agrupando em um só objeto:
        Pokemon pokemon = new Pokemon(number, name, types, imgUrl, height, weight, abilities, stats, evolutionChain);
        
        return pokemon;
    }
}
