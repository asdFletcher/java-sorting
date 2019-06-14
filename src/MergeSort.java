import java.util.Arrays;

public class MergeSort {
  public static Integer[] sort(Integer[] nums) {
    return splitAndMerge(0, nums.length - 1, nums);
  }

  public static Integer[] splitAndMerge(int start, int end, Integer[] arr) {
    // base case
    int a;
    if (end == start) {
      try {
        a = arr[start];
      } catch (Error e) {
      System.out.println("start: " + start + ", arr: " + Arrays.toString(arr));

      }
      Integer[] res = new Integer[] {arr[start]};
      return res;
    }

    int mid = start + (int)((double)end - (double)start) / 2;

    Integer[] left = splitAndMerge(start, mid, arr);
    Integer[] right = splitAndMerge(mid + 1, end, arr);

    return merge(left, right);
  }

  public static Integer[] merge(Integer[] leftList, Integer[] rightList) {
    // copies array, inefficient space usage, could zipper lists for O(1) space
    int currentLeft = 0;
    int currentRight = 0;
    Integer[] result = new Integer[leftList.length + rightList.length];

    // advance L & R until all numbers have been added to new sorted array
    for( int i = 0; i < result.length; i++) {

      if (currentLeft == leftList.length) {
        int right = rightList[currentRight];
        result[i] = right;
        currentRight++;
        continue;
      }
      if (currentRight == rightList.length) {
        int left = leftList[currentLeft];
        result[i] = left;
        currentLeft++;
        continue;
      }

      int left = leftList[currentLeft];
      int right = rightList[currentRight];

      if (left < right || left == right) {
        result[i] = left;
        currentLeft++;
      } else {
        result[i] = right;
        currentRight++;
      }
    }

    return result;
  }
}


