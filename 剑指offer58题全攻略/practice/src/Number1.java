/**
 * @author goodtime
 * @create 2020-01-17 8:20 下午
 */
public class Number1
{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int target = 10;
        int[][] array = new int[3][3];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length;j++){
                array[i][j] = i+j;
            }
        }
        boolean b = solution.find(target, array);
        System.out.println(b);
    }

}

class Solution {
    public boolean find(int target, int [][] array) {
        for(int i = array.length -1,j = 0;i >= 0 && j < array[0].length;){
            if(array[i][j] == target){
                return true;
            }else if(array[i][j] > target){
                i--;
            }else{
                j++;
            }
        }
        return false;
    }
}