package strategy;

/**
 * @author goodtime
 * @create 2023-12-24 16:44
 */
public class WalkStrategy implements TravelStrategy{

    @Override
    public double calculateTime(int distance) {
        return distance / 60.0;
    }

    @Override
    public double calculatePrice(int distance) {
        return 0;
    }
}
