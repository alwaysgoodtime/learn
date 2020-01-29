/**
 * @author goodtime
 * @create 2020-01-18 10:40 下午
 */
public class Number9 {
    public static void main(String[] args) {
        Solution9 solution9 = new Solution9();
        int i = solution9.JumpFloor(4);
        System.out.println(i);


    }
}


class Solution9 {
    public int JumpFloor(int target) {
        if(target == 1){
            return 1;
        }
        else if(target == 2){
            return 2;
        }else{
            int s = 1;
            for (int i = target-1; i > 0 ; i--) {
                s += JumpFloor(i);
            }
            return s;
        }
    }
}

//也可直接写2的（n-1）次方，因为除了最后一个格子，每个格子要么走，要么不走。