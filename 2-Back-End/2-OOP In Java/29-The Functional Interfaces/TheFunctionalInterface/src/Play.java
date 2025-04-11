public interface Play
{
    final int x =5;
    static void CallStaticMethod()
    {
        CallAPrivateMethod();
        System.out.println("This is a static method");
    }
    private static void CallAPrivateMethod()
    {
        System.out.println("This is a private method");
    }
    void PlayGames();
    void CalculateResult();
    void ChooseTheTeams();

    default void TheDefaultMethod()
    {
        CallAPrivateMethod();
    }
}
