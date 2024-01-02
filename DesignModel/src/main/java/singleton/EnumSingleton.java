package singleton;

/**
 * @author goodtime
 * @create 2023-12-23 02:17
 */
public enum EnumSingleton {

    SINGLE;
    private String name = "signle";

    public static EnumSingleton getSingle() {
        return SINGLE;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
