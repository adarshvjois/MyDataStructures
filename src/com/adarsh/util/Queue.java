package com.adarsh.util;


import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 
 * @author adarsh
 * Re-implemented from the sedgewick book.
 * supports enqueue dequeue size and isempty
 * @param <Item> is a thing.
 */ 
public class Queue<Item> implements Iterable<Item> {
	int N;
	private Node<Item> first;
	private Node<Item> last;
	/**
	 * Simple node.
	 * @author adarsh
	 *
	 * @param <Item> a thing
	 */
	public static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}
	/**
	 * Default Constructor
	 */
	public Queue() {
		this.first = null;
		this.last = null;
		this.N = 0;
	}
	/**
	 * Adds an item to the back.
	 * if the queue is new, there's just one element
	 * and the back and front are the same element.
	 * @param i thing to be added
	 */
	public void enqueue(Item i) {
		Node<Item> oldLast = last;
		last = new Node<Item>();
		last.item = i;
		last.next = null;
		if (this.size() == 0)
			first = last;
		else
			oldLast.next = last;
		N++;
	}
	/**
	 * This removes an item from the front of the queue.
	 * if this is the last element in the queue, first and last wud have been 
	 * the same reference, so we make sure that last is also null
	 * @return thing in the queue
	 */
	public Item dequeue() {
		if (size() == 0)
			throw new NoSuchElementException();
		Item i = first.item;
		first = first.next;
		N--;
		if (isEmpty())
			last = null; // to avoid loitering.
		return i;
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return (N > 0);
	}
	/**
	 * we all want to look pretty.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Item i : this) {
			sb.append(i + " ");
		}
		return sb.toString();
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
/**
 * Strangest guy here.
 *
 * @author adarsh
 *	Used to iterate over the queue starting at the item first elem.
 *	No deletion is reqd. coz that is taken care of in dequeue.
 * @param <Item> it is a thing.
 */
	private class ListIterator<Item> implements Iterator<Item> {
		private Node<Item> current;

		public ListIterator(Node<Item> first) {
			current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
