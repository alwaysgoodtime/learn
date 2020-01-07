package cn.itcast.travel.util;

/**
 * @author goodtime
 * @create 2020-01-07 12:33 下午
 */
public class test {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://localhost:8080/travel/user/register");
        int i = stringBuilder.lastIndexOf("/");
        stringBuilder.delete(i,i+10);
        stringBuilder.append("/activateUser");
        String s = stringBuilder.toString();
        System.out.println(s);
    }

}
