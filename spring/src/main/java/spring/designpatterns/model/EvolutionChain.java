package spring.designpatterns.model;

public class EvolutionChain {
    private String name;
    private String img;
    private String evolvesTo;
    private String imgEvo;

    public EvolutionChain(String name, String img, String evolvesTo, String imgEvo) {
        this.name = name;
        this.img = img;
        this.evolvesTo = evolvesTo;
        this.imgEvo = imgEvo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getEvolvesTo() {
        return evolvesTo;
    }
    
    public void setEvolvesTo(String evolvesTo) {
        this.evolvesTo = evolvesTo;
    }

    public String getImgEvo() {
        return imgEvo;
    }

    public void setImgEvo(String imgEvo) {
        this.imgEvo = imgEvo;
    }

}
