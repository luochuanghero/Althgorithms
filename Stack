/**
 * The Stack class represents a last-in-first-out (LIFO) stack of generic items.
 * It supports the usual push and pop operations, along with methods for peeking at the top item,
 * testing if the stack is empty, and iterating throughthe items in LIFO order.
 * isEmpty()  ->  return true if this stack is empty;
 * size()  ->  return the number of items in the stack;
 * push(Item item)  ->  Adds the item to this stack.
 * pop()  ->  Removes and returns the item most recently added to this stack.
 * peek() ->  Returns (but does not remove) the item most recently added to this stack.
 */

public class Stack<Item>implements Iterable<Item> {

    private int N; // size of the stack
    private Node<Item> first; //top of stack

    /*
     * helpe to linked list class
     */
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Initializes an empty stack.
     */
    public Stack(){
       first = null;
       N = 0;
    }
    /**
     * Is this stack empty?
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty(){
        return first == null;
    }
    /**
     * Returns the number of items in the stack.
     * @return the number of items in the stack
     */
    public int size(){
        return N;
    }
    /**
     * Adds the item to this stack.
     * @param item the item to add
     */
    public void push(Item item){
        Node<Item> oldlist = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldlist;
        N++;
    }
    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public Item pop(){
        if(isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    /**
     * Returns (but does not remove) the item most recently added to this stack.
     * @return the item most recently added to this stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public Item peek(){
        if(isEmpty()) throw new NoSuchElementException();
        return first.item;
    }

    /**
     * Returns a string representation of this stack.
     * @return the sequence of items in the stack in LIFO order, separated by spaces
     */
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
        return new ListIterator<Item>(first);
    }

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
