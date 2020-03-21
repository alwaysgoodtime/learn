/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * @author goodtime
 * @create 2020-01-18 10:40 下午
 */
public class Number8 {
    public static void main(String[] args) {
        Solution8 solution8 = new Solution8();
        int i = solution8.JumpFloor(3);
        System.out.println(i);


    }
}


class Solution8 {
    public int JumpFloor(int target) {
        if(target == 1){
            return 1;
        }
        else if(target == 2){
            return 2;
        }else{
            return JumpFloor(target-1) + JumpFloor(target -2);
        }
    }
}

