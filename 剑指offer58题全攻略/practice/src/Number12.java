import javax.sound.midi.SoundbankResource;

/**
 * @author goodtime
 * @create 2020-01-19 10:25 上午
 */
public class Number12 {
    public static void main(String[] args) {
        Solution12 solution12 = new Solution12();
        double power = solution12.Power(2, -2);
        System.out.println(power);
    }
}
class Solution12 {
    public double Power(double base, int exponent) {
//        double pow = Math.pow(base, exponent);
//        return pow;
//        考虑1、base为0，exponent<0，无效的输入，2、指数为正，3、指数为负,4、指数为0四种情况即可

        /*剑指书中细节：
         *1.当底数为0且指数<0时
         *会出现对0求倒数的情况，需进行错误处理，设置一个全局变量；
         *2.判断底数是否等于0
         *由于base为double型，不能直接用==判断
         *3.优化求幂函数
         *当n为偶数，a^n =（a^n/2）*（a^n/2）
         *当n为奇数，a^n = a^[(n-1)/2] * a^[(n-1)/2] * a
         *时间复杂度O(logn)
         */

         if(Math.abs(base)<0.00001 && exponent < 0){
             throw new RuntimeException("无效的输入");//0的负次方无意义
         }
         if(Math.abs(base) < 0.00001){
             return 0;//直接返回0
         }
         if(exponent == 1){
             return base;//double有精度，不能直接和0、1比较
         }
         if(exponent == 0){
             return 1;
         }
//            快速幂
         int e = exponent > 0 ? exponent : -exponent;
         double res = 1;//存放返回函数
        while (e != 0) {
            res = (e & 1) != 0 ? res * base : res;//判断最后一位是否为1，如果是，则乘1次base
            base *= base;// base 的2次方、4次方、16次方...
            e = e >> 1;//因为是正数，这里无符号右移，高位补0
         }
        if(exponent > 0){
            return res;
        }else {
            return 1/res;
        }
    }
    }
