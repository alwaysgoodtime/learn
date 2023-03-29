
/**
 * JZ10 斐波那契数列
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 *
 * @author goodtime
 * @create 2020-01-18 10:28 下午
 */
public class Number7 {
    public static void main(String[] args) {
        Solution7s solution = new Solution7s();
        System.out.println(solution.fibonacci(6));

    }
}

class Solution7 {
    public int fibonacci(int n) {
        int s = fibo(n);
        return s;
    }

    public int fibo(int j) {
        if (j == 0) {
            return 0;
        } else if (j == 1) {
            return 1;
        } else {
            return fibo(j - 1) + fibo(j - 2);
        }

    }
}

class Solution7s {
    public int fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}