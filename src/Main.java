import java.util.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {

  static boolean SHUFFLENUMS = true;

  public static void main(String[] args) throws IOException {

    boolean testRun = false;
    if (testRun) { performTestRun(); return; }

    long start = System.nanoTime(); // total test timer

    int sampleRate = 1000;
    int runCount = 40;
    int nMax = 100000;
    String filePath = "./data.csv";


    // store results in the form of:
    // key: number of items sorted (integer), value: time (integer)
    // time taken to sort is an average from runCount runs
    TreeMap <Integer, Long> myMap = new TreeMap<Integer, Long>();

    for (int n = sampleRate; n <= nMax; n += sampleRate) {
      // generate random numbers, sort them, do this runCount times
      // store the individual sort times
      long[] times = generateAndSortNumbers(n, runCount);

      // average the times
      long avg = getAverage(times);

      // get the min time
      long min = getMin(times);

      // get the min time
      long max = getMax(times);

      // n = the number of items sorted
      // avg = the average sorting time for the given n
      myMap.put(n, avg);
    }

    writeStringToFile(convertMapToString(myMap), filePath);

    long end = System.nanoTime(); // total test timer
    long elapsed = end-start;
    System.out.println("Total elapsed time: " + elapsed / 1000000000 + " seconds");
  }

  public static long getMax(long[] times) {
    long max = times[0];
    for (int i = 0; i < times.length; i++) {
      if (times[i] > max) {
        max = times[i];
      }
    }
    return max;
  }

  public static long getMin(long[] times) {
    long min = times[0];
    for (int i = 0; i < times.length; i++) {
      if (times[i] < min) {
        min = times[i];
      }
    }
    return min;
  }

  //
  // Test run
  //
  public static void performTestRun() {
    //      Integer[] nums = {2, 5, 0, 16, 42, 8, 3, 12, 7, 12};
    //    Integer[] nums = {846096478, 852999422, 736753295, 607471164, 103317737, 1051199469, 207973094, 623476563, 917012307, 712123254};
    Integer[] nums = {7, 2, 1, 8, 6, 3, 5, 4};
    QuickSort.sort(nums);

    if (!isSorted(nums)){
      System.out.println("Result: " + Arrays.toString(nums));
    }
    return;
  }

  public static void writeStringToFile(String fileContent, String filePath) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
    writer.write(fileContent);
    writer.close();
  }

  public static String convertMapToString(TreeMap<Integer, Long> myMap) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter writer = new PrintWriter(stringWriter, true);

    String result = "";
    int count = 0;
    int lastItem = myMap.size() - 1;

    // Format: time in nanoseconds, n, time (in s)
    for (Map.Entry<Integer, Long> entry : myMap.entrySet()) {
      writer.println(entry.getKey() + ", " + (float)entry.getValue()/1000000000);
    }
    return stringWriter.toString();
  }

  public static long[] generateAndSortNumbers(int n, int runCount) {
    long[] times = new long[runCount];

    for (int i = 0; i < runCount; i++) {
      // generate numbers
      Integer[] nums =  getRandomNumberList(n);

      // sort, and time the sorting
      long start = System.nanoTime();
//      InsertionSort.sort(nums);
//      SelectionSort.sort(nums);
      nums = MergeSort.sort(nums); // merge sort returns a new array
//      MergeSort2.sort(nums, nums.length); // merge sort returns a new array
//      QuickSort.sort(nums);
      long end = System.nanoTime();
      long elapsed = end-start;
      times[i] = elapsed;

      if (!isSorted(nums)) {
        System.out.println("🍎🍎🍎 ERROR ARRAY WAS NOT SORTED🍎🍎🍎");
      }
    }
    return times;
  }

  public static boolean isSorted(Integer[] nums) {
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] < nums[i - 1]) {
        System.out.println("Array: " + Arrays.toString(nums));
        return false;
      }
    }
    return true;
  }


  public static long getAverage(long[] nums) {
    long average = 0;
    for (int i = 0; i < nums.length; i++) {
      average += nums[i];
    }
    return average/nums.length;
  }


  public static Integer[] getRandomNumberList(int count) {
    //
    // Generate the random numbers to be used in the sort
    // All numbers are unique
    //

    Integer[] nums = new Integer[count];
    for (int i = 0; i < count; i++) {
      nums[i] = i;
    }

    if (SHUFFLENUMS) {
      Collections.shuffle(Arrays.asList(nums));
    }

    return nums;
  }

  public static int getRandomNumber() {
    return (int)(Math.random() * 10 * Math.random() * 10 * 200000);
  }


}
