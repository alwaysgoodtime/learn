package interview2;

import java.util.Scanner;

/**
 * 计算生日问题
 * @author goodtime
 * @create 2020-02-20 6:56 下午
 */
public class FirstProject {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int f = 0;
        int g = 0;
        int days = 0;
        while (in.hasNextInt()) {
            a = in.nextInt();
            b = in.nextInt();
            c = in.nextInt();
            //2018.8.1
            d = 2018 - a;
            e = b - 8;
            g = c - 1;
            int ny = 365;
            //先计算有几年
            days = ny * d + 1 * (d / 4);
            if (d >= 2) {
                d += 1;
            }
            if (a % 4 == 0) {
                f = 1;
            }
            //计算月份
            if (e >= 0) {
                if (e == 0) {
                    days += g;
                }
                if (e == 1) {
                    days += g + 31;
                }
                if (e == 2) {
                    days += g + 31 + 30;
                }
                if (e == 3) {
                    days += g + 31 + 30 + 31;
                }
                if (e == 4) {
                    days += g + 31 + 30 + 31 + 30;
                }
            }
            if (e < 0) {
                if (e == -1) {
                    days += (32 - c);
                }
                if (e == -2) {
                    days += (31 + 31 - c);
                }
                if (e == -3) {
                    days += (31 + 30 + 32 - c);
                }
                if (e == -4) {
                    days += (31 + 30 + 31 + 31 - c);
                }
                if (e == -5) {
                    days += (31 + 30 + 31 + 30 + 32 - c);
                }
                if (e == -6) {
                    if (f == 1) {
                        days += (31 + 30 + 31 + 30 + 31 + 30 - c);
                    } else {
                        days += (31 + 30 + 31 + 30 + 31 + 29 - c);
                    }
                }
                if (e == -7) {
                    if (f == 1) {
                        days += (31 + 30 + 31 + 30 + 31 + 29 + 32 - c);
                    } else {
                        days += (31 + 30 + 31 + 30 + 31 + 28 + 32 - c);
                    }
                }
            }
            System.out.println(days);
        }
    }
}


