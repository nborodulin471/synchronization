package task1;

import java.util.ArrayList;
import java.util.List;

public class AutoDealer {
    private static final int TIME_BUY_CAR = 1000;
    private static final int TIME_WAIT_CAR = 5000;

    private final String name;
    private final List<Auto> autos = new ArrayList<>();
    private final List<AutoMaker> autoMakers;

    private int soldAuto = 0;

    public AutoDealer(String name, List<AutoMaker> autoMakers) {
        this.name = name;
        this.autoMakers = autoMakers;
    }

    public synchronized void acceptAuto() {
        System.out.println(name + " заказал авто");
        try {
            Auto auto = getRandomAutoMaker().produce();
            autos.add(auto);
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private AutoMaker getRandomAutoMaker() {
        return autoMakers.get((int) (Math.random() * autoMakers.size()));
    }

    public synchronized void sellAuto() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (autos.size() == 0) {
                System.out.println("Машин нет");
                wait(TIME_WAIT_CAR);
                if (autos.isEmpty()) {
                    System.out.println(Thread.currentThread().getName() + " не дождался и ушел");
                    return;
                }
            }
            Thread.sleep(TIME_BUY_CAR);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком " + autos.get(0).getName());
            autos.remove(0);
            soldAuto++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public int getSoldAuto() {
        return soldAuto;
    }
}
