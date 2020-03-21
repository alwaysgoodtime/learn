package search;

/**
 * 顺序查找就是暴力遍历，好处是数组不要求是有序的，这是比其他查找好的地方
 * 其他查找要是想用的话，首先得先给数组排序
 * 时间复杂度也最高，就是O(n)
 * @author goodtime
 * @create 2020-02-06 12:49 下午
 */
public class SequentialSearch {

    /**
     * 接口统一定义，传入数组和目标值，返回目标值的下标（如果有重复值，就返回数组中此值第一次出现的下标）
     * @param array
     * @param target
     * @return 没有找到值，统一返回-1;
     */
    public static int search(int[] array,int target){
        for (int i = 0; i < array.length; i++) {
            if(target == array[i]){
                return i;
            }
        }
        return -1;
    }
}
