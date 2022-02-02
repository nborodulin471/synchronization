package task1;

public class Buyer {
    private final String FIRST_NAME;
    private final String LAST_NAME;

    public Buyer(String FIRST_NAME, String LAST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
    }

    public String getName() {
        return FIRST_NAME + " " + LAST_NAME;
    }

}
