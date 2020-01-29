import java.security.ProtectionDomain;

/**
 * @author goodtime
 * @create 2020-01-23 2:08 下午
 */
public class Number37 {
    public static void main(String[] args) {
        Solution37 solution37 = new Solution37();
        int[]a = {1,2};
        int i = solution37.GetNumberOfK(a, 2);
        System.out.println(i);
    }
}

//统计一个数字在排序数组中出现的次数，题目是考有序数组的查找，下面采用二分查找。
//题目不难，关键是各种优化二分查找，让运行速度提上去
class Solution37 {
    public int GetNumberOfK(int [] array , int k) {
        if(array == null || array.length == 0){
            return 0;//预处理，保证代码稳定性
        }
        if(array.length == 1){
            if(k == array[0]){
                return 1;
            }else{
              return 0;
            }//预处理
        }
        int count = 0;
        int l = -1;
        int m = array.length;
        int start = 0;
        int end = m-1;
        int middle = (start+end)/2;
        while(end - start >= 0) {//这里一定要小心，end-start一定是大于等于0才行，如果是大于等于1，end和start相等时，可能正好才取到k值
            //eg. [1,2] k = 1
            if(array[middle] == k){
                l = middle;
                break;
            }else if(array[middle] > k){
                end = middle-1;
                middle = (start+end)/2;
            }else {
                start = middle+1;
                middle = (start+end)/2;
            }
        }
        if(l == -1){
            return 0;//说明l未赋值，循环正常终止，直接输出，减少代码运行时间
        }
        for (int i = l; i != -1; i--) {
            if(array[i] == k)
                count++;
            else
                break;
        }
        for (int n = l+1; n != m; n++) {
            if(array[n] == k)
                count++;
            else
                break;
        }
        return count;
    }
}
