package leetcode.src.main.java.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/partition-labels/
 * @author goodtime
 * @create 2023-04-01 22:26
 */
public class Number763 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution763().partitionLabels("qiejxqfnqceocmy").toArray()));
    }
}

/**
 * 从第一个字符开始找起，如果找到第一个字符在字符串里最后出现的位置，然后把两段之间所有的字符都找到最后出现的位置，最后
 * 当成第一个片段，接下来再从第一个片段后的第一个字符找起，重复上述过程
 *
 * 优化，可以用空间换一下时间，用一个27个元素的数组hash[27]，先遍历一遍数组，把每个元素最远出现的位置记录下来，以替代s.lastIndexOf方法
 * hash[s[i] - 'a'] = i;
 */
class Solution763 {

    ArrayList<Integer> result  = new ArrayList<>();

    public List<Integer> partitionLabels(String s) {

        if(s == null || s.length() == 0){
            return null;
        }

        for (int i = 0; i < s.length();) {
            char first = s.charAt(i);
            int index = s.lastIndexOf(first);
            if(index == -1){
                i++;
                result.add(1);
            }else {
                i = findMaxIndex(s,i,index);
            }

        }

        return result;
    }

    private int findMaxIndex(String s, int i, int index) {

        int maxIndex = index;

        for (int j = i + 1; j < maxIndex; j++) {
            char letter = s.charAt(j);
            int lastIndex = s.lastIndexOf(letter);
            maxIndex = Math.max(lastIndex, maxIndex);
        }

        result.add(maxIndex - i + 1);

        return maxIndex + 1;

    }
}