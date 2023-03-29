package interview2;

import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author goodtime
 * @create 2020-02-21 9:18 下午
 */
public class Birthday {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {
//            int a = in.nextInt();
//            int b = in.nextInt();
//            int c = in.nextInt();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy:MM:dd");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        System.out.println(format);
//        }
    }
}

