package interview;


/**
 * @author goodtime
 * @create 2024-01-10 10:52
 */
public class FutuInterview {
    public static void main(String[] args) {

        int[] origin = {3, 5, 1};
        System.out.println(isTargetArray(origin));
    }

    private static boolean isTargetArray(int[] origin) {

        if (origin == null || origin.length <= 1) {
            return false;
        }

        int length = 0;

        for (int i = 0; i < origin.length; i++) {
            length = Math.max(length, origin[i]);
        }

        int[] zeroOneArray = new int[length+1];

        for (int i = 0; i < origin.length; i++) {
            zeroOneArray[origin[i]] = 1;
        }

        int beginIndex = -1;
        int diff = -1;

        //查找
        for (int i = 0; i < zeroOneArray.length; i++) {
            if (zeroOneArray[i] != 1) {
                continue;
            }
            if (beginIndex == -1) {
                beginIndex = i;
                continue;
            }
            beginIndex = i;

            if (diff == -1) {
                diff = i - beginIndex;
                continue;
            }

            if (diff != i - beginIndex) {
                return false;
            }
        }

        return true;
    }


}
