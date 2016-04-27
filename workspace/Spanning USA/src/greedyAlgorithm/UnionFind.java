package greedyAlgorithm;

import java.util.HashMap;
import java.util.HashSet;

public class UnionFind {
	HashMap<String, HashSet<String>> sets;

	public UnionFind(HashSet<String> s) {
		sets = new HashMap<String, HashSet<String>>();
		for (String node : s) {
			HashSet<String> temp = new HashSet<String>();
			temp.add(node);
			sets.put(node, temp);
		}
	}

	public String find(String u) {
		if(sets.get(u).contains(u))
			return u;
		else
			return sets.get(u).iterator().next();
	}

	public void union(String a, String b) {
			if(sets.get(a).size()>=sets.get(b).size()){
				sets.get(a).addAll(sets.get(b));
				sets.get(b).clear();
				sets.get(b).add(a);
				for (String currentSetName : sets.keySet()) {
					if (sets.get(currentSetName).contains(b) && !(currentSetName.equals(a))) {
						HashSet<String> temp = sets.get(currentSetName);
						temp.clear();
						temp.add(a);
						sets.put(currentSetName, temp);
					}
					System.out.println(currentSetName + ": " + sets.get(currentSetName).toString());

				}
			} else {
				sets.get(b).addAll(sets.get(a));
				sets.get(a).clear();
				sets.get(a).add(b);
				for (String currentSetName : sets.keySet()) {
					if (sets.get(currentSetName).contains(a) && !(currentSetName.equals(b))) {
						HashSet<String> temp = sets.get(currentSetName);
						temp.clear();
						temp.add(b);
						sets.put(currentSetName, temp);
					}
					System.out.println(currentSetName + ": " + sets.get(currentSetName).toString());

				}
			}
			

		

	}

}
