package spring.designpatterns.model;

public class EvolutionChain {
    private String name;
    private String evolvesTo;
    private String imgUrl;

    public EvolutionChain(String name, String evolvesTo, String imgUrl) {
        this.name = name;
        this.evolvesTo = evolvesTo;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvolvesTo() {
        return evolvesTo;
    }
    
    public void setEvolvesTo(String evolvesTo) {
        this.evolvesTo = evolvesTo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

}
