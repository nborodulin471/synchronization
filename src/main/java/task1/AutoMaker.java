package task1;

import java.util.ArrayList;
import java.util.List;

public class AutoMaker {
    private String name;
    private List<String> models;
    private List<Auto> autos;

    public AutoMaker(String name, List<String> models) {
        this.name = name;
        this.models = models;
        this.autos = new ArrayList<Auto>();
    }

    public String getName() {
        return name;
    }

    public void addModel(String model) {
        models.add(model);
    }

    private String getRandomModel() {
        return models.get((int) (Math.random() * models.size()));
    }

    public Auto produce() {
        return new Auto(getRandomModel(), this);
    }

}
