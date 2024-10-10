package Task_1;

import java.util.concurrent.CopyOnWriteArrayList;

public class Reader implements Runnable {

    private CopyOnWriteArrayList<Integer> listOfNumbers;

    public Reader(CopyOnWriteArrayList<Integer> listOfNumbers) {
        this.listOfNumbers = listOfNumbers;
    }


    @Override
    public void run() {
        while (1 != 0) {
            for (Integer num : listOfNumbers) {
                System.out.println("Number: " + num);
            }

            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
