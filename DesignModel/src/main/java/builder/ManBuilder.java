package builder;

/**
 * @author goodtime
 * @create 2023-12-23 02:34
 */
public class ManBuilder extends Builder {



    @Override
    void buildHeart() {
        System.out.println("要心");
        people.heart = "心";
    }

    @Override
    void buildHands() {
        System.out.println("要手");
        people.hands = "手";
    }

    @Override
    void buildLegs() {
        System.out.println("要脚");
        people.legs = "脚";
    }

    public ManBuilder(String brain) {
        people = new People(brain);
    }


    @Override
    People create() {
        return people;
    }
}
