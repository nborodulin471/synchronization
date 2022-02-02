package task1;

import java.util.List;

public class AutoDealer {
    private static final int TIME_PRODUCE_CAR = 3000;
    private static final int TIME_BUY_CAR = 1000;

    private String name;
    private List<AutoMaker> autoMakers;
    private List<Auto> autos;

    public AutoDealer(String name, List<AutoMaker> autoMakers, List<Auto> autos) {
        this.name = name;
        this.autoMakers = autoMakers;
        this.autos = autos;
    }

    public synchronized void acceptAuto() {
        try {
            Auto auto = getRandomAutoMaker().produce();
            System.out.println("Автодилер: " + Thread.currentThread().getName() +
                    ". Привез новую машину " + auto.getName());
            autos.add(auto);
            Thread.sleep(TIME_PRODUCE_CAR);
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
            System.out.println("Покупатель: " + Thread.currentThread().getName() + ". Пытаюсь купить машину");
            while (autos.size() == 0) {
                System.out.println("Покупатель: " + Thread.currentThread().getName() + ". Не могу купить машину");
                wait();
            }
            Thread.sleep(TIME_BUY_CAR);
            System.out.println("Покупатель: " + Thread.currentThread().getName() + ". Купил Машину "
                    + autos.get(0).getName());
            autos.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}
