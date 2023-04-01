package 剑指0ffer.search;

/**
 * JZ4 二维数组中的查找
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * @author goodtime
 * @create 2020-01-17 8:20 下午
 */
public class Number1 {
    public static void main(String[] args) {
        Solutions2 solution = new Solutions2();
        int target = 4;
        int[][] array = new int[3][3];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = i + j;
            }
        }
        boolean b = solution.Find(target, array);
        System.out.println(b);
    }

}

/**
 * 解题思路，要找到一个二维数组中的位置，在其上面或下面的值都比它小，在其左边或右边的值都比它大。
 * 二维数组有两个这样的位置
 */

class Solutions1 {
    public boolean Find(int target, int[][] array) {

        //增加判空与快速失败
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }


        int length = array[0].length;
        int width = array.length - 1;
        int x = 0;
        while (width >= 0 && x < length && target != array[width][x]) {
            if (target < array[width][x]) {
                width--;
            } else {
                x++;
            }
        }

        return width >= 0 && x < length;

    }

}

class Solutions2 {
    public boolean Find(int target, int[][] array) {

        //增加判空与快速失败
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }

        int length = array.length;
        int width = array[0].length;

        for (int i = length - 1, j = 0; i >= 0 && j < width; ) {
            if (array[i][j] == target) {
                return true;
            } else if (array[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }

        return false;

    }
}