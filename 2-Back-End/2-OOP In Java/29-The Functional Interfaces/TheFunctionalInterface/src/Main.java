//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        Converter<String,Integer>Convert = new Converter<String, Integer>() {
            @Override
            public Integer Convert(String X) {
                return Integer.valueOf(X);
            }
        };

        Converter<String,Integer>Converter = X -> Integer.parseInt(X);//Lambda Expression
        Integer Number = Converter.Convert("123");
        System.out.println(Number);
        //Lambda Expression is only applied to functional interfaces with one abstract method
    }
}
