import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] array = inputArray();        
        int result_1 = sumMethod(array);
        int result_2 = hashmapMethod(array);
        int result_3 = sortingMethod(array);

        System.out.println("The missing number using sum method is: " + result_1);
        System.err.println("The missing number using hash map is: " + result_2);
        System.out.println("The missing numner using sorting is: " + result_3);
    }

    // Method 1: Using Sum formula
    // Complexity: O(n)
    public static int sumMethod(int[] array) {
        int expectedSum = ((array.length + 1) * (array.length + 2)) / 2;
        int sum = sumArray(array);

        return expectedSum - sum;
    }

    // Method 2: Using HashMap
    // Complexity: O(n)
    public static int hashmapMethod(int[] array) {
        Map map = new HashMap<Integer, Boolean>();
        int i;

        for (i = 0; i < array.length; ++i) {
            map.put(array[i], true);
        }

        for (i = 1; i <= array.length; ++i) {
            if (!map.containsKey(i)) {
                return i;
            }
        }

        return i;
    }

    // Method 3: Sorting
    public static int sortingMethod(int[] array) {
        Arrays.sort(array);

        int j = 1;
        for (int i = 0; i < array.length; ++i, ++j) {
            if (array[i] != j) {
                return j;
            }
        }

        return j;
    }

    public static int sumArray(int[] array) {
        int sum = 0;
        for (int element: array) {
            sum += element;
        }

        return sum;
    }

    public static int[] inputArray() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] parts = input.split(" ");

        int[] array = new int[parts.length];
        for (int i = 0; i < parts.length; ++i) {
            array[i] = Integer.parseInt(parts[i]);
        }

        return array;
    }
}
