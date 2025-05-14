
class A
{
    public A()
    {
        System.out.println("Hello World I'm Constructor A");
    }
    public void Show()
    {
        System.out.println("I'm a Method");
    }
}
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        A A1 = new A();
        A1.Show();

        new A(); //Anonymous Object.
        new A().Show();
    }
}