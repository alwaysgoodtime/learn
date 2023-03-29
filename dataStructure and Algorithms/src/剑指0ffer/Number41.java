import java.util.ArrayList;

/**
 * @author goodtime
 * @create 2020-01-24 5:20 下午
 */


public class Number41 {
    public static void main(String[] args) {
        Solution41 solution41 = new Solution41();
        ArrayList<ArrayList<Integer>> arrayLists = solution41.FindContinuousSequence(10);
        System.out.println(arrayLists);
    }
}

//最直接做法：可以暴力穷举，从1开始，往后数，直到累加的数大于等于sum，然后再从2开始，直到遍历完所有的数,就找到了所有连续值的和为sum的数组。
//
//滑动窗口（左右指针）做法，想象tcp的滑动窗口发报文机制，起始窗口最左边是1，最右边是2，如果窗口的值小于sum，右边窗口右移，如果窗口值大于sum，左边窗口右移。
//如果等于，则count（连续数列等于sum的个数）++,数列放到ArrayList中；
class Solution41 {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        for (int start = 1,end = 2,all = 3; end < sum ;) {
            ArrayList<Integer> integers = new ArrayList<>();
            all = (start+end)*(end-start+1)/2;
            if(all < sum){
                end ++;
            }else if(all > sum){
                start++;
            }else {
                integers.clear();
                for (int i = start; i <= end ; i++) {
                    integers.add(i);
                }
               // list.add(integers);//如果integers的定义在循环外面时，不能这样写，因为list中加引用数据对象时，
                // 传的是integers这个对象的引用，后续如果integers修改，list中存储的值也会相应修改，list修改也是同理
                //所以integers要定义在循环中，保证每次循环用的integers不一样
                start++;

            }
        }
        return list;

    }
}
