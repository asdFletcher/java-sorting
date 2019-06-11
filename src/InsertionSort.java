public class InsertionSort {

  public static Integer[] sort(Integer[] nums) {
    // run thru the list forwards
    for (int i = 1; i < nums.length; i++) {
      Integer temp = nums[i];

      // compare and swap with each element going backwards
      for (int j = i - 1; j >= 0; j--) {
        if (temp < nums[j]) {
          nums[j + 1] = nums[j];
          nums[j] = temp;
        } else {
          break;
        }
      }
    }

    return nums;
  }
}
