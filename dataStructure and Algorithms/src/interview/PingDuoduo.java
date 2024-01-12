package interview;

import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-03-20 6:57 下午
 */
public class PingDuoduo {
    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        int[] length = new int[2];
        for (int i = 0; i < 2; i++) {
            length[i] = a.nextInt();
        }
        int line = length[0];
        int row = length[1];
        int max = 0;
        int[][] target = new int[line][row];
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < row; j++) {
                target[i][j] = a.nextInt();
                max = max > target[i][j] ? max : target[i][j];
            }
        }
        int count = 0;
//        for(int i = 1; i < line -1;i++){
//            for (int j = 1; j < row - 1; j++) {
        for (int k = target[1][1] + 1; k <= max; k++) {
            if (judge(k, target)) {
                count++;
            } else {
                break;
            }

//                }
//            }
//        }

            System.out.println(count);
        }

    }

    //未完成，通过不停给1，1这个角落下雨，如果雨能留到别处，就+1
    private static boolean judge(int k, int[][] target) {
        if (k <= target[1][0] && k <= target[1][2] &&
                k <= target[0][1] && k <= target[2][1]) {
            return true;
        }
        return false;
    }
}
