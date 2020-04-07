package interview2;


import java.util.HashMap;

/**
 * @author goodtime
 * @create 2020-03-28 2:18 下午
 */
public class Shopee1 {
    public static void main(String[] args) {
    }

    /**
     * 计算球第n次落地时经过的距离
     *
     * @param n int整型 落地次数
     * @return float浮点型
     */
    public float FallingBall(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 100.0f;
        } else {
            float pow = (float) Math.pow(2, n - 1);
            float height = 100.0f / pow;
            return height * 2 + FallingBall(n - 1);
        }
    }

    /**
     * @param Range int整型一维数组 数组长度为2，第1个数为区间下限，第2个数为区间上限（包含区间下限和上限）
     * @return int整型
     */
    public int CountPrime(int[] Range) {
        int low = Range[0];
        int high = Range[1];
        if (low > high) {
            return 0;
        }
        int count = 0;
        if (low == 1) {
            low = 2;
        }
        for (int i = low; i <= high; i++) {
            if (judgePrime(i)) {
                count++;
            }
            ;
        }
        return count;
    }

    private boolean judgePrime(int num) {
        for (int j = 2; j < num; j++) {
            if (num % j == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 用尽量少的硬币数组合出不同的面值
     *
     * @param m  int整型 1<=m<=109
     * @param n  int整型 1<=n<=100，不同面值的硬币种类
     * @param cv int整型一维数组 n种具体的面值
     * @return int整型
     */
    public int ComboCoins(int m, int n, int[] cv) {
        HashMap hashMap = new HashMap();

        for (int i = 1; i <= m; i++) {
            hashMap.put(i, i);
        }

        int result = n;

        if (hashMap.isEmpty() != true) {
            result = recur(1, cv, hashMap);
        }

        if(n >= 0){
            return result;
        }else {
            return -1;
        }
    }

    //不断递归
    private int recur(int depth, int[] cv, HashMap hashMap) {

        int[] cvplus = new int[depth];

        HashMap tmp = new HashMap(hashMap);//拷贝一份

        if (tmp.isEmpty() == true) {
            return depth;
        }

        return 0;
    }
}

