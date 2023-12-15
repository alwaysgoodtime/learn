package classic;


/**
 *
 * @see leetcode.src.main.java.array.Number28
 * 可以实现KMP算法的类
 * 方法名为KMPSearch,且为静态方法
 * @author goodtime
 * @create 2020-02-04 7:38 下午
 */


public class KMP {
    /**
     * 静态方法，直接调用即可，完成KMP查找功能，找到则返回下标，找不到返回-1
     *返回在origin中第一个与pattern匹配的子字符串首地址
     * 稍微改造自然也能做返回所有匹配到的字符串首地址
     * @param origin
     * @param pattern
     * @return
     */
    public static int KMPSearch(String origin, String pattern) {
        if (origin == null || pattern == null) {
            throw new NullPointerException();//健壮性处理
        }
        if (pattern == origin) {//快速判断，它们指向同一个字符串常量的话，就直接返回
            return 0;
        }

//KMP分两步，建立pattern的部分匹配表（最大相同前缀和后缀）
        int[] temp = new int[pattern.length()];//这里面的数据temp[i]，对应的是从0到i的最长匹配子串的长度
        temp[0] = 0;
        //求出来的数组是印度阿三版的，和严蔚敏的不一样。倒是和知乎版本的有异曲同工之妙，他没讲在得到匹配表的时候
        //为什么要i指针前移，然后找以它的最大子串长度值为下标的元素，知乎上的答案讲了，因为那个值代表次长子串，对应元素的值就相当于是和当前字符做
        //匹配，如果匹配不到，再找次长字符
        //和严蔚敏的版本区别是：这里得到的数组右移1位，然后前插-1，然后全部元素+1，就是严蔚敏版本的了
        for (int i = 0, j = 1, count = 0; j < pattern.length(); j++) {
            if (pattern.charAt(j) == pattern.charAt(i)) {
                temp[j] = ++i;
                continue;
            }
            while (pattern.charAt(j) != pattern.charAt(i) && i != 0) {
                if (pattern.charAt(temp[--i]) == pattern.charAt(j)) {//字符串次长的最大匹配就是temp[i--],定位到对应的下标
                    temp[j] = temp[i] + 1;
                    i = temp[j];
                    break;
                }
//                if(i != 0 && i == temp[i]){
//                    //加这个if的原因是aaaaab，对于b来说，它的下标应该为0，而且前面一个a的下标所指的元素就是那个下标
//                    //这种情况如果遍历下去，就是0。（因为temp[i]等于i，说明它们和第1个元素都相同）。如果不加这一条，循环会慢一点，因为前面有--i，也可以一直往下走
//                    temp[j] = 0;
//                    i = 0;
//                    break;
//                }

                i = temp[i];//如果匹配子串不行的话，重置i的值

//                if (i == 0 && pattern.charAt(0) == pattern.charAt(j)){
//                    temp[j] = 1;
//                    i++;
//                    break;
//                }//没必要判断i==0了，因为如果i==0，那么在第一个if中已经执行了判断，这里是做无用功
            }
            //temp中元素的值默认为0，如果正常跳出循环，说明它的最大匹配子串长度就是0。
        }
        for (int t: temp
             ) {
            System.out.println(t);//打印测试
        }

//        第二步，执行判断
        for(int l = 0,m = 0;l < origin.length();){//退出条件就是l遍历完成了
            if(origin.charAt(l) == pattern.charAt(m)){//字符相同，往后走
                l++;
                m++;
            }else if(m != 0){//说明还没返回pattern的开头，属于pattern中间匹配了一次，没匹配对，那么就返回前面串的最长子串，继续遍历
                if(origin.charAt(l) == pattern.charAt(temp[m-1])){
                    l++;
                    m = temp[m-1]+1;//说明子串的最长前缀匹配上了，往下走
                }else {
                    m = temp[m-1];//说明子串没匹配上，注意！！！KMP的顺序是，如果最长子串匹配不上，再回到最长前缀的次长前缀那里，而不是直接返回到子串开头
                }
            }else {
                l++;//m=0，而且没匹配上，接着让origin字符串往下走吧
            }

            if(m == pattern.length()){//说明pattern遍历完了，找到子串了
                return l-m;
            }
        }

        return -1;
    }
}
