package Task_1;

import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    /*
        Создать два класса, реализующих стандартный интерфейс Runnable.
        Полем классов будет переменная типа CopyOnWriteArrayList<int> listOfNumbers.
        Первый класс будет читать из переменной и выводить на экран, а второй класс будет в неё записывать числа.
        Сделать таким образом, чтобы чтение и запись выполнялись с небольшой задержкой.
     */

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> listOfNumbers = new CopyOnWriteArrayList<>();

        Thread writerClassThread = new Thread(new Writer(listOfNumbers));
        Thread readerClassThread = new Thread(new Reader(listOfNumbers));

        writerClassThread.start();
        readerClassThread.start();
    }
}