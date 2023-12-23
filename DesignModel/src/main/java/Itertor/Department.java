package Itertor;

/**
 * @author goodtime
 * @create 2020-03-10 1:27 上午
 */
public class Department {
    private String name;
    private String description;

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Department(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
