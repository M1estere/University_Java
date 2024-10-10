package Task_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

// класс мфц
public class ServiceCenter {

    private Window[] windows = new Window[3];
    private ArrayList<Integer> leaves = new ArrayList<>(Arrays.asList(0, 0, 0));

    public ServiceCenter() {
        for (int i = 0; i < 3; i++) {
            windows[i] = new Window();
        }
    }

    public boolean doVisit(Citizen citizen) {
        Random random = new Random();
        int wIndex = random.nextInt(windows.length);
        if (windows[wIndex].isBusy) {
            return false;
        }

        if (canServeCitizen(citizen, wIndex)) {
            windows[wIndex].isBusy = true;
            return true;
        }

        return false;
    }

    private boolean canServeCitizen(Citizen citizen, int wIndex) {
        if (wIndex == 0) {
            return true;
        } else if (wIndex == 1 && citizen.getCategory().equals("old")) {
            return true;
        } else if (wIndex == 2 && citizen.getCategory().equals("business")) {
            return true;
        }

        return false;
    }

    public void leaveWindow() {
        for (Window window : windows) {
            window.isBusy = false;
        }
    }

    public void addLeave(String cat) {
        if (cat.equals("young")) {
            leaves.set(0, leaves.get(0) + 1);
        } else if (cat.equals("old")) {
            leaves.set(1, leaves.get(1) + 1);
        } else if (cat.equals("business")) {
            leaves.set(2, leaves.get(2) + 1);
        }
    }

    public void printLeaves(int resultCitizens) {
        System.out.println(
                "Leaves pct:\n" +
                "\tYoung: " + (leaves.get(0) * 100 / resultCitizens) + "%\n" +
                "\tOld: " + (leaves.get(1) * 100 / resultCitizens) + "%\n" +
                "\tBusiness: " + (leaves.get(2) * 100 / resultCitizens) + "%"
        );
    }

}
