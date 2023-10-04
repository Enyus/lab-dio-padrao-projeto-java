package spring.designpatterns.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// Improvável conseguir descrever o objeto da PokeAPI como um objeto java, então desisti de usar um model e, por enquanto, estou usando o tipo padrão Object.

@Entity
public class Pokemon {
    @Id
    private int number;
    private String name;
    private List<String> types;
    private String imgUrl;

    public Pokemon (){};

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

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
