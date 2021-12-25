
public class Human implements java.io.Serializable {
    private int age;
    private String name;

    public Human(int age, String name)
    {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}
