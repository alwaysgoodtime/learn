import java.util.ArrayList;

/**
 * @author goodtime
 * @create 2020-01-24 12:28 上午
 */
public class Number40 {
    public static void main(String[] args) {
        int[] a ={1,1,4,5,4,6,6,3};
        int[] b = new int[1];
        int[] c = new int[1];
        Solution40 solution40 = new Solution40();
        solution40.FindNumsAppearOnce(a,b,c);
        System.out.println(b[0]);
        System.out.println(c[0]);

    }
}

//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果，这里用的还是哈希的思想，不过是用动态数组实现，空间复杂度为O（n），即算法所需的额外空间
class Solution40 {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        ArrayList<Integer> noRepeat = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            if(noRepeat.contains(array[i])){
                noRepeat.remove((Integer)array[i]);//这里千万注意，如果不把int转成Integer，默认删除的是那个数字的下标，
                // 而非和那个数字相等的对象
            }else{
                noRepeat.add(array[i]);
            }
        }
        num1[0] = noRepeat.get(0);
        num2[0] = noRepeat.get(1);
    }
}

//还有一种异或的思路：这样空间复杂度为O（1），省空间
//首先：位运算中异或的性质：两个相同数字异或=0，一个数和0异或还是它本身。
//当只有一个数出现一次时，我们把数组中所有的数，依次异或运算，最后剩下的就是落单的数，因为成对儿出现的都抵消了。
//依照这个思路，我们来看两个数（我们假设是AB）出现一次的数组。我们首先还是先异或，剩下的数字肯定是A、B异或的结果，
// 这个结果的二进制中的1，表现的是A和B的不同的位。我们就取第一个1所在的位数，假设是第3位，接着把原数组分成两组，
// 分组标准是第3位是否为1。如此，相同的数肯定在一个组，因为相同数字所有位都相同，而不同的数，肯定不在一组。
// 然后把这两个组按照最开始的思路，依次异或，剩余的两个结果就是这两个只出现一次的数字。
//判断最后得到的数不为1的最低位的代码：
//        for(index = 0; index < 32; index++){
//            if((sum & (1 << index)) != 0) break;
//        }