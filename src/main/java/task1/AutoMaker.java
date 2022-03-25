package task1;

import java.util.ArrayList;
import java.util.List;

public class AutoMaker {
    private static final int TIME_PRODUCE_CAR = 3000;

    private String name;
    private List<String> models;

    public AutoMaker(String name, List<String> models) {
        this.name = name;
        this.models = models;
    }

    public String getName() {
        return name;
    }

    private String getRandomModel() {
        return models.get((int) (Math.random() * models.size()));
    }

    public Auto produce() throws InterruptedException {
        Thread.sleep(TIME_PRODUCE_CAR);
        System.out.println("Производитель " + name + " выпустил 1 авто");
        return new Auto(getRandomModel(), this);
    }

}
