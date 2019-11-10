package LLD.Cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeLRU<K, V> {
	private ConcurrentLinkedQueue<K> queue;
	private ConcurrentHashMap<K, V> lookup;
	private ReadWriteLock readWriteLock;
	private Lock readLock;
	private Lock writeLock;
	int capacity;

	public ThreadSafeLRU(int capacity) {
		this.capacity = capacity;
		this.queue = new ConcurrentLinkedQueue<>();
		this.lookup = new ConcurrentHashMap<>();
		this.readWriteLock = new ReentrantReadWriteLock();
		this.readLock = readWriteLock.readLock();
		this.writeLock = readWriteLock.writeLock();
	}

	public V get(K key) {
		readLock.lock();
		try {
			V v = null;
			if (lookup.containsKey(key)) {
				v = lookup.get(key);
				queue.remove(key);
				queue.add(key);
			}
			return v;
		} finally {
			readLock.unlock();
		}
	}

	public V remove(K key) {
		readLock.lock();
		try {
			V v = null;
			if (lookup.containsKey(key)) {
				v = lookup.remove(key);
				queue.remove(key);
			}
			return v;
		} finally {
			readLock.unlock();
		}
	}

	public void put(K key, V value) {
		writeLock.lock();
		try {
			if (lookup.containsKey(key)) {
				queue.remove(key);
			} else {
				if (queue.size() == capacity) {
					K keyToBeRemoved = queue.remove();
					lookup.remove(keyToBeRemoved);
				}
			}
			queue.add(key);
			lookup.put(key, value);
		} finally {
			writeLock.unlock();
		}
	}
}
