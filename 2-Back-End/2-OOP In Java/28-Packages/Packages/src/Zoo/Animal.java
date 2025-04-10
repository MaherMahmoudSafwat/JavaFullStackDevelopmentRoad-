package Zoo;

public class Animal
{
    /*
    ✅ Key Points for Subclass Access:
    Access Modifier	Subclass in Same Package	Subclass in Different Package
    public	✅	✅
    protected	✅	✅
    no modifier	✅	❌
    private	❌	❌
    */
    public String publicInfo = "I am public";
    protected String protectedInfo = "I am protected";
    String defaultInfo = "I am package-private";
    private String privateInfo = "I am private";

    public void showInfo()
    {
        System.out.println(publicInfo);
        System.out.println(protectedInfo);
        System.out.println(defaultInfo);
        System.out.println(privateInfo);
    }
}
