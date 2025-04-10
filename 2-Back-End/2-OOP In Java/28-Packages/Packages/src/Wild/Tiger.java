package Wild; //Subclass in DIFFERENT package.

import Zoo.Animal;

public class Tiger extends Animal
{
    public void printDetails()
    {
        System.out.println(publicInfo); //Allowed
        System.out.println(protectedInfo); //Allowed
        //System.out.println(defaultInfo); // Not Allowed (Because both of them they are not on the same package)
        //System.out.println(privateInfo); //Not Allowed
    }
}
