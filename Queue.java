package com.finals.link;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The Queue class represents a first-in-first-out queue of gereric items;
 * isEmpty()  ->  is this queue is empty ;
 * size()  -> return the number of the queue ;
 * enqueue(Item item)  ->  add the item to the queue ;
 * dequeue()  ->  remove and return the item on this queue that was least recently added ;
 * peek() ->  return the item(not delete)recently added to the queue ;
 * Queue()  -> Initializes an empty queue ;
 *
 */
public class Queue<Item> implements Iterable<Item> {
	
	private int N;//number of elements on queue
	private Node<Item> first; //begining of queue
	private Node<Item> last; //end of queue
	
	
	/*helper linked like class*/
	private static class Node<Item>{
		private Item item;  
		private Node<Item> next;
	}
	
	/*is this queue is empty*/
	public boolean isEmpty(){
		return first == null;
	}
	
	/*return the number of the queue*/
	public int size(){
		return N;
	}
	
	/*Initializes an empty queue*/
	public Queue() {
		first = null;
		last = null;
		N = 0;
	}
	
	/*add the item to the queue*/
	public void enqueue(Item item){
		Node<Item> oldlast = last; //save a link to the last node
		last = new Node<>(); //create a new node for the end
		last.item = item; 
		oldlast.next = last;//link the new node to the end of the link
		N++;
	}
	
	/*remove and return the item on this queue that was least recently added*/
	public Item dequeue(){
		if(isEmpty()) throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		N--;
		return item;	
	}
	
	/*return the item(not delete)recently added to the queue*/
	public Item peek(){
		if(isEmpty()) throw new NoSuchElementException();
		return first.item;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Item item : this) {
			sb.append(item + " ");
		}
		return new String(sb);
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator(first);
	}
	
	private class ListIterator implements Iterator<Item>{
		
		private Node<Item> current;
		
		public ListIterator(Node<Item> first){
			current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if(!hasNext()) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
           // not support operat			
		}
	}
}
