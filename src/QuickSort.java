import java.util.Arrays;

public class QuickSort {
  public static void sort(Integer[] nums) {
    quicksort(nums, 0, nums.length - 1);
  }

  public static void quicksort(Integer[] nums, int left, int right) {
    if (left < right) {
      int pivot = partition(nums, left, right);
      quicksort(nums, left, pivot - 1);
      quicksort(nums, pivot + 1, right);
    }
  }

  public static int partition(Integer[] nums, int left, int right) {
    int pivotIndex = (left + right - 1) / 2;
    int pivotVal = nums[pivotIndex];

    while(true) {
      while (nums[left] < pivotVal) {
        left++;
      }

      while (nums[right] > pivotVal) {
        right--;
      }

      if ( left >= right) {
        return right;
      }

      // swap
      int temp = nums[left];
      nums[left] = nums[right];
      nums[right] = temp;
    }
  }
}
