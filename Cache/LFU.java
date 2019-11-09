package LLD.Cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

// LFU Cache implementation  using 3 HashMap
//Get(key) O(1)
//Put(key,value) O(1)

public class LFU {
	private Map<Integer, Integer> keyValueMap;
	private Map<Integer, Integer> freqMap;
	private Map<Integer, LinkedHashSet<Integer>> lists;
	private int capacity;
	private int min;

	public LFU(int capacity) {
		this.capacity = capacity;
		min = Integer.MIN_VALUE;
		keyValueMap = new HashMap<>();
		freqMap = new HashMap<>();
		lists = new HashMap<>();
		lists.put(1, new LinkedHashSet<>());
	}

	public int get(int key) {
		if (!keyValueMap.containsKey(key))
			return -1;

		int count = freqMap.get(key);

		// update freqMap with latest count
		freqMap.put(key, count + 1);

		// remove key corresponding to count and take minimum key removal into
		// consideration
		lists.get(count).remove(key);
		if (count == min && lists.get(count).size() == 0)
			min++;

		// put latest count of keys into maps
		if (!lists.containsKey(count + 1))
			lists.put(count + 1, new LinkedHashSet<>());
		lists.get(count + 1).add(key);

		return keyValueMap.get(key);
	}

	public void put(int key, int val) {
		if (capacity <= 0)
			return;

		// if key exists in the map
		if (keyValueMap.containsKey(key)) {
			keyValueMap.put(key, val);
			get(key);
		} 
		
		else {
			// addition of new key exceeds cache capacity
			if (keyValueMap.size() >= capacity) {
				int toBeRemoved = lists.get(min).iterator().next();
				lists.get(min).remove(toBeRemoved);
				freqMap.remove(toBeRemoved);
				keyValueMap.remove(toBeRemoved);
			}

			keyValueMap.put(key, val);
			freqMap.put(key, 1);
			min = 1;
			lists.get(1).add(key);
		}
	}
}
