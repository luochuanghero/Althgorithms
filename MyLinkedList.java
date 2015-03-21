package com.finals.link;

import java.util.Iterator;

public class MyLinkedList<AnyType> implements Iterable<AnyType> {

	private int theSize;
	private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
    
	public MyLinkedList() {
		doClear();
	}

	/**
	 * change the size of the collection to zero
	 */
	public void clear() {
		doClear();
	}

	/**
	 * return the number of the coolection
	 * 
	 * @return
	 */
	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public void doClear() {
		beginMarker = new Node<>(null, null, null);
		endMarker = new Node<>(null, beginMarker, null);
		beginMarker.next = endMarker;

		theSize = 0;
		modCount++;
	}

	/**
	 * adds an item to this collection,at the end
	 * 
	 * @param x
	 * @return
	 */
	public boolean add(AnyType x) {
		add(size(), x);
		return true;
	}

	/**
	 * add an item to this collection, at specified postion items at or after
	 * that position are slid one postion higher.
	 * 
	 * @param idx
	 *            postion to add at
	 * @param x
	 *            any object
	 */
	public void add(int idx, AnyType x) {
		addBefore(getNode(idx, 0, theSize), x);
	}

	/**
	 * add an item to this collection, at specified postion items at or after
	 * that position are slid one postion higher.
	 * 
	 * @param p
	 *            node to add before
	 * @param x
	 *            any object
	 */
	private void addBefore(Node<AnyType> p, AnyType x) {
		Node<AnyType> newNode = new Node<>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}

	/**
	 * return the item at postion idx
	 * 
	 * @param idx
	 *            the index to search in
	 * @return the item at postion idx
	 */
	public AnyType get(int idx) {
		return getNode(idx).data;
	}

	/**
	 * Get the item at postion idx . which must range from 0 to size() - 1
	 * 
	 * @param idx
	 *            index to search at .
	 * @return if idx is not between 0 and size() - 1 will throw
	 *         indexoutofboundexception.
	 */
	private Node<AnyType> getNode(int idx) {
		return getNode(idx, 0, size());
	}

	/**
	 * Gets the node at position index , which must range form lower to upper
	 * 
	 * @param idx
	 *            index to search at
	 * @param lower
	 *            lower valid index
	 * @param upper
	 *            upper valid index
	 * @return internal node collesponding to index
	 */
	private Node<AnyType> getNode(int idx, int lower, int upper) {
		Node<AnyType> p = null;

		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException();

		if (idx < size() / 2) {
			p = beginMarker.next;
			for (int i = 0; i < idx; i++) {
				p = p.next;
			}
		} else {
			p = endMarker.next;
			for (int i = size(); i > idx; i--) {
				p = p.prev;
			}
		}
		return p;
	}

	/**
	 * remove an item from the collection
	 * 
	 * @param idx
	 *            the index of the object
	 * @return the item was removed from the collection
	 */
	public AnyType remove(int idx) {
		return remove(getNode(idx));
	}

	/**
	 * removes the object cotained in node p
	 * 
	 * @param p
	 *            the node cotaining the object
	 * @return the item was removed from the collection
	 */
	private AnyType remove(Node<AnyType> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount++;
		return p.data;
	}

	/* This is the doubly - linked list node */
	private static class Node<AnyType> {

		public AnyType data;
		public Node<AnyType> prev;
		public Node<AnyType> next;

		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
			this.data = d;
			this.prev = p;
			this.next = n;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for (AnyType anyType : this) {
			sb.append(anyType + " ");
		}
		sb.append("]");
		return new String(sb);
	}

	@Override
	public Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<AnyType>{
		
		private Node<AnyType> current = beginMarker.next;
		private boolean okToRemove = false;
		private int exceptedModCount = modCount;

		@Override
		public boolean hasNext() {
			return current != endMarker;
		}

		@Override
		public AnyType next() {
			if(modCount != exceptedModCount)
				throw new java.util.ConcurrentModificationException();
			if(!hasNext())
				throw new java.util.NoSuchElementException();
			AnyType nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		@Override
		public void remove() {
			if(modCount != exceptedModCount)
				throw new java.util.ConcurrentModificationException();
			if(!okToRemove)
				throw new IllegalStateException();
			MyLinkedList.this.remove(current.prev);
			exceptedModCount++;
			okToRemove = false;
		}
	}
}
