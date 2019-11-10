package LLD.Cache;

import java.util.HashMap;
import java.util.Map;

// LRU Cache implementation using HashMap + Doubly Linked List
// Get(key) O(1)
// Put(key,value) O(1)

class DLNode<K,V> {
	K key;
	V value;
	DLNode<K,V> prev;
	DLNode<K,V> next;

	public DLNode(K key, V value) {
		this.key = key;
		this.value = value;
	}
}

public class LRU<K,V> {

	private DLNode<K,V> head;
	private DLNode<K,V> tail;
	private int capacity;
	private int elements;
	private Map<K, DLNode<K,V>> cache;

	public LRU(int capacity) {
		this.capacity = capacity;
		this.elements = 0;
		cache = new HashMap<>();
	}

	public V get(K key) {
		if (!cache.containsKey(key))
			return null;
		DLNode<K,V> currNode = cache.get(key);
		V retValue = currNode.value;
		removeNode(currNode);
		addNodeToHead(currNode);
		return retValue;
	}

	public void put(K key, V val) {
		if (cache.containsKey(key)) {
			DLNode<K,V> currNode = cache.get(key);
			currNode.value = val;
			removeNode(currNode);
			addNodeToHead(currNode);
		} else {
			DLNode<K,V> newNode = new DLNode<>(key, val);
			addNodeToHead(newNode);
			elements++;
			cache.put(key, newNode);
			if (elements > capacity) {
				cache.remove(tail.key);
				removeNode(tail);
				elements--;
			}
		}
	}

	private void removeNode(DLNode<K,V> currNode) {
		if (currNode.prev == null) {
			tail = currNode.next;
			currNode.next = null;
			return;
		}
		currNode.prev.next = currNode.next;

		if (currNode.next == null) {
			head = currNode.prev;
		} else {
			currNode.next.prev = currNode.prev;
			currNode.next = null;
		}
		currNode.prev = null;

	}

	private void addNodeToHead(DLNode<K,V> currNode) {
		if (head == null)
			head = tail = currNode;
		else {
			currNode.prev = head;
			head.next = currNode;
			head = head.next;
		}
	}
}
