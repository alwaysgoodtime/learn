package sort;

/**
 * 计数排序，建个hash索引数组，数组的下标就是待排序数组中的值，其中的数字是值出现的次数
 * @author goodtime
 * @create 2020-02-06 1:52 上午
 */
public class CountingSort {
    public static int[] sort(int[] array){

        //第一步，计算要排的数范围，创建数组

        int min = array[0];//存放数组最小值
        int max = array[0];//存放数组最大值

        for (int i = 0; i < array.length; i++) {
            min = min < array[i] ? min : array[i];
            max = max > array[i] ? max : array[i];
        }

        int countArrayLength = max-min+1;

        int[] countArray = new int[countArrayLength];

        //第二步，遍历原来的数组，计算每个数出现的次数

        for (int i = 0; i < array.length; i++) {
            countArray[array[i]-min]++;//减去最小值，是因为建数组时为了节省空间，把最小值映射为了下标0，
            //如果原来数组从0开始，相当于没动
        }

        //第三步，从计数数组中取值，排序原来数组

        for (int l = 0,index = 0; l < countArrayLength; l++) {//index为原来数组的下标
            if(countArray[l] != 0){
                while(countArray[l] != 0) {//倒序取值，是为了保持原来数组的稳定性，保证先进来的相同的数先出去
                    //计数的时候，对于相同的数，最先进来的数默认就是最高位（比如有数组中有3个5，计数数组中第3个5默认就是最先进来的5）
                    array[index++] = l+min;
                    countArray[l]--;
                }
            }

        }
        return array;

    }
}
