import java.util.*;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) {
    int sampleRate = 1000;
    int runCount = 5;
    int nMax = 10000;
    int nMin = 1;

    long[] results = new long[runCount];

    for (int n = sampleRate * 1; n < nMax; n += sampleRate) {
      for (int i = 0; i < runCount; i++) {
        // generate numbers
        Integer[] nums =  getRandomNumberList(n);

        long start = System.nanoTime();
        InsertionSort.sort(nums);
        long end = System.nanoTime();
        long elapsed = end-start;
        System.out.println("elapsed: " + elapsed);
      }
    }
    // sort 100 times, time each one, average, add to array

    // start timer
    // sort array
    // end timer
    // add time to array
    // average array

    // add each to an array index = n, t = average time
    // process array into something that can be plotted


    // 1000 times
    // generate 10 random numbers
    // sort them
    // time it
    // x: 10, y: 0.05
    // [ {x: 10, y: 0.05} .... {x: 10, y: 0.04} ] <-- many at x = 10
    // [ {x: 11, y: 0.05} .... {x: 11, y: 0.04} ] <-- many at x = 11
    // [ {x: 12, y: 0.05} .... {x: 12, y: 0.04} ] <-- many at x = 12
    // average each x:10, put into new array
    // average each x:11, put into new array
    // ....
    // return result

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
