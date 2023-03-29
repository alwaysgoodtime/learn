import java.util.*;

/**
 * TODO 动态规划
 * JZ85 连续子数组的最大和(二)
 * 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，找到一个具有最大和的连续子数组。
 * 1.子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
 * 2.如果存在多个最大和的连续子数组，那么返回其中长度最长的，该题数据保证这个最长的只存在一个
 * 3.该题定义的子数组的最小长度为1，不存在为空的子数组，即不存在[]是某个数组的子数组
 * 4.返回的数组不计入空间复杂度计算
 *
 * @author goodtime
 * @create 2020-01-21 10:31 下午
 */
public class Number70 {
    public static void main(String[] args) {
        Solution70 solution70 = new Solution70();
        int[] a = {1, 2, -3, 4, -1};
        int[] ints = solution70.FindGreatestSumOfSubArray(a);
        System.out.println(ints.length);
    }
}


//动态规划呼应Number30
class Solution70 {

    //比较复杂，但思路简单，时间空间复杂度高
    public int[] FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return array;
        }

        new ArrayList<Integer>();


        int curr = array[0];
        int max = array[0];

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        //防止第一个数就是最大值
        hashMap.put(0, max);

        for (int i = 1; i < array.length; i++) {

            if (curr < 0) {
                curr = array[i];
            } else {
                curr += array[i];
            }

            //所有加和过的下标放到hashmap里,key为index,map为value
            if (curr >= max) {
                hashMap.put(i, curr);
                max = curr;
            }
        }

        //拿到总和为max的右下标
        Iterator<Integer> iterator = hashMap.keySet().iterator();
        ArrayList<Integer> validIndex = new ArrayList<>();
        while (iterator.hasNext()) {
            int key = iterator.next();
            Integer val = hashMap.get(key);
            if (val == max) {
                validIndex.add(key);
            }
        }

        ArrayList<ArrayList<Integer>> numbersList = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        int sum = 0;

        for (int i = 0; i < validIndex.size(); i++) {
            sum = 0;
            for (int j = validIndex.get(i); j >= 0; j--) {
                numbers.add(array[j]);
                sum += array[j];
                if (sum == max) {
                    numbersList.add(new ArrayList<>(numbers));
                }
            }
            numbers.clear();
        }

        numbersList.sort(new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o1.size() - o2.size();
            }
        });


        ArrayList<Integer> results = numbersList.get(numbersList.size() - 1);

        int[] result = new int[results.size()];

        for (int i = 0; i < results.size(); i++) {
            result[i] = results.get(results.size() - i - 1);
        }

        return result;

    }


}