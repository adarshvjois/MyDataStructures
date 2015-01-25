package com.adarsh.ds;

import java.util.ArrayList;
import java.util.Iterator;

public class HashTableUsingAL<Key, Value> {
	private static final int INIT_SIZE = 16;
	private static final float ALPHA = 0.75f;

	private int M; // size of hash table
	private int N; // number of elements inserted

	private ArrayList<Entry<Key, Value>> entries[];

	private class Entry<Key, Value> {
		public Key k;
		public Value v;

		public Entry(Key k, Value v) {
			this.k = k;
			this.v = v;
		}
	}

	public HashTableUsingAL() {
		this(INIT_SIZE);
	}

	public HashTableUsingAL(int size) {
		this.M = size;
		entries = (ArrayList<Entry<Key, Value>>[]) new ArrayList[size];
		for (int i = 0; i < M; i++) {
			entries[i] = new ArrayList<Entry<Key, Value>>();
		}
	}

	private int hash(Key k) {
		return (k.hashCode() & 0x7fffffff) % M;
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public Iterable<Key> keys() {
		ArrayList<Key> A = new ArrayList<Key>();
		for (int i = 0; i < M; i++) {
			for (Entry<Key, Value> e : entries[i])
				A.add(e.k);
		}
		return A;
	}

	private void resize(int toSize) {

		HashTableUsingAL<Key, Value> temp = new HashTableUsingAL<Key, Value>(
				toSize);
		for (int i = 0; i < M; i++) {
			for (Entry<Key, Value> e : entries[i]) {
				temp.put(e.k, e.v);
			}
		}
		this.M = temp.M;
		this.N = temp.N;
		this.entries = temp.entries;
	}

	public void delete(Key k) {
		int i = hash(k);

		Iterator<Entry<Key, Value>> it = entries[i].iterator();
		while (it.hasNext()) {
			if (it.next().k.equals(k)) {
				N--;
				it.remove();
			}
		}
		if (M > INIT_SIZE && N <= 2 * M) {
			resize(M / 2);
		}
	}

	public void put(Key k, Value v) {
		if (v == null) {
			delete(k);
		}

		if (N >= ALPHA * M) {
			resize(2 * M);
		}
		int i = hash(k);
		if (!this.contains(k)) {
			N++;
		}
		entries[i].add(new Entry<Key, Value>(k, v));
	}

	public Value get(Key k) {
		int i = hash(k);

		for (Entry<Key, Value> e : entries[i]) {
			if (e.k.equals(k)) {
				return e.v;
			}
		}
		return null;
	}

	public boolean contains(Key k) {
		return (get(k) != null);
	}

}
