package com.adarsh.util;

/**
 * Stores a sequence that consists of a key and value pair. Like a symbol table,
 * follows the convention that if an object is added with the same key as
 * another one, the old object is replaced with the new. Doesn't store null
 * values. A value being set to null, is like deleting it.
 *
 * Supports, put, get, contains, delete, size and is-empty. Helper method to
 * iterate over keys, returns a Queue.
 * 
 * Also from Sedgewick and Wayne.
 * 
 * @author adarsh
 *
 * @param <Key>
 *            an object to address the elements by.
 * @param <Value>
 *            the element being stored.
 */
public class SequentialSearchST<Key, Value> {
	private int N;
	private Node first;

	/**
	 * Simple node. Used to store a key value tuple and the next element.
	 * 
	 * @author adarsh
	 *
	 */
	private class Node {
		private Key key;
		private Value value;
		private Node next;

		public Node(Key k, Value v, Node n) {
			this.key = k;
			this.value = v;
			this.next = n;
		}
	}

	public SequentialSearchST() {

	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	/**
	 * Goes through the list and fetches a value matching the key.
	 * 
	 * @param k
	 *            key
	 * @return value
	 */
	public Value get(Key k) {

		for (Node x = first; x != null; x = x.next) {
			if (x.key.equals(k))
				return x.value;
		}
		return null;
	}

	/**
	 * Puts value into list for a given key. If key already exists value is
	 * updated to what is being passed in. If a value is null, key value pair is
	 * deleted. This is so that no null values are stored and no keys are
	 * duplicated. O(n)
	 * 
	 * @param k
	 *            key
	 * @param v
	 *            value
	 */
	public void put(Key k, Value v) {
		if (v == null) {
			delete(k);
			return;
		} else {
			// look for k first.
			for (Node x = first; x != null; x = x.next) {
				if (x.key.equals(k)) {
					x.value = v;
					return;
				}
			}
			first = new Node(k, v, first);
			N++;
			return;
		}

	}

	/**
	 * checks if a key is present in the list
	 * 
	 * @param k
	 *            key
	 * @return boolean
	 */
	public boolean contains(Key k) {
		return (get(k) != null);
	}

	/**
	 * Iterative delete. So things can kind of get big. Once a key is found,
	 * deletes it. No key is inserted twice.
	 * 
	 * @param k
	 *            key
	 * @return if we found anything and deleted it.
	 */
	public boolean delete(Key k) {
		Node prev = null;
		Node temp = first;
		boolean deleteSuccess = false;
		while (temp != null) {
			if (temp.key.equals(k)) {
				if (temp.key.equals(first.key)) {
					first = first.next;
					N--;
				} else {
					prev.next = temp.next;
					N--;
				}
				deleteSuccess = true;
			} else {
				prev = temp;
			}
			temp = temp.next;
		}
		return deleteSuccess;
	}

	public Iterable<Key> keys() {
		Queue<Key> q = new Queue<Key>();
		for (Node x = first; x != null; x = x.next) {
			q.enqueue(x.key);
		}
		return q;
	}
}
