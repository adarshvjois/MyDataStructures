package com.adarsh.ds;

import com.adarsh.util.Queue;
import com.adarsh.util.SequentialSearchST;

/**
 * Represents HashTable using sequential chaining.
 * 
 * @author adarsh
 *
 * @param <Key>
 *            key
 * @param <Value>
 *            value
 */
public class HashTable<Key, Value> {
	public static final int INIT_SIZE = 16;
	public static final float Alpha = 0.75f;

	private int N; // no. of key value pairs
	private int M; // size of the hash table.
	private SequentialSearchST<Key, Value>[] st;

	public HashTable() {
		this(INIT_SIZE);
	}

	public HashTable(int M) {
		this.M = M;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
		for (int i = 0; i < M; i++) {
			st[i] = new SequentialSearchST<Key, Value>();
		}
	}

	/** unsigned hash in the range of 0 to M-1
	 * 
	 * @param key
	 * @return hashcode
	 */
	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public Value get(Key k) {
		int i = hash(k);
		return st[i].get(k);
	}

	public boolean contains(Key k) {
		return get(k) != null;
	}
	/**
	 * resize a hashTable to a new size.
	 * Creates a temp table and inserts all the values in the existing table to it.
	 * Copies params from temp table to current table.
	 * Replaces the SequentialSearchST array in the temp one as the current one.
	 * @param toSize new size specified by the param.
	 */
	private void resize(final int toSize){
		HashTable<Key,Value> T = new HashTable<Key,Value>(toSize);
		for(int i=0;i<toSize;i++){
			for(Key k : st[i].keys()){
				T.put(k,st[i].get(k));
			}
		}
		this.M = T.M;
		this.N = T.N;
		this.st = T.st;
	}
	/**
	 * Adds a key value pair.
	 * Lets it grow until each bucket has 10 times the elements as the number of 
	 * buckets. Each bucket is of avg size 10.
	 * O(10) guarantee.
	 * @param k key
	 * @param v value
	 */
	public void put(Key k, Value v) {
		if(v==null){
			delete(k);
			return;
		}
		if(N>=Alpha*M) resize(2*M);
		//need to hash after this in case resize happens.
		
		int i = hash(k);
		//increment size if the key is new.
		
		if(!st[i].contains(k)) N++;
		st[i].put(k, v);
	}
	/**
	 * Deletes a key from the list. Uses the SequentialST's delete.
	 * Lets the table shrink upto half its former size. When the number of elements
	 * are less than twice the number of buckets, resize.
	 * @param k key
	 */
	public void delete(Key k){
		int i = hash(k);
		if(st[i].contains(k)) N--;
		
		st[i].delete(k);
		if(M > INIT_SIZE && N<=2*M){
			resize(M/2);
		}
	}
	public Iterable<Key> keys(){
		Queue<Key> keys = new Queue<Key>();
		
		for(int i = 0;i<N;i++){
			for(Key k: st[i].keys()){
				keys.enqueue(k);
			}
		}
		return keys;
	}

}
