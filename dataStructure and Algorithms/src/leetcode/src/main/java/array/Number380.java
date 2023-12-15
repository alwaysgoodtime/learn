package leetcode.src.main.java.array;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * https://leetcode.cn/problems/insert-delete-getrandom-o1/
 *
 * @author goodtime
 * @create 2023-12-03 21:04
 */
public class Number380 {
    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(0));
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.remove(0));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.remove(1));
        System.out.println(randomizedSet.getRandom());

    }
}

/**
 * 因为insert和remove要求o(1)复杂度，就用hashmap来模拟
 *
 * hashmap存储的value是每个元素的下标
 *
 * 但是获取随机数字hashmap不能达到o(1)复杂度，就用hashmap+arraylist来完成
 */
class RandomizedSet {

    private HashMap<Integer, Integer> map = new HashMap<>();
    private int size = 0;
    private ArrayList<Integer> list = new ArrayList();
    private Random random = new Random();


    public RandomizedSet() {

    }

    public boolean insert(int val) {
        Integer value = map.get(val);
        if (value != null) {
            return false;
        }
        map.put(val, size);
        list.add(val);
        size++;
        return true;
    }


    public boolean remove(int val) {
        Integer index = map.get(val);
        if (index == null) {
            return false;
        }

        //这里需要处理list中remove后留下的空，于是把最后一个数删掉后补进原来的位置，来保证顺序不乱
        if (!index.equals(list.size() - 1)) {
            Integer element = list.get(list.size() - 1);
            map.put(element, index);
            list.remove(index.intValue());
            list.remove(list.size() - 1);
            list.add(index, element);
        } else {
            list.remove(index.intValue());
        }

        map.put(val, null);
        size--;
        return true;
    }

    public int getRandom() {
        int num = random.nextInt(size);
        return list.get(num);

    }
}
