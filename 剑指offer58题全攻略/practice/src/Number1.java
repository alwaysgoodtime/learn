/**
 *在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 *请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * @author goodtime
 * @create 2020-01-17 8:20 下午
 */
public class Number1
{
    public static void main(String[] args) {
        Solutions1 solution = new Solutions1();
        int target = 4;
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

class Solutions1{
    public boolean find(int target, int [][] array) {
        int length =  array[0].length;
        int width = array.length-1;
        int x = 0;
        while(width >= 0 && x < length && target != array[width][x]){
            if(target < array[width][x]){
                width--;
            }else{
                x++;
            }
        }

        return width >= 0 && x < length;

    }

}