/**
 * @author goodtime
 * @create 2020-01-28 4:37 下午
 */
public class Number54 {
    public static void main(String[] args) {
        Solution54 solution54 = new Solution54();
        solution54.Insert('a');
        solution54.Insert('b');
        solution54.Insert('a');
        solution54.Insert('c');
        char c = solution54.FirstAppearingOnce();
        System.out.println(c);

    }
}
//如果暴力穷举，时间复杂度为n的平方。
// 这里可以用hash的做法，可以创造一个长度为128的数组做hash，下标代表字符，存的值就是出现的次数。
//然后遍历一遍原来的字符流，第一个字符下标存值为1的就是所需的字符
class Solution54 {
    private StringBuilder stringBuilder = new StringBuilder();

    public void Insert(char ch) {
        stringBuilder.append(ch);
    }

    public char FirstAppearingOnce() {
       int[] a = new int[128];//数组中的元素为boolean，比存int更节省空间
        for (int i = 0; i < stringBuilder.length(); i++) {
                a[(int)stringBuilder.charAt(i)]++;
        }
        for (int i = 0; i < stringBuilder.length(); i++) {
            if(a[(int)stringBuilder.charAt(i)] == 1){
                return stringBuilder.charAt(i);
            }
        }
        return '#';
    }
}
