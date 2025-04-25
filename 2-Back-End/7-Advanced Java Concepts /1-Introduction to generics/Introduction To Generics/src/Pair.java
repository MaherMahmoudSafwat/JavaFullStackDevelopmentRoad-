//Generics
public class Pair <T>
{
    T Pair1;
    T Pair2;
    Pair(T Pair1,T Pair2)
    {
        this.Pair1 = Pair1;
        this.Pair2=Pair2;
    }

    public void setPair1(T pair1) {
        Pair1 = pair1;
    }

    public T getPair1() {
        return Pair1;
    }

    public void setPair2(T pair2) {
        Pair2 = pair2;
    }

    public T getPair2() {
        return Pair2;
    }
}
