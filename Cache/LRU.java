package LLD.Cache;

import java.util.HashMap;
import java.util.Map;

// LRU Cache implementation using HashMap + Doubly Linked List
// Get(key) O(1)
// Put(key,value) O(1)

class DLNode {
	int key;
	int value;
	DLNode prev;
	DLNode next;

	public DLNode(int key, int value) {
		this.key = key;
		this.value = value;
	}
}

public class LRU {

	private DLNode head;
	private DLNode tail;
	private int capacity;
	private int elements;
	private Map<Integer, DLNode> cache;

	public LRU(int capacity) {
		this.capacity = capacity;
		this.elements = 0;
		cache = new HashMap<>();
	}

	public int get(int key) {
		if (!cache.containsKey(key))
			return -1;
		DLNode currNode = cache.get(key);
		int retValue = currNode.value;
		removeNode(currNode);
		addNodeToHead(currNode);
		return retValue;
	}

	public void put(int key, int val) {
		if (cache.containsKey(key)) {
			DLNode currNode = cache.get(key);
			currNode.value = val;
			removeNode(currNode);
			addNodeToHead(currNode);
		} else {
			DLNode newNode = new DLNode(key, val);
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

	private void removeNode(DLNode currNode) {
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

	private void addNodeToHead(DLNode currNode) {
		if (head == null)
			head = tail = currNode;
		else {
			currNode.prev = head;
			head.next = currNode;
			head = head.next;
		}
	}
}
