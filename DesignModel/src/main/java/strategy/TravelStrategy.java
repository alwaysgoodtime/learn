package strategy;

/**
 * @author goodtime
 * @create 2023-12-24 16:43
 */
public interface TravelStrategy {

    double calculateTime(int distance);


    double calculatePrice(int distance);

}
