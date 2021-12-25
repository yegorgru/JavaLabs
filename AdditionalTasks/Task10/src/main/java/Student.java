public class Student implements Human{
    private String name;

    public void speak() {
        System.out.println("Hi, where can I find freebie?");
    }

    public void run() {
        System.out.println("Hi, I'm running");
    }

    public String getName(){
        return "Hi, my name is " + name;
    }
}