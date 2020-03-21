package sort;

/**
 * 基数排序，复杂度O(nXk),空间复杂度O（n+k）
 *
 * @author goodtime
 * @create 2020-02-05 11:56 下午
 */
public class RadixSort {
    public static int[] sort(int[] array) {
        //创建二维数组做桶，一般是10个一维数组（10个桶，分别对应0-9），每个一维数组的长度是array.length
        //如果有负数，可以把负数和正数分别排序，各自排序好后再合到一起
        int[][] bucket = new int[10][array.length];
        int[] bucketElementCount = new int[10];//数组中分别存每个桶对应的值有多少，方便遍历
        int max = array[0];//找到数组中最大的数，确定装桶和遍历几次桶
        for (int a : array) {
            max = a > max ? a : max;
        }
        int maxLength = (max+"").length();//求得要遍历的次数，也就是数组中最大数的位数

        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < array.length; j++) {
                int a = array[j]/(int)Math.pow(10,i)%10; //取到每次遍历时，每个数对应的位数，个位、十位...
                bucket[a][bucketElementCount[a]++] = array[j];
            }
            //遍历每个桶，取出来对应的值放到原来数组中，进行初次排序
            for (int l = 0,index = 0; l < 10; l++) {//index为原来数组对应的下标
                if(bucketElementCount[l] != 0){
                    for (int m = 0; m < bucketElementCount[l]; m++) {
                        array[index++] = bucket[l][m];
                    }
                    bucketElementCount[l] = 0;//取完后清空每个桶的计数器即可，桶不用清空，后面的数据会覆盖
                    //而且不被覆盖的数据也不会被取到，由计数器控制取还是不取
                }
            }
        }

        return array;
    }
}
