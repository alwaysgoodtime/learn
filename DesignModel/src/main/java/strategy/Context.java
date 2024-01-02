package strategy;

/**
 * @author goodtime
 * @create 2023-12-24 16:47
 */
public class Context {

    private TravelStrategy travelStrategy;

    public Context(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    public void setStrategy(TravelStrategy travelStrategy) {
        this.travelStrategy = travelStrategy;
    }

    public double getTime(int distance) {
        return travelStrategy.calculateTime(distance);
    }

    public double getPrice(int distance){
        return travelStrategy.calculatePrice(distance);
    }

}
