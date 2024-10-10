package Task_3;

public class Patient implements Runnable {

    private int id;

    public Patient(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        System.out.println(String.format("Patient %d is waiting for terapeut", id));
    }

}
