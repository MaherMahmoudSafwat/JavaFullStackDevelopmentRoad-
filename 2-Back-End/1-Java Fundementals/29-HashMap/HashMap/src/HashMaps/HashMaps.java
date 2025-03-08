package HashMaps;

import java.util.HashMap;

public class HashMaps {

	public static void main(String[] args) {
		HashMap<String,String>hm = new HashMap<String, String>();
		hm.put("Programming1","C#");
		hm.put("Programming1","Java");
		hm.put("Programming2","Python");
		hm.put("Programming3","C++");
		System.out.println(hm);
		System.out.println(hm.get("Programming3"));
		if(!hm.isEmpty())
		{
			System.out.println("HashMap is Not empty");
		}
		else
		{
			System.out.println("Hash Map is Empty");
		}
	}

}
