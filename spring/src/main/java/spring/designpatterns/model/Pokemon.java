package spring.designpatterns.model;

import java.util.ArrayList;

// Improvável conseguir descrever o objeto da PokeAPI como um objeto java, então desisti de usar um model e, por enquanto, estou usando o tipo padrão Object.
// 05/10 - Vou tentar modelar o objeto que retornarei como JSON, com a ajuda do site https://json2csharp.com/code-converters/json-to-pojo

public class Pokemon {

    private int number;
    private String name;
    private ArrayList<String> types;
    private String imgUrl;
    private double height;
    private int weight;
    private ArrayList<String> abilities;
    private ArrayList<Stat> stats;
    private ArrayList<EvolutionChain> evolutionChain;

    // Constructor
    public Pokemon (
        int number,
        String name,
        ArrayList<String> types,
        String imgUrl,
        double height,
        int weight,
        ArrayList<String> abilities,
        ArrayList<Stat> stats,
        ArrayList<EvolutionChain> evolutionChain
    ){
        this.number = number;
        this.name = name;
        this.types = types;
        this.imgUrl = imgUrl;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
        this.stats = stats;
        this.evolutionChain = evolutionChain;
    };


    // Getters e Setters:

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ArrayList<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<String> abilities) {
        this.abilities = abilities;
    }

    public ArrayList<Stat> getStats() {
        return stats;
    }

    public void setStats(ArrayList<Stat> stats) {
        this.stats = stats;
    }

    public ArrayList<EvolutionChain> getEvolutionChain() {
        return evolutionChain;
    }

    public void setEvolutionChain(ArrayList<EvolutionChain> evolutionChain) {
        this.evolutionChain = evolutionChain;
    }

}
