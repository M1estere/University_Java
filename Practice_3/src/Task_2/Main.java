package Task_2;

import java.util.Random;

public class Main {
    /*
        В одном МФЦ работают три окна, которые обслуживают три категории граждан: молодые, пожилые и бизнесмены.
        Окна работают без очередей (каждый гражданин является отдельным потоком).
        Первое окно принимает любые категории граждан, второе - только вторую категорию, а третье - только третью.
        Гражданин идёт в случайное окно.
        Если гражданин не может получить услугу из-за занятости окна или неверной категории, то он разгневанно уходит.
        Определить процент ушедших клиентов из каждой категории.
     */

    public static void main(String[] args) {
        ServiceCenter serviceCenter = new ServiceCenter();
        int citizensAmount = 10;

        Thread[] citizensThreads = new Thread[citizensAmount];
        String[] citizensCategories = {"young", "old", "business"};

        for (int i = 0; i < citizensAmount; i++) {
            String category = citizensCategories[new Random().nextInt(citizensCategories.length)];
            citizensThreads[i] = new Thread(new Citizen(category, serviceCenter));
            citizensThreads[i].start();
        }

        for (Thread citizenThread : citizensThreads) {
            try {
                citizenThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        serviceCenter.printLeaves(citizensAmount);
    }
}
