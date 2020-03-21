package interview2;

import java.util.*;

/**
 * @author goodtime
 * @create 2020-02-20 6:47 下午
 */
public class SameSet {

    //判断两个list是否相同
    public static boolean sameArr(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size()) {
            return false;
        } else {
            for (int i = 0; i < list1.size(); i++) {
                if (!(list1.get(i).equals(list2.get(i))))
                    return false;
            }
            return true;
        }
    }

    public static void sameSet(List<List<Integer>> list) {
        //这个set用来去重。假如i=2，3，8时三个序列相同。那么只需要遍历i=2时就好了 ，i=3和i=8的时候直接跳过
        Set<Integer> set = new TreeSet<Integer>();
        int isHave = 0;
        //这个isHave用来表示整个List<List<Integer>> list是否用相同的序列
        for (int i = 0; i < list.size() - 1; i++) {
            int flag = 0;
            // 这个flag用来表示 是否有与list.get(i)相同的序列
            if (set.contains(i)) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            //这个builder用于保存满足条件的一次输出结果，一次循环结束之后，输出就可以了。
            // 下面隐去的三个System.out.print就是我最开始的输出方式。但是测试的时候超时了。使用System.out.print需要进行各种切换，保护现场以及恢复现场，非常耗时
            //后来就想到用builder保存结果，使用一次System.out.print。速度快了很多，顺利通过。
            for (int j = i + 1; j < list.size(); j++) {
                if (sameArr(list.get(i), list.get(j)) && flag == 0) {
                    //注意这个if条件和下面那个if条件的区别，这个表示第一次有序列与list.get(i)相同，那么i和j必须都加进去
                    builder.append(i+" "+j);
                    // System.out.print(i + " " + j);
                    flag++;
                    isHave++;
                    set.add(i);
                    set.add(j);
                    continue;
                }
                if (flag != 0 && sameArr(list.get(i), list.get(j))) {
                    //进入这个循环表明至少有两个序列和list.get(i)相同，因为第一次加了i，所以只的每一场只需要加j就好了
                    builder.append(" "+j);
                    // System.out.print(" " + j);
                    set.add(j);
                }
            }
            if (flag != 0) {
                //System.out.println();
                System.out.println(builder.toString());
            }
        }
        if (isHave == 0) {
            System.out.println("no");
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        while (in.hasNextInt()) {
            int num = in.nextInt();
            List<Integer> tmp = new ArrayList<Integer>();
            for (int i = 0; i < num; i++) {
                int n = in.nextInt();
                tmp.clear();
                for (int j = 0; j < n; j++) {
                    tmp.add(in.nextInt());
                }
                list.add(new ArrayList<Integer>(tmp));
            }
            sameSet(list);
            list.clear();
        }
    }
}