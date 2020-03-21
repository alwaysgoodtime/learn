package classic;

import java.util.*;

/**
 * 贪心算法的经典案例，发送广播的问题
 *
 * @author goodtime
 * @create 2020-02-07 11:53 上午
 */
public class Broadcast {

    /**
     * @param broadcast key为广播名字，value为广播能覆盖到的城市target
     * @param target 要覆盖的城市
     *@return 装的是需要的广播名字
     */
    public static String[] boradcast(HashMap<String,HashSet<String>> broadcast,String[] target) {
        List<String> cities = new ArrayList(Arrays.asList(target));//方便后续操作目标城市
        //一定要这样建才行，直接Arrays.asList出来的List是一个AbstractList，不能进行add和remove操作
        //会报java.lang.UnsupportedOperationException这个运行时异常
        String maxkey = null;//装每次循环后第一个找到的覆盖最多未覆盖城市的广播名字
        int maxsize = 0;//统计每次装完广播后最多未覆盖城市的数目
        HashSet tmp = new HashSet();//装每次循环后可以覆盖的城市
        Set<String> strings = broadcast.keySet();//string中装的是broadcast中所有的key，也就是所有广播的名字
        //方便一会儿遍历
        ArrayList<String> rt = new <String>ArrayList();//存放要返回的广播名字


        //当cities（未覆盖城市）不为空时，不停遍历HashMap，找到覆盖最多城市的广播
        while (cities.size() != 0) {

            //遍历完一次，就让maxsize = 0; 重新开始下一次循环
            maxsize = 0;

            //遍历
            for (String name : strings) {//不停遍历broadcast
                HashSet hashSet = broadcast.get(name);
                tmp.addAll(hashSet);
                tmp.retainAll(cities);
                if (tmp.size() > maxsize) {
                    maxkey = name;
                    maxsize = tmp.size();
                }
                //每遍历完一小次，清空tmp，准备放入下个广播的城市
                tmp.clear();
            }

            //第一次遍历的最优解
            HashSet aim = broadcast.get(maxkey);//本轮循环后找到的最优广播
            cities.removeAll(aim);//把cities中最优广播已覆盖的城市取掉，方便进入下次循环。
            rt.add(maxkey);
        }

        //ArrayList转换成Array时传个参数，方便转换
        String[] solution = rt.toArray(new String[0]);//得到的结果返回
        return solution;
    }
}
