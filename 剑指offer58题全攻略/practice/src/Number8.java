/**
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
