import java.util.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    int sampleRate = 1000;
    int runCount = 5;
    int nMax = 10000;
    int nMin = 1;

//    long[] results = new long[runCount];

    Map <Integer, Long> myMap = new HashMap<Integer, Long>();


    for (int n = sampleRate * 1; n < nMax; n += sampleRate) {

      // generate an empty array to store the time values so they can be averaged
      long[] times = new long[runCount];

      for (int i = 0; i < runCount; i++) {
        // generate numbers
        Integer[] nums =  getRandomNumberList(n);

        // sort, and time the sorting
        long start = System.nanoTime();
        InsertionSort.sort(nums);
        long end = System.nanoTime();
        long elapsed = end-start;

        // average the numbers in the times array
        long avg = getAverage(times);

        // n = the number of items sorted
        // elapsed = the time taken
        // avg = the average sorting time for the given n

        myMap.put(n, avg);

        System.out.println("sorted " + n + "numbers in " + avg);
      }
      generateAndSortNumbers(n, runCount);
    }

  }

  public static long getAverage(long[] nums) {
    long average = 0;
    for (int i = 0; i < nums.length; i++) {
      average += nums[i];
    }
    return average/nums.length;
  }

  public static Integer[] getRandomNumberList(int count) {
    Integer[] result = new Integer[count];

    Set added = new HashSet();

    int a = 0;
    int i = 0;
    while (i < count) {
      if (a > 200000) {
        System.out.println("🍊🍊🍊🍊");
        break;
      }
      int rand = getRandomNumber();
      if (!added.contains(rand)) {
        result[i] = rand;
        added.add(rand);
        i++;
      }
      a++;
    }

    return result;
  }

  public static int getRandomNumber() {
    return (int)(Math.random() * 1024 * 1024);
  }

  public static boolean contains(Integer[] nums, int num) {
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != null) {
        if (nums[i] == num) {
          return true;
        }
      }

    }
    return false;
  }

  public static void usingBufferedWritter() throws IOException {
    String fileContent = "Hello Learner !! Welcome to howtodoinjava.com.";

    BufferedWriter writer = new BufferedWriter(new FileWriter("c:/temp/samplefile1.txt"));
    writer.write(fileContent);
    writer.close();
  }
}
