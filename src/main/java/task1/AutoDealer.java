package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AutoDealer {
    private static final int TIME_BUY_CAR = 1000;
    private static final long TIME_WAIT_CAR = 5000;

    private final String name;
    private final List<Auto> autos = new ArrayList<>();
    private final List<AutoMaker> autoMakers;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private int soldAuto = 0;

    public AutoDealer(String name, List<AutoMaker> autoMakers) {
        this.name = name;
        this.autoMakers = autoMakers;
    }

    public void acceptAuto() {
        System.out.println(name + " заказал авто");
        try {
            lock.lock();
            Auto auto = getRandomAutoMaker().produce();
            autos.add(auto);
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    private AutoMaker getRandomAutoMaker() {
        return autoMakers.get((int) (Math.random() * autoMakers.size()));
    }

    public void sellAuto() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (autos.size() == 0) {
                System.out.println("Машин нет");
                condition.await(TIME_WAIT_CAR, TimeUnit.MILLISECONDS);
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
        }finally {
            lock.unlock();
        }
    }

    public String getName() {
        return name;
    }

    public int getSoldAuto() {
        return soldAuto;
    }
}
