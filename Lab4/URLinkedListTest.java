import java.util.Arrays;
import java.util.Collection;

public class URLinkedListTest {
    public static void main(String[] args) {
        URLinkedList<Integer> testList = new URLinkedList<>();

        System.out.println("Beginning tests.");

        // Test add method
        testList.add(1);
        testList.add(2);
        if (testList.size() != 2) {
            System.out.println("   FAILED add method");
        }

        // Test get method
        if (testList.get(0) != 1 || testList.get(1) != 2) {
            System.out.println("   FAILED get method");
        }

        // Test remove method
        int removed = testList.remove(0);
        if (removed != 1 || testList.size() != 1) {
            System.out.println("   FAILED remove method");
        }

        // Test set method
        int oldValue = testList.set(0, 3);
        if (oldValue != 2 || testList.get(0) != 3) {
            System.out.println("   FAILED set method");
        }

        // Test addAll method
        Collection<Integer> toAdd = Arrays.asList(4, 5, 6);
        testList.addAll(toAdd);
        if (testList.size() != 4 || testList.get(3) != 6) {
            System.out.println("   FAILED addAll method");
        }

        // Test subList method
        URList<Integer> subList = testList.subList(1, 3);
        if (subList.size() != 2 || subList.get(0) != 4 || subList.get(1) != 5) {
            System.out.println("   FAILED subList method");
        }

        // Test toArray method
        Object[] array = testList.toArray();
        if (array.length != 4 || !Arrays.equals(array, new Object[]{3, 4, 5, 6})) {
            System.out.println("   FAILED toArray method");
        }

        // Test peek and poll methods
        if (testList.peekFirst() != 3 || testList.peekLast() != 6) {
            System.out.println("   FAILED peek methods");
        }
        if (testList.pollFirst() != 3 || testList.pollLast() != 6 || testList.size() != 2) {
            System.out.println("   FAILED poll methods");
        }

        System.out.println("Tests complete.");
    }
}