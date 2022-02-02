package task1;

public class Auto {
    private String model;
    private AutoMaker automaker;

    public Auto(String model, AutoMaker automaker) {
        this.model = model;
        this.automaker = automaker;
    }

    public String getName() {
        return automaker.getName() + " " + model;
    }

}
