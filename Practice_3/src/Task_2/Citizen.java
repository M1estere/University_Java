package Task_2;

public class Citizen implements Runnable {

    private final String category;
    private final ServiceCenter serviceCenter;

    public Citizen(String category, ServiceCenter serviceCenter) {
        this.category = category;
        this.serviceCenter = serviceCenter;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public void run() {
        if (serviceCenter.doVisit(this)) {
            System.out.println(String.format("Citizen %s is being served", category));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            serviceCenter.leaveWindow();
            System.out.println(String.format("Citizen %s has left", category));
        } else {
            System.out.println(String.format("Citizen %s is not suitable, leaving", category));
            serviceCenter.addLeave(category);
        }
    }
}
