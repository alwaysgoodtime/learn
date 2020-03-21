package Observer;

/**
 * @author goodtime
 * @create 2020-03-10 3:49 下午
 */
public class Baidu implements Observer {

    private String temperature;
    private String date;

    @Override
    public void update(String temperature, String date) {
        this.temperature = temperature;
        this.date = date;
        System.out.println("百度网站的气温"+temperature);
        System.out.println("百度网站的date"+date);
    }
}
