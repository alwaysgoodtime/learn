package builder;

/**
 * @author goodtime
 * @create 2023-12-23 02:36
 */
public class People {

    public String brain;
    public String heart;
    public String hands;
    public String legs;

    public People(String brain) {
        this.brain = brain;
    }

    @Override
    public String toString() {
        return "People{" +
                "brain='" + brain + '\'' +
                ", heart='" + heart + '\'' +
                ", hands='" + hands + '\'' +
                ", legs='" + legs + '\'' +
                '}';
    }
}
