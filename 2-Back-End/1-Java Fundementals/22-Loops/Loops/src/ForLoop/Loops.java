package ForLoop;

public class Loops {

	public static void main(String[] args) {
		String[] names =	{"Muhammed", "Essa", "Hameed",
                "Hayder", "Ali", "Hassan",
                "Ahmed", "Usama", "Mustafa",
                "Sarmad", "Yusuf", "Yassir"};

for (int i = 0; i < names.length; i++) {
System.out.println(names[i]);
}

for (String name : names) {
System.out.println(name);
}
int counter = 0;
while (counter <names.length) {
System.out.println(names[counter]);
counter++;
}
counter = 0;
do {
System.out.println(names[counter]);
counter++;

}while (counter <names.length);
	}
}
