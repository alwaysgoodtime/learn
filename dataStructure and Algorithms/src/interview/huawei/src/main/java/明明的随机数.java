package interview.huawei.src.main.java;

import java.util.*;

/**
 * 描述
 * 明明生成了N个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
 *
 * 输入描述：
 * 第一行先输入随机整数的个数 N 。 接下来的 N 行每行输入一个整数，代表明明生成的随机数。 具体格式可以参考下面的"示例"。
 * 输出描述：
 * 输出多行，表示输入数据处理后的结果
 * @author goodtime
 * @create 2023-12-21 15:11
 */
public class 明明的随机数 {

    /**
     * 可以用hashmap+list实现，也可以用TreeSet实现去重和排序
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int count = in.nextInt();
            int[] hash = new int[500];
            ArrayList<Integer> list = new ArrayList<>();
            for(int i = 0; i < count; i++){
                while(in.hasNextInt()){
                    int a = in.nextInt();
                    if(hash[a] == 1){
                        continue;
                    }
                    hash[a] = 1;
                    list.add(a);
                }
            }

            HashSet<Integer> a = new HashSet<>();

            list.sort((o1,o2)-> o1 - o2);
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i));
            }

        }
    }
}
