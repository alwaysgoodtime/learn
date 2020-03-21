package Observer;

/**
 * @author goodtime
 * @create 2020-03-10 3:18 下午
 */
public class CurrentConditions implements Observer {

    private String temperature;
    private String date;

    @Override
    public void update(String temperature, String date) {
        this.temperature = temperature;
        this.date = date;
        System.out.println(temperature+date);
    }
}
