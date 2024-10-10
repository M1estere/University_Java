package Task_3;

import java.util.Random;

public class Main {
    /*
        В поликлинике все пациенты принимаются двумя кабинетами (терапевт и МРТ) с единой (одной) очередью.
        Когда пациент выходит из очереди, он сначала принимается терапевтом.
        Как только осмотр заканчивается, пациент отправляется в кабинет МРТ, а к терапевту поступает следующий пациент, если очередь не пустая.
        Пациент у терапевта ждёт окончания обследования предыдущего пациента на МРТ, прежде чем выйти.
        Определить максимальную длину очереди из пациентов.
     */

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        Thread hospitalThread = new Thread(() -> {
            try {
                hospital.heal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        hospitalThread.start();

        for (int i = 0; i < 10; i++) {
            Patient patient = new Patient(i);
            hospital.addPatient(patient);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            hospitalThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(String.format("Max queue length: %d", hospital.getMaxQueueLength()));
    }
}
