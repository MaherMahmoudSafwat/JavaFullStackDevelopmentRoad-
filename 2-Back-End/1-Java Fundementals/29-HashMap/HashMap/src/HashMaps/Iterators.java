package HashMaps;

import java.util.ArrayList;
import java.util.ListIterator;

public class Iterators {

	public static void main(String[] args) {
		ArrayList<Integer>Lists = new ArrayList<>();
		Lists.add(1);
		Lists.add(2);
		Lists.add(3);
		Lists.add(4);
		Lists.add(5);
		ListIterator<Integer>Iterator = Lists.listIterator();
		while(Iterator.hasNext())
		{
			int value = Iterator.next();
			System.out.println(value);
		}
	}

}
