@FunctionalInterface
public interface Converter <T,R>
{
    //I can apply Lambda expression here.
    R Convert(T X);
}
