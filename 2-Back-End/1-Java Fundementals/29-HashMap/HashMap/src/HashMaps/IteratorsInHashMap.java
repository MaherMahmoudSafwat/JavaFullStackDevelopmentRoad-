package HashMaps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;

public class IteratorsInHashMap {

	public static void main(String[] args) {
		HashMap<String,String>Maps = new HashMap<String,String>();
		Maps.put("Maher", "Mahmoud");
		Maps.put("Youssef", "Kamael");
		Maps.put("Mohammed", "Samir");
		Set<String>Keys = Maps.keySet();
		Iterator<String>NamesIterator = Keys.iterator();
		while(NamesIterator.hasNext())
		{
			String S = NamesIterator.next();
			System.out.println(S + " " + Maps.get(S));
		}
	}

}
