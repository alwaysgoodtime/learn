import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author goodtime
 * @create 2024-01-02 14:40
 */
public class TestStream {
    public static void main(String[] args) {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(3, 4);
        map.put(2, 3);

        Stream<Map.Entry<Integer, Integer>> stream = map.entrySet().stream();
        stream.filter(a -> a.getKey() > 2).forEach(a -> System.out.println(a.getKey()));
        System.out.println(map.size());
        Stream<Map.Entry<Integer, Integer>> stream2 = map.entrySet().stream();
        stream2.sorted(((o1, o2) -> o1.getValue() - o2.getValue())).forEach(a -> System.out.println(a.getKey()));

        Stream<Map.Entry<Integer, Integer>> stream3 = map.entrySet().stream();
        stream3.sorted(((o1, o2) -> o1.getValue() - o2.getValue())).skip(2).forEach(a -> System.out.println(a));


        Map<Integer, List<String>> listMap = new HashMap<>();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("ddd");
        listMap.put(1, strings);

        ArrayList<String> strings2 = new ArrayList<>();
        strings2.add("bbb");
        strings2.add("bbb");
        listMap.put(2, strings2);

        ArrayList<String> strings3 = new ArrayList<>();
        strings3.add("aaa");
        strings3.add("ddd");
        listMap.put(3, strings3);

        Stream<Map.Entry<Integer, List<String>>> stream4 = listMap.entrySet().stream();
        stream4.map(a -> a.getValue()).forEach(a -> System.out.println(a.get(0)));
        Stream<Map.Entry<Integer, List<String>>> stream5 = listMap.entrySet().stream();
        stream5.flatMap(a -> a.getValue().stream()).distinct().forEach(a -> System.out.println(a));

        Stream<Map.Entry<Integer, List<String>>> stream6 = listMap.entrySet().stream();
        System.out.println(stream6.map(a -> a.getValue()).count());
        Stream<Map.Entry<Integer, List<String>>> stream7 = listMap.entrySet().stream();
        System.out.println(stream7.flatMap(a -> a.getValue().stream()).max((o1, o2) -> {
            for (int i = 0; i < o1.length(); i++) {
                char c = o1.charAt(i);
                if (o2.length() <= i) {
                    return 1;
                }

                char c1 = o2.charAt(i);
                if (c == c1) {
                    continue;
                } else {
                    return c - c1;
                }
            }
            return -1;
        }).get());

        Stream<Map.Entry<Integer, List<String>>> stream8 = listMap.entrySet().stream();
        System.out.println(stream8.flatMap(a -> a.getValue().stream()).distinct().collect(Collectors.toMap(
                str -> str, str -> str.length())));

        Stream<Map.Entry<Integer, List<String>>> stream9 = listMap.entrySet().stream();
        System.out.println(stream9.map(a -> a.getKey()).anyMatch(a -> a >= 3));

        Stream<Map.Entry<Integer, List<String>>> stream10 = listMap.entrySet().stream();
        System.out.println(stream10.map(a -> a.getKey()).allMatch(a -> a >= 1));

        Stream<Map.Entry<Integer, List<String>>> stream11 = listMap.entrySet().stream();
        System.out.println(stream11.map(a -> a.getKey()).noneMatch(a -> a >= 3));

        Stream<Map.Entry<Integer, List<String>>> stream12 = listMap.entrySet().stream();
        System.out.println(stream12.map(Map.Entry::getKey).findAny());

        Stream<Map.Entry<Integer, List<String>>> stream13 = listMap.entrySet().stream();
        System.out.println(stream13.map(Map.Entry::getKey).sorted().filter(a -> a >= 2).findAny());

        Stream<Map.Entry<Integer, List<String>>> stream14 = listMap.entrySet().stream();
        System.out.println(stream14.map(a -> a.getKey()).sorted().filter(a -> a >= 3).findFirst());

        Stream<Map.Entry<Integer, List<String>>> stream15 = listMap.entrySet().stream();
        System.out.println(stream15.map(a -> a.getKey()).sorted().reduce(Math::max));

        Optional<Integer> o = Optional.ofNullable(null);
        Optional<String> s = o.map(o1 -> "aaa");
        System.out.println(s);


        Stream<Integer> stream16 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        stream16.filter(e -> e < 5).reduce(Integer::sum).orElse(11);
        Stream<Integer> stream17 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(stream17.parallel().peek(t -> System.out.println(t + Thread.currentThread().getName())).filter(e -> e < 6).reduce(Integer::sum).orElse(10));

        List<String> a = new ArrayList<>();
        a.parallelStream().forEach(System.out::println);
    }
}
