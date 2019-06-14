import java.util.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Main {
  public static void main(String[] args) throws IOException {

    boolean testRun = true;

    if (testRun) {

      int[] nums = {2, 5, 0, 16, 42, 8, 3, 12, 7, 12};
      MergeSort.sort(nums);
      return;
    }

    long start = System.nanoTime();


    int sampleRate = 1000;
    int runCount = 20;
    int nMax = 25000;
    String filePath = "./data.csv";
    // store results in the form of:
    // key: number of items sorted (integer) ==> value: time (integer)
    // time taken to sort is an average from runCount runs
    Map <Integer, Long> myMap = new HashMap<Integer, Long>();

    for (int n = sampleRate; n <= nMax; n += sampleRate) {
      // generate random numbers, sort them, do this runCount times
      // store the individual sort times
      long[] times = generateAndSortNumbers(n, runCount);

      // average the times
      long avg = getAverage(times);

      // n = the number of items sorted
      // avg = the average sorting time for the given n
      myMap.put(n, avg);
    }

    writeStringToFile(convertMapToString(myMap), filePath);

    long end = System.nanoTime();
    long elapsed = end-start;
    System.out.println("Total elapsed time: " + elapsed / 1000000000 + " seconds");
  }


  public static void writeStringToFile(String fileContent, String filePath) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
    writer.write(fileContent);
    writer.close();
  }

  public static String convertMapToString(Map<Integer, Long> myMap) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter writer = new PrintWriter(stringWriter, true);

    String result = "";
    int count = 0;
    int lastItem = myMap.size() - 1;
    for (Map.Entry<Integer, Long> entry : myMap.entrySet()) {
      writer.println(entry.getKey() + ", " + entry.getValue().toString());
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
      SelectionSort.sort(nums);
      long end = System.nanoTime();
      long elapsed = end-start;
      times[i] = elapsed;
    }
    return times;
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

}
