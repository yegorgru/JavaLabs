import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

public class InfoClass {
    private final String classPath;
    private final Class<?> loadedClass;
    private static final String SEPARATOR = "   ";

    public InfoClass(String classPath) {
        CustomClassLoader myLoader = new CustomClassLoader();
        this.classPath = classPath;
        this.loadedClass = myLoader.findClass(classPath);
    }

    public void showClassInfo(){
        System.out.println(classPath + ":");
        printTitle();
        printFields();
        printMethods();
    }

    private void printTitle() {
        System.out.println("Modifiers: " + Modifier.toString(loadedClass.getModifiers()));
        if(loadedClass.getSuperclass() != null){
            System.out.println("Extends: " + loadedClass.getSuperclass().getName());
        } else {
            System.out.println("Doesn't extend any class.");
        }
        Class<?>[] interfaceClasses = loadedClass.getInterfaces();
        if(interfaceClasses.length == 0){
            System.out.println("Doesn't implement any interfaces.");
        }
        else{
            System.out.println("Implements interfaces: ");
            for(Class<?> interfaceClass: interfaceClasses){
                System.out.println(SEPARATOR + Modifier.toString(interfaceClass.getModifiers()) + " " +
                        interfaceClass.getName());
            }
        }
    }

    private void printFields(){
        Field[] listOfFields = loadedClass.getDeclaredFields();
        if(listOfFields.length == 0){
            System.out.println("No fields. ");
            return;
        } else{
            System.out.println("Fields: ");
        }
        for(Field field: listOfFields){
            System.out.println(SEPARATOR + Modifier.toString(field.getModifiers()) + " " +
                    field.getType().getName() + " " + field.getName());
        }
    }

    private void printMethods(){
        Method[] listOfMethods = loadedClass.getDeclaredMethods();
        if(listOfMethods.length == 0){
            System.out.println("No methods. ");
            return;
        } else{
            System.out.println("Methods: ");
        }
        for(Method method: listOfMethods){
            System.out.println(SEPARATOR + Modifier.toString(method.getModifiers()) + " " +
                    method.getReturnType().getName() + " " + method.getName());
            if(method.getParameterTypes().length > 0) {
                System.out.println(SEPARATOR + "parameter types: " +
                        Arrays.stream(method.getParameterTypes())
                                .map(Class::getName)
                                .collect(Collectors.joining(",")));
            } else {
                System.out.println(SEPARATOR + SEPARATOR+ "no parameters");
            }
        }
    }
}