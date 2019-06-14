public class SelectionSort {
  public static Integer[] sort(Integer[] nums) {
    int lastSortedIndex = 0;

    // go thru the list
    for (int i = 0; i < nums.length; i++) {

      // scan the rest for min
      int minIndex = i;
      for (int j = i; j < nums.length; j++) {
        if (nums[j] < nums[minIndex]) {
          minIndex = j;
        }
      }

      // perform swap between current and min
      if (minIndex != i) {
        int minValue = nums[minIndex];
        nums[minIndex] = nums[i];
        nums[i] = minValue;
      }
    }
    return nums;
  }
}
