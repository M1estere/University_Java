package Task_3;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class Hospital {

    private BlockingQueue<Patient> patientsQueue = new LinkedBlockingQueue<>();
    private Semaphore therapistAvailableSem = new Semaphore(1);
    private Semaphore mrtAvailableSem = new Semaphore(1);

    private int maxQueueLength = 0;

    public int getMaxQueueLength() {
        return maxQueueLength;
    }

    public void addPatient(Patient patient) {
        patientsQueue.offer(patient);
        updateQueueLength();
    }

    private void updateQueueLength() {
        int currentLength = patientsQueue.size();
        maxQueueLength = Math.max(currentLength, maxQueueLength);
    }

    public void heal() throws InterruptedException {
        while (1 != 0) {
            Patient patient = patientsQueue.take();
            therapistAvailableSem.acquire();
            patient.run();

            Thread.sleep(5000);

            therapistAvailableSem.release(); // leaves therapist

            mrtAvailableSem.acquire();
            System.out.println(String.format("Patient %d is attending mrt", patient.getId()));
            Thread.sleep(7500);
            mrtAvailableSem.release();
        }
    }

}
