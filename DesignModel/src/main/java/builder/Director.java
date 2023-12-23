package builder;

/**
 * @author goodtime
 * @create 2023-12-23 02:47
 */
public class Director {

    Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }

    public void create() {
        builder.buildHands();
        builder.buildHeart();
        builder.buildLegs();
    }

}
