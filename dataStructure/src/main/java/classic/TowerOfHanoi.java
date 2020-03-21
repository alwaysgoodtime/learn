package classic;

/**
 * 汉诺塔问题，分治算法的经典案例
 * 这个接口，实现了从a到c的移动，b是中介的塔
 * 数组下标小的是大盘子，下标大的是小盘子
 * @author goodtime
 * @create 2020-02-07 12:28 上午
 */
public class TowerOfHanoi {
    private static int i;
    private static int[] atemp;
    private static int[] ctemp;

    public static void main(String[] args) {
        towerOfHanoi(3,'a','b','c');
    }
    public static void towerOfHanoi(int num, char a,char b, char c){
        if(num == 1){
            System.out.println("第1个盘从"+a+"到"+c);
            return;
        }
        else{
            //第一步，把a中上面所有的盘，从a借助c移动到b；
            towerOfHanoi(num-1,a,c,b);
            //第二步，把a中最下面一个盘，从a借助b移动到c；
            System.out.println("第"+num+"个盘从"+a+"到"+c);
            //第三步，把b中所有的盘，从b借助a移动到c；
            towerOfHanoi(num-1,b,a,c);
            return;
        }


    }
}
