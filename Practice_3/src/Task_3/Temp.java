//package Task_3;
//
//import java.util.Random;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.Semaphore;
//
//public class Main {
//    /*
//        В поликлинике все пациенты принимаются двумя кабинетами (терапевт и МРТ) с единой (одной) очередью.
//        Когда пациент выходит из очереди, он сначала принимается терапевтом.
//        Как только осмотр заканчивается, пациент отправляется в кабинет МРТ, а к терапевту поступает следующий пациент, если очередь не пустая.
//        Пациент у терапевта ждёт окончания обследования предыдущего пациента на МРТ, прежде чем выйти.
//        Определить максимальную длину очереди из пациентов.
//     */
//
//    public static void main(String[] args) {
//        Hospital hospital = new Hospital();
//        Thread hospitalThread = new Thread(() -> {
//            try {
//                hospital.heal();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        hospitalThread.start();
//
//        for (int i = 0; i < 10; i++) {
//            Patient patient = new Patient(new Random().nextInt(5000));
//            hospital.addPatient(patient);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        try {
//            hospitalThread.join();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(String.format("Max queue length: %d", hospital.getMaxQueueLength()));
//    }
//}
//
//public class Hospital {
//
//    private BlockingQueue<Patient> patientsQueue = new LinkedBlockingQueue<>();
//    private Semaphore therapistAvailableSem = new Semaphore(1);
//    private Semaphore mrtAvailableSem = new Semaphore(1);
//
//    private int maxQueueLength = 0;
//
//    public int getMaxQueueLength() {
//        return maxQueueLength;
//    }
//
//    public void addPatient(Patient patient) {
//        patientsQueue.offer(patient);
//        updateQueueLength();
//    }
//
//    private void updateQueueLength() {
//        int currentLength = patientsQueue.size();
//        maxQueueLength = Math.max(currentLength, maxQueueLength);
//    }
//
//    public void heal() throws InterruptedException {
//        while (1 != 0) {
//            Patient patient = patientsQueue.take();
//            therapistAvailableSem.acquire();
//            patient.run();
//
//            Thread.sleep(5000);
//
//            therapistAvailableSem.release(); // leaves therapist
//
//            mrtAvailableSem.acquire();
//            System.out.println(String.format("Patient %d is attending MRT", patient.getId()));
//            Thread.sleep(7500);
//            mrtAvailableSem.release();
//        }
//    }
//
//}
//
//public class Patient implements Runnable {
//
//    private int id;
//
//    public Patient(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    @Override
//    public void run() {
//        System.out.println(String.format("Patient %d is attending therapist", id));
//    }
//
//}
