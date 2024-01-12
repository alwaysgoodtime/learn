package interview;


import java.util.*;


/**
 * @author goodtime
 * @create 2020-02-28 8:25 下午
 */
public class Evil {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int k = 0;
        if(sc.hasNext()) {
            k = sc.nextInt();//k为值
        }

        //第一步，找到4-1000000所有的第一类邪恶数字
        ArrayList<Integer> a = new ArrayList();
        String l = "";
        for (int i = 4; i <= 1000000; i++) {
            l = String.valueOf(i);
            String s = l.replaceAll("4", "");
            String s1 = s.replaceAll("7", "");
            if (s1.length() == 0) {
                a.add(i);
            }
        }
        //第二步，找到第一类邪恶数字可以加得的第二类邪恶数字，并用map保存起来


        Set<Integer> b = new LinkedHashSet<>();

        for (int j = 0; j < a.size(); j++) {
            for (int m = j; m < a.size(); m++) {
                b.add(a.get(j) + a.get(m));
            }
        }

        //第三步，查看输入的数字

        if (a.contains((Integer) k)) {
            System.out.println(1);
        } else if (b.contains((Integer) k)) {
            System.out.println(2);
        }else{
            System.out.println(0);
        }
    }
}
