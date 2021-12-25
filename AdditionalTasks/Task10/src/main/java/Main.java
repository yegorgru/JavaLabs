public class Main {
    public static void main(String[] args) {
        InfoClass infoClass = new InfoClass("Human");
        infoClass.showClassInfo();
        System.out.println("----------------------------------");
        infoClass = new InfoClass("Student");
        infoClass.showClassInfo();
        System.out.println("----------------------------------");
        infoClass = new InfoClass("Freshman");
        infoClass.showClassInfo();
    }
}