package com.luochuang.DS;

import java.util.Iterator;

public class MyArrayList<AnyType> implements Iterable<AnyType> {
	
	private static final int DEFAULT_CAPACITY = 10;
	private int theSize;
	private AnyType[] theItems;
	
	/**
	 * Construct an empty ArrayList
	 */
	public MyArrayList(){
		doClear();
	}
	
	/**
	 * 
	 * @return the numbers of items in this collection
	 */
	public int size(){
		return theSize;
	}
	
	/**
	 * 
	 * @return true if the collection is empty
	 */
	public boolean isEmpty(){
		return size() == 0;
	}
	
	
	/**
	 * return the item at position idx
	 * @param idx the index to search in.
	 * @return
	 * @throws arrayindexoutofboundsexceptions if index is out of range
	 */
	public AnyType get(int idx){
		if(idx < 0 || idx > size())
			throw new ArrayIndexOutOfBoundsException();
		return theItems[idx];
	}
	
	/**
	 * Changes the item at postion idx
	 * @param idx the index of change 
	 * @param newVal the new value
	 * @return the old value
	 */
	public AnyType set(int idx,AnyType newVal){
		if(idx < 0 || idx >=size())
			throw new ArrayIndexOutOfBoundsException();
		AnyType old = theItems[idx];
		theItems[idx] = newVal;
		return old;
	}
	
	@SuppressWarnings("unchecked")
	public void ensureCapacity(int newCapacity){
		if(newCapacity < theSize)
			return;
		AnyType[] old = theItems;
		theItems = (AnyType[]) new Object[newCapacity];
		for (int i = 0; i < size(); i++) {
			theItems[i] = old[i];
		}
	}
	
	/**
	 * Adds an item to this coolection, at the end
	 * by default add item end of the collection.
	 * @param x any object
	 * @return true.
	 */
	public boolean add(AnyType x){
		add(size(), x);
		return true;
	}
	
	/**
	 * add an item to this collection , at the specified index.
	 * @param idx
	 * @param x
	 */
	public void add(int idx, AnyType x){
		if(theItems.length == size())
			ensureCapacity(size() * 2 + 1);
		for (int i = theSize; i > idx; i--) {
			theItems[i] = theItems[i -1];
		}
		theItems[idx] = x;
		theSize++;
	}
	
	/**
	 * Removes an item from this collection.
	 * @param index the index of the object
	 * @return the item which was removed from the collection
	 */
	public AnyType remove(int index){
		AnyType removedItem = theItems[index];
		for (int i = index; i < size() - 1; i++) {
			theItems[i] = theItems[i + 1];
		}
		theSize--;
		return removedItem;
	}
	
	/**
	 * change the size of the collection to zero
	 */
	public void clear(){
		doClear();
	}
	
	private void doClear(){
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}

	@Override
	public Iterator<AnyType> iterator() {
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<AnyType>{
		
		private int current = 0;
		private boolean okToRemove = false;

		@Override
		public boolean hasNext() {
			return current < size();
		}

		@Override
		public AnyType next() {
			if(!hasNext())
				throw new java.util.NoSuchElementException();
			okToRemove = true;
			return theItems[current++];
		}

		@Override
		public void remove() {
			if(!okToRemove)
				throw new IllegalStateException();
			MyArrayList.this.remove(--current);
			okToRemove = false;
		}
	}
}
