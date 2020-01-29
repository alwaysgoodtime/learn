/**
 * @author goodtime
 * @create 2020-01-18 10:28 下午
 */
public class Number7 {
    public static void main(String[] args) {
        Solution7 solution = new Solution7();
        System.out.println(solution.Fibonacci(1));

    }
}

class Solution7 {
        public int Fibonacci(int n) {
            int s = fibo(n);
            return s;
        }

        public int fibo(int j){
            if (j == 0){
                return 0;
            }
            else if(j == 1){
                return 1;
            }else{
                return fibo(j-1) + fibo(j-2);
            }

        }
    }

