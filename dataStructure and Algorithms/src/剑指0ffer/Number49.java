/**
 * @author goodtime
 * @create 2020-01-26 10:01 下午
 */
public class Number49 {
    public static void main(String[] args) {
        Solution49 solution49 = new Solution49();
        int i = solution49.StrToInt("+123");
        System.out.println(i);
        int j = -2147483648;
        System.out.println(-j);


    }
}

/**
 * 边界条件：
 * 数据上下溢出(未完成)
 * 空字符串
 * 只有正负号
 * 有无正负号
 * 错误标志输出
 */
class Solution49 {
    public int StrToInt(String str) {
        int b = 0;
        if(str == null || str.length() == 0){
            return 0;
        }
        if(str.length() == 1 && (str == "+" || str == "-")){
            return 0;
        }
        char[] chars = str.toCharArray();
        if(chars[0] == 43){
            for (int i = 1; i <= chars.length-1; i++) {//从char数组末尾到开头，也就是从个位到高位，
                //如果首位是+，忽略第一个字符
                int a = chars[i] - 48;
                if(a > 9 || a < 0){
                    return 0;
                }
                int c = b;
                b = c*10+a;//让每个字符，变成数字，然后乘以位数

                if(b < c*10){
                    return 0;//b小于b乘以10，说明b超过了2的31次方减1的值，返回0
                }
                if((c*10 +a)<c*10){
                    return 0;//说明b*10已经在超限的边缘，+a之后直接超限，返回0
                }
            }
        }
        else if(chars[0] == 45){
            for (int i = 1; i <= chars.length-1; i++) {//从char数组末尾到开头，也就是从个位到高位，
                //如果首位是-，忽略第一个字符
                int a = chars[i] - 48;
                if(a > 9 || a < 0){
                    return 0;
                }
                int c = b;
                b = c*10-a;//让每个字符，变成数字，然后乘以位数
                if(b > c*10){
                    return 0;//b小于b乘以10，说明b超过了2的31次方减1的值，返回0
                }
                if((c*10-a)>c*10){
                    return 0;//说明b*10已经在超限的边缘，+a之后直接超限，返回0
                }
            }

            //   b = -b;
            // 先取正值，然后再加个负数
            // 这种方法不行，int值，范围是2的-31次方到2的31次方-1，所以直接让b从一开始就为负值，而非从正值转负值。如果b为
            // 2的31次方，其实就是b的2的负31次方。
            //        int j = -2147483648;
            //        System.out.println(-j);
            // -j还是2的负31次方。
            // 如果b一开始是正数，最后加负号，那么b就无法取值为2147483648，这样会提示数值过大。
            //但b确实可以为-2147483648，所以这样就会少一个负数，本来可以取值，但是会返回0。。
        }else {
            for (int i = 0; i <= chars.length-1; i++) {
                int a = chars[i] - 48;
                if(a > 9 || a < 0){
                    return 0;
                }
                int c = b;
                b = c*10+a;//让每个字符，变成数字，然后乘以位数
                if(b < c*10){
                    return 0;//b小于b乘以10，说明b超过了2的31次方减1的值，返回0
                }
                if((c*10 +a)<c*10){
                    return 0;//说明b*10已经在超限的边缘，+a之后直接超限，返回0
                }
            }
        }
        return b;
    }
}
