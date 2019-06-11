public class InsertionSort {
  public static void sort(Double[] nums) {
    System.out.println("insertion sort:");
    Double sum = 0.0;
    for(int i = 0; i < nums.length; i++) {
      System.out.println(nums[i]);
      sum = sum + nums[i];
    }
    System.out.println("Sum: " + sum);

  }

  // cast integers as doubles
  public static void sort(Integer[] nums) {
    Double[] numsAsDoubles = new Double[nums.length];
    for (int i = 0; i < nums.length; i++) {
      numsAsDoubles[i] = Double.valueOf(nums[i]);
    }
    sort(numsAsDoubles);
  }

}
