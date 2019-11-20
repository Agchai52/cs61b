/** Double ended queue with circular sentinel.
 * @author Nora
 */
public class LinkedListDeque<T>  implements Deque<T>{
    /** This is the private class of the basic structure.*/
    private class Node {
        /** Value of the node.*/
        private T item;
        /** Previous node. */
        private Node prev;
        /** Previous node. */
        private Node next;

        /** Constructor the Node.
         * @param i value
         * @param p previous node
         * @param n next node.
         **/
        Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

	/** Sentinel node of the class. */
	private Node sentinel;
	/** Size of the deque. */
	private int size;
    /** Constructor for empty input. */
	public LinkedListDeque() {
		sentinel = new Node(null, null, null);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
	}

	/** Creates a deep copy of other deque. @param other another deque. */
	public LinkedListDeque(LinkedListDeque other) {
		LinkedListDeque lld = new LinkedListDeque();
		for (int i = 0; i < other.size(); i++) {
			lld.addLast(other.get(i));
		}
	}

    /** Adds an item of type T to the front of the deque.
      * @param item input value.
      */
	@Override
    public void addFirst(T item) {
    	Node preFirst = sentinel.next;
    	sentinel.next = new Node(item, sentinel, preFirst);
    	preFirst.prev = sentinel.next;
    	size += 1;
    }

    /** Adds an item of type T to the back of the deque.
      * @param item input value.
      */
	@Override
    public void addLast(T item) {
    	Node preLast = sentinel.prev;
    	preLast.next = new Node(item, preLast, sentinel);
    	sentinel.prev = preLast.next;
    	size += 1;
    }

	///** Returns true if deque is empty, false otherwise.*/
    /*@Override
    public boolean isEmpty() {
        return size == 0;
    }*/

    /** Returns the number of items in the deque.*/
	@Override
    public int size() {
    	return size;
    }

    /** Prints the items in the deque from first to last.*/
	@Override
    public void printDeque() {
    	for (Node p = sentinel.next; p != sentinel; p = p.next) {
    		System.out.print(p.item + " ");
    	}
    	System.out.println();

    }

    /** Removes and returns the item at the front of the deque.*/
	@Override
    public T removeFirst() {
    	if (isEmpty()) {
    		return null;
    	}
    	T firstItem = sentinel.next.item;
    	Node second = sentinel.next.next;
    	sentinel.next = second;
    	second.prev = sentinel;
    	size -= 1;
    	return firstItem;
    }

    /** Removes and returns the item at the back of the deque.*/
	@Override
    public T removeLast() {
    	if (isEmpty()) {
    		return null;
    	}
    	T lastItem = sentinel.prev.item;
    	Node secondLast = sentinel.prev.prev;
    	sentinel.prev = secondLast;
    	secondLast.next = sentinel;
    	size -= 1;
    	return lastItem;
    }

    /** Gets the item at the given.
      * @param index index.
      * @return item.*/
	@Override
    public T get(int index) {
    	Node p = sentinel.next;
    	for (int i = 0; i < index; i++) {
    		p = p.next;
    	}
    	return p.item;
    }

    /** This is the helper method of getRecursive.
      * @param index index.
      * @param p node.
      * @return item.*/
    private T getRecursive(int index, Node p) {
    	if (index == 0) {
    		return p.item;
    	}
    	return getRecursive(index - 1, p.next);
    }

    /** Same as get given, but uses recursion.
      * @param index index.
      * @return item.*/
    public T getRecursive(int index) {
    	return getRecursive(index, sentinel.next);
    }

}
