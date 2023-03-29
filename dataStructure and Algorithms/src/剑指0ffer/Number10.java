/**
 * @author goodtime
 * @create 2020-01-19 9:45 上午
 */
public class Number10 {
    public static void main(String[] args) {
        Solution10 solution10 = new Solution10();
        int i = solution10.RectCover(10);
        System.out.println(i);


    }

}
class Solution10 {
    public int RectCover(int target) {
        if(target <= 0){
            return 0;
        }
        else if (target == 1){
            return 1;
        }
        else if (target == 2){
            return 2;
        }else {
            return  RectCover(target-1) + RectCover(target - 2);
        }

    }
}