// Author: Eray BOZOGLU
// NetID: ebozoglu
// CSC172 LAB_1

public class LabOne {
    public static void main(String[] args) {

        Integer[] intArry = {1, 2, 3, 4, 5};
        Double[] doubArry = {1.1, 2.2, 3.3, 4.4};
        Character[] charArray = {'H', 'E', 'L', 'L', 'O'};
        String[] strArray = {"once", "upon", "a", "time"};
        printArray(intArry);
        printArray(doubArry);
        printArray(charArray);
        printArray(strArray);

        System.out.println("max Integer is: " + getMax(intArry));
        System.out.println("max Double is: " + getMax(doubArry));
        System.out.println("max Character is: " + getMax(charArray));
        System.out.println("max String is: " + getMax(strArray));
    }

    /*
     *    public static void printArray(Object[] array) {
     *        for (Object element : array) {
     *            System.out.print(element + " ");
     *        }
     *        System.out.println();
     *    }
     */


    /*
     *    public static void printArray(Integer[] arr) {
     *        for (Integer a : arr) {
     *            System.out.print(a + " ");
     *        }
     *        System.out.println();
     *    }
     *
     *    public static void printArray(Double[] arr) {
     *        for (Double a : arr) {
     *            System.out.print(a + " ");
     *        }
     *        System.out.println();
     *    }
     *
     *    public static void printArray(Character[] arr) {
     *        for (Character a : arr) {
     *            System.out.print(a + " ");
     *        }
     *        System.out.println();
     *    }
     *    public static void printArray(String[] arr) {
     *        for (String a : arr) {
     *            System.out.print(a + " ");
     *        }
     *        System.out.println();
     *    }
     */

    public static <T> void printArray(T[] arr) {
        for (T a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    /*
     *    Inspection: Raw use of parameterized class.
     *    public static Comparable getMax(Comparable [] arr) {
     *        Comparable currMax = arr[arr.length - 1];
     *
     *        for (Comparable obj : arr) {
     *            if (obj.compareTo(currMax) > 0) {
     *                currMax = obj;
     *            }
     *        }
     *        return currMax;
     *    }
     */

    public static <T extends Comparable<T>> T getMax(T [] arr) {
        T currMax = arr[arr.length - 1];

        for (T obj : arr) {
            if (obj.compareTo(currMax) > 0) {
                currMax = obj;
            }
        }
        return currMax;
    }
}