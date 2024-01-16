package sort;

import java.util.Arrays;

/**
 * @author goodtime
 * @create 2024-01-15 11:45
 */
public class SortTest {
    public static void main(String[] args) {
        int[] sortTarget = {2,2,3,5,4,1};

        int[] sort = BubbleSort.sort(sortTarget);

        System.out.println(Arrays.toString(sort));

    }
}
