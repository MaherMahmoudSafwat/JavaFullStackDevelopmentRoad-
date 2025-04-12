//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        /*
        Annotations are metadata tags that provide additional information about Java code
        (classes, methods, fields, etc.) without affecting execution directly. They start with @ and are used by:

        Compilers (e.g., @Override)
        Frameworks (e.g., Spring’s @Autowired)
        Tools (e.g., JUnit’s @Test)
        */
        @SuppressWarnings("unused")
        int Number=5;
        //TheDeprecatedMethod();
    }
    @Deprecated
    public static void TheDeprecatedMethod()
    {
        System.out.println("This is a deprecated method.");
    }
    public class Car
    {
        @Override
        public String toString()
        {
            return "";
        }
    }
}
