//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        int x = 5; //Literal
        int y = 012; //Octal
        System.out.println(y);
        y = 0xfff; //Hexadecimal
        System.out.println(y);
        y = 0Xfff;
        System.out.println(y);
        y = 0b10110;//Binary
        System.out.println(y);
        y = 10110;
        System.out.println(y);
        y = 100_555_6789;
        System.out.println(y);
        float z = 1.56709f;
        z = 156709E-05f; //Scientific Notation.
        System.out.println(z);
        char Char = '\u1101'; //The Unicode Escape sequence
        System.out.println(Char);
        String Str = "\u0438"; //It is used to print the character of these hexadecimal.
        System.out.println(Str);
    }
}