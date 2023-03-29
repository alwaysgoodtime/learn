/**
 * @author goodtime
 * @create 2020-01-26 12:31 下午
 */
public class Number45 {
    public static void main(String[] args) {
        Solution45 solution45 = new Solution45();
        int[] a = {9,10,0,12,13};
        boolean continuous = solution45.isContinuous(a);
        System.out.println(continuous);
    }
}

//判断连续数组

class Solution45 {
    public boolean isContinuous(int [] numbers) {
        int count = 0;//计算数组中0的个数（通配的大王小王）
        int min = 0;//存储连续数组中除0以外的最小值
        if(numbers == null || numbers.length == 0) {
            return false;//健壮性处理
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 0) {
                count++;
            } else {
                if (min <= 0) {
                    min = numbers[i];
                } else if (min > numbers[i]) {
                    min = numbers[i];
                }
            }
        }
        if(min == 0){
            return true;
        }//数组里面全是大王小王（0）
        for (int i = 0,flag = 0,j = min+1; i < numbers.length-1; i++,j++) {//遍历所有的值，最小值不用考虑了，
            // 只用循环number.length-1次就好
            for (int k = 0; k < numbers.length ; k++) {
                if(j == numbers[k]){
                    flag = 1;//找到j值，flag置为1。
                    break;
                }
            }
            if(flag == 0){//flag等于0，说明数组中找不到对应的j值。
                count--;
            }
            if(count < 0){
                return false;//说明数组中没有能用的万能大王小王了。
            }
            flag = 0;//flag置0
        }
        return true;//如果正常出了循环，说明数组能连续，返回真值
    }
}
