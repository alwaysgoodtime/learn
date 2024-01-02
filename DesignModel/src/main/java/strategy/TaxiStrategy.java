package strategy;

/**
 * @author goodtime
 * @create 2023-12-24 16:45
 */
public class TaxiStrategy implements TravelStrategy {
    @Override
    public double calculateTime(int distance) {
        return distance / 300.0;
    }

    @Override
    public double calculatePrice(int distance) {
        return distance / 10.0;
    }
}
