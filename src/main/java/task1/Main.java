package task1;

import java.util.List;

public class Main {
    public static final int LIMIT_AUTOS = 10;
    public static final int WAIT_ITERATION = 4000;

    public static void main(String[] args) throws InterruptedException {
        AutoMaker toyota = new AutoMaker("Тойота", List.of("Крузак", "Камри"));
        AutoDealer autoDealer = new AutoDealer("Автодилер", List.of(toyota));

        while (autoDealer.getSoldAuto() < LIMIT_AUTOS) {
            new Thread(null, autoDealer::acceptAuto, autoDealer.getName()).start();
            new Thread(null, autoDealer::sellAuto, generateBuyer().getName()).start();
            new Thread(null, autoDealer::sellAuto, generateBuyer().getName()).start();
            Thread.sleep(WAIT_ITERATION);
        }
    }

    private static Buyer generateBuyer() {
        return new Buyer("Покупатель №" +  (int)(Math.random() * 100));
    }
}
