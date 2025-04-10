package Zoo;

public class Dog extends Animal
{
    public void printDetails()
    {
        System.out.println(publicInfo);    // Allowed
        System.out.println(protectedInfo); // Allowed
        System.out.println(defaultInfo);   // Allowed (Same Package)
        //System.out.println(privateInfo); //Not allowed
    }
}
