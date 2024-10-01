import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Sorting {
  
  private static final int INSERTION_SORT_THRESHOLD = 10;

  public static void main(String[] args) {
    
      In in = new In(args[0]);
      int[] a = in.readAllInts();

      // Create array b (sorted)
      int[] b = Arrays.copyOf(a, a.length);
      Arrays.sort(b);

      // Create array c (reverse sorted)
      int[] c = new int[a.length];
      for (int i = 0; i < a.length; i++) {
          c[i] = b[a.length - 1 - i];
      }

      // Create array d (almost sorted)
      int[] d = Arrays.copyOf(b, b.length);
      Random rand = new Random();
      int swaps = (int) (0.1 * d.length);
      for (int i = 0; i < swaps; i++) {
          int index1 = rand.nextInt(d.length);
          int index2 = rand.nextInt(d.length);
          int temp = d[index1];
          d[index1] = d[index2];
          d[index2] = temp;
      }

      int algorithmChoice = Integer.parseInt(args[1]);
      
      sortAndPrintResults(a, "a", algorithmChoice, args[0]);
      sortAndPrintResults(b, "b", algorithmChoice, args[0]);
      sortAndPrintResults(c, "c", algorithmChoice, args[0]);
      sortAndPrintResults(d, "d", algorithmChoice, args[0]);
  }

  private static void sortAndPrintResults(int[] arr, String arrName, int algorithmChoice, String inputFile) {
      String algorithmName = getName(algorithmChoice);

      Stopwatch timer = new Stopwatch();
      sort(arr, algorithmChoice);
      double time = timer.elapsedTimeMillis();

      String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
      String netID = "ebozoglu"; 

      StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmName, arrName, time, timeStamp, netID, inputFile);
      
      // Write the resultant array to a file
      String outputFileName = arrName + ".txt";
      try (FileWriter fileWriter = new FileWriter(outputFileName);
            PrintWriter printWriter = new PrintWriter(fileWriter)) {
          for (int num : arr) {
              printWriter.println(num);
          }
      } catch (IOException e) {
          System.err.println("Error writing to file: " + e.getMessage());
      }
  }

  private static void sort(int[] arr, int algorithmChoice) {
      switch (algorithmChoice) {
          case 0:
              Arrays.sort(arr);
              break;
          case 1:
              bubbleSort(arr);
              break;
          case 2:
              selectionSort(arr);
              break;
          case 3:
              quickSort(arr, 0, arr.length - 1);
              break;
          default:
              return;
      }
  }

  private static String getName(int algorithmChoice) {
      switch (algorithmChoice) {
          case 0: return "Arrays.sort()";
          case 1: return "Bubble Sort";
          case 2: return "Selection Sort";
          case 3: return "Quick Sort";
          default: return " ";
      }
  }

  private static void bubbleSort(int[] arr) {
      for (int i = 0; i < arr.length - 1; i++) {
          boolean swapped = false;
          for (int j = 0; j < arr.length - 1 - i; j++) {
              if (arr[j] > arr[j + 1]) {
                  int temp = arr[j];
                  arr[j] = arr[j + 1];
                  arr[j + 1] = temp;
                  swapped = true;
              }
          }
          if (!swapped) break;
      }
  }

  private static void selectionSort(int[] arr) {
      for (int i = 0; i < arr.length - 1; i++) {
          int minIndex = i;
          for (int j = i + 1; j < arr.length; j++) {
              if (arr[j] < arr[minIndex]) {
                  minIndex = j;
              }
          }
          if (minIndex != i) {
              int temp = arr[i];
              arr[i] = arr[minIndex];
              arr[minIndex] = temp;
          }
      }
  }

  // I was getting StackOverFLow errors with my recursive implementation so I had to look up solutions.
  private static void quickSort(int[] arr, int low, int high) {
    while (low < high) {
        if (high - low < INSERTION_SORT_THRESHOLD) {
            insertionSort(arr, low, high);
            return;
        }
        
        int pivotIndex = partition(arr, low, high);
        
        // Tail recursion optimization
        if (pivotIndex - low < high - pivotIndex) {
            quickSort(arr, low, pivotIndex - 1);
            low = pivotIndex + 1;
        } else {
            quickSort(arr, pivotIndex + 1, high);
            high = pivotIndex - 1;
        }
    }
  }

  private static int partition(int[] arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    
    for (int j = low; j < high; j++) {
        if (arr[j] <= pivot) {
            i++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    
    return i + 1;
  }

  /*
  Source: LuizGsa21's GitHub repository
  Title: intro-to-java-10th-edition
  File: QuickSort.java
  URL: https://github.com/LuizGsa21/intro-to-java-10th-edition/blob/master/src/Chapter_23/QuickSort.java
  */

 private static void insertionSort(int[] arr, int low, int high) {
    for (int i = low + 1; i <= high; i++) {
        int key = arr[i];
        int j = i - 1;

        while (j >= low && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }
  }
}