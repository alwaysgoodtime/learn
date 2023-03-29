import java.util.HashMap;

/**
 * @author goodtime
 * @create 2020-01-22 7:56 下午
 */
public class Number34 {
    public static void main(String[] args) {
        Solution34s solution34 = new Solution34s();
        System.out.println(solution34.FirstNotRepeatingChar("abcaaab"));
    }
}

// 直接暴力穷举，时间复杂度是o（n的平方）
//class Solution34{
//    public int FirstNotRepeatingChar(String str) {
//        if(str == null || str.length() == 0){
//            return -1;
//        }
//        int length = str.length();
//        char[] chars = str.toCharArray();
//        for (int i = 0,j = 0; i < length; i++) {
//            for (j = 0; j < length; j++) {
//                if(j == i){
//                    continue;
//                }
//                if( chars[i] == chars[j]){
//                    break;
//                }
//            }
//            if(j == length){
//                return i;
//            }
//        }
//        return -1;
//    }
//}


//改进版，思路是用hashmap存值，遍历一遍string，把大小写字母出现的次数存到hashmap当然，算法时间复杂度就只有O(n）
//还可以更简单，直接用一个数组做hash，数组的下标代表字母，值为出现次数。
//链接：https://www.nowcoder.com/questionTerminal/1c82e8cf713b4bbeb2a5b31cf5b0417c?f=discussion
//来源：牛客网
//
//说一下解题思路哈，其实主要还是hash，利用每个字母的ASCII码作hash来作为数组的index。首先用一个58长度的数组来存储每个字母出现的次数，
//为什么是58呢，主要是由于A-Z对应的ASCII码为65-90，a-z对应的ASCII码值为97-122，
//而每个字母的index=int(word)-65，比如g=103-65=38，而数组中具体记录的内容是该字母出现的次数，
//最终遍历一遍字符串，找出第一个数组内容为1的字母就可以了，时间复杂度为O(n)
class Solution34s {
    public int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
        //用linkedhashmap也可以，它可以保证遍历顺序为添加顺序,
        //那样的话第一个值为1的key就是第一个出现一次的字母，不过仍要重新遍历一次原来的数组，反而浪费了保存空间。
        char[] chars = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (hashMap.containsKey(chars[i])) {
                int j = hashMap.get(chars[i]);
                hashMap.put(chars[i], ++j);//不能直接让++j，因为++j，只是改变j的值，而非改变key为chars[i]的值
            } else {
                hashMap.put(chars[i], 1);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            if (hashMap.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }
}