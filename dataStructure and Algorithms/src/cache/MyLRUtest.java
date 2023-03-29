package cache;

/**
 * @author goodtime
 * @create 2020-03-26 1:38 上午
 */
public class MyLRUtest {
    public static void main(String[] args) {
        MyLRU<String, String> lru = new MyLRU<>(3);
        lru.put("1","1");
        lru.put("2","2");
        lru.put("3","3");
        lru.put("4","4");
        lru.get("2");
        lru.put("5","5");
        lru.put("6","6");
        System.out.println(lru.get("7"));
        lru.put(null,"1");
        lru.put("1",null);
        System.out.println(lru.get(null));
        System.out.println(lru.get("1"));
        lru.remove(null);
        System.out.println(lru.get(null));
        lru.clear();
        System.out.println(lru.get("1"));
    }
}
