package task1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final int LIMIT_AUTOS = 10;

    public static void main(String[] args) throws InterruptedException {
        int autoSold = 0;

        AutoMaker autoMaker1 = new AutoMaker("Запорожец", List.of("Ушастый", "Горабатый"));
        AutoMaker autoMaker2 = new AutoMaker("Москвич", List.of("412", "2140"));
        List<AutoMaker> autoMakers = List.of(autoMaker1, autoMaker2);
        AutoDealer autoDealer = new AutoDealer("Авто без допов", autoMakers, new ArrayList<>());
        Buyer buyer1 = new Buyer("Василий", "Степаненко");
        Buyer buyer2 = new Buyer("Маргарита", "Василенко");

        ThreadGroup threadBuyers = new ThreadGroup("Покупатели");
        ThreadGroup threadAutoMaker = new ThreadGroup("Автодилер");
        while (autoSold < LIMIT_AUTOS) {
            new Thread(threadBuyers, autoDealer::sellAuto, buyer1.getName()).start();
            new Thread(threadBuyers, autoDealer::sellAuto, buyer2.getName()).start();

            Thread thread = new Thread(threadAutoMaker, autoDealer::acceptAuto, autoDealer.getName());
            thread.start();
            thread.join();
            autoSold++;
        }
        threadBuyers.interrupt();
        threadAutoMaker.interrupt();
    }
}
