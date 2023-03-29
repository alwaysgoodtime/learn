package interview2;

import java.util.Scanner;

/**
 * 华为的题，不知道为什么没通过
 * @author goodtime
 * @create 2020-03-25 7:51 下午
 */
public class HUAWEI3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int row = scanner.nextInt();

        if (row == 1) {
            System.out.println(1);
        } else {

            String[] target = new String[row];
            String s = scanner.nextLine();
            for (int i = 0; i < row; i++) {
                target[i] = scanner.nextLine();
            }

            int length = target[0].length();
            int[][] num = new int[row][length];
            for (int i = 0; i < row; i++) {
                char[] chars = target[i].toCharArray();
                int count = 0;
                for (char a : chars
                ) {
                    num[i][count++] = a == '0' ? 0 : 1;
                }
            }

            int max = 0;

            for (int i = 0; i < row; i++) {
               int tmp =  findMAX(num,num[i],i);
               max = tmp > max ? tmp : max;
            }

            System.out.println(max);

        }


    }

    private static int findMAX(int[][] nums, int[] num,int row) {
        int max = 1;
        for (int i = 0; i < num.length; i++) {
            if(num[i] == 1){
                int tmp = findMAX2(nums, num,row,i,0);
                tmp = (tmp+1)*(tmp+1);
                max = max > tmp ? max : tmp;
            }
        }
        return max;
    }

    private static int findMAX2(int[][] nums, int[] num, int row,int line,int height) {

        int tmp = height;

        if(line < num.length-1 && row < nums.length-1 && num[line+1] == 1 && nums[row+1][line] == 1 && nums[row+1][line+1] == 1){
            int right =  findMAX2(nums,nums[row],row,line+1,height+1);
            if(right == 0){
                return 1;
            }
            int under = findMAX2(nums,nums[row+1],row+1,line,height+1);
            if(under == 0){
                return 1;
            }
            int underright = findMAX2(nums,nums[row+1],row+1,line,height+1);

            right = right > under ? under : right;

            right = right > underright ? underright : right;

            return right;

        }else {

            return tmp;

        }
    }
}


