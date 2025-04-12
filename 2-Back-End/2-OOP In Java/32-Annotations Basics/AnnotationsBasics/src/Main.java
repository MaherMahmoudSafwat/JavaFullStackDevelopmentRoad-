import java.lang.reflect.Method;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws NoSuchMethodException {
        /*
         Annotations are metadata tags that provide extra information about:
         Classes
         Methods
         Fields
         Parameters
         They do not change code behavior directly but are used by:
         Compilers (e.g., @Override)
         Frameworks (e.g., Spring’s @Autowired)
         Runtime environments (via Reflection)
        */
        Method MyMethod = MyClass.class.getMethod("MyMethod");
        if(MyMethod.isAnnotationPresent(MyAnnotation.class))
        {
            MyAnnotation Annotation = MyMethod.getAnnotation(MyAnnotation.class);
            System.out.println(Annotation.Value());
            System.out.println(Annotation.Number());
        }
        /*
        Java provides three retention policies via RetentionPolicy enum:
        Retention Policy	Availability	Use Cases
        SOURCE	Discarded after compilation	- @Override, @SuppressWarnings (compile-time checks)
        CLASS (Default)	In .class file but not runtime	- Rarely used (some bytecode tools)
        RUNTIME	Available at runtime (Reflection)	- Spring (@Autowired), JUnit (@Test), Hibernate (@Entity)
        */
    }
}
