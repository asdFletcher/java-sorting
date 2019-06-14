import java.util.Arrays;

public class MergeSort {
  public static int[] sort(int[] nums) {
    return splitAndMerge(0, nums.length - 1, nums);
  }

  public static int[] splitAndMerge(int start, int end, int[] arr) {
    // base case
    if (end == start) {
      int[] res = new int[] {arr[start]};
      return res;
    }

    int mid = start + (int)((double)end - (double)start) / 2;

    int[] left = splitAndMerge(start, mid, arr);
    int[] right = splitAndMerge(mid + 1, end, arr);

    return merge(left, right);
  }

  public static int[] merge(int[] leftList, int[] rightList) {
    // copies array, inefficient space usage, could zipper lists for O(1) space
    int currentLeft = 0;
    int currentRight = 0;
    int[] result = new int[leftList.length + rightList.length];

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


