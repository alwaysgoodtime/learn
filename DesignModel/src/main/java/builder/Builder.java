package builder;

/**
 * @author goodtime
 * @create 2023-12-23 01:46
 */
public abstract class Builder {

     People people;

     abstract void buildHeart();
     abstract void buildHands();
     abstract void buildLegs();
     abstract People create();

}
