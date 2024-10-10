import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String[] array1 = input.split("_")[0].split(" ");
        String[] array2 = input.split("_")[1].split(" ");

        // Находим общие значения
        Set<Integer> commonElements = findCommonElements(array1, array2);

        StringBuilder result = new StringBuilder();
        for (Integer value : commonElements) {
            result.append(value).append(" ");
        }

        System.out.println(result.toString().trim());
    }

    public static Set<Integer> findCommonElements(String[] array1, String[] array2) {
        Set<Integer> set1 = new TreeSet<>();
        Set<Integer> commonSet = new TreeSet<>();

        for (String element : array1) {
            set1.add(Integer.parseInt(element));
        }

        for (String element : array2) {
            int targetElement = Integer.parseInt(element);
            if (set1.contains(targetElement)) {
                commonSet.add(targetElement);
            }
        }

        return commonSet;
    }
}
