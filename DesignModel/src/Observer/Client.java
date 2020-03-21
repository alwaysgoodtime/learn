package Observer;

/**
 * @author goodtime
 * @create 2020-03-10 3:39 下午
 */
public class Client {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData("10","0000");
        CurrentConditions currentConditions = new CurrentConditions();
        weatherData.registerObserver(currentConditions);
        weatherData.registerObserver(new Baidu());
        weatherData.dataChange("10","0701");
        weatherData.removeObserver(currentConditions);
        weatherData.dataChange("10","0701");

    }
}
