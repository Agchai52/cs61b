/** Implement ArrayDeque.
 * @author Nora.
 * */
public class ArrayDeque<T> {
    /** Array items. */
    private T[] items;
    /** Sentinel */
    private static final int SENTINEL = 3;
    /** Array size. */
    private int size;
    /** Next index for addFirst. */
    private int nextFirst;
    /** Next index for addLast. */
    private int nextLast;

    /** Create an empty deque. */
    public ArrayDeque() {
        items = (T[]) new  Object[8];
        nextFirst = SENTINEL + 1;
        nextLast = SENTINEL + 2;
        size = 0;
    }

    /** Create a deep copy of other array.
     * @param other array.
     * */
    public ArrayDeque(ArrayDeque other) {
        ArrayDeque arrayDeque = new ArrayDeque();
        for (int i = 0; i < other.size(); i++) {
            arrayDeque.addLast(other.get(i));
        }
    }

    /** Resize the array when size exceeds 8. */
    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        int head = nextLast;
        System.arraycopy(items, head, temp, SENTINEL+1, size-head);
        System.arraycopy(items, 0, temp, size + SENTINEL, head);
        nextFirst = SENTINEL;
        nextLast = SENTINEL + size + 1;
        items = temp;
    }
    /** Adds an item of type T to the front of the deque.
     * @param item input value.
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        }
        items[nextFirst] = item;
        nextFirst -= 1;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque.
     * @param item input value.
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        if (nextLast == items.length) {
            nextLast = 0;
        }
        items[nextLast] = item;
        nextLast += 1;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque.*/
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last.*/
    public void printDeque() {
        int start = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            if (start == items.length) {
                start = 0;
            }
            System.out.print(items[start] + " ");
            start += 1;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.*/
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst += 1;
        if (nextFirst == items.length) {
            nextFirst = 0;
        }
        T front = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return front;
    }

    /** Removes and returns the item at the back of the deque.*/
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast -= 1;
        if (nextLast == -1) {
            nextLast = items.length;
        }
        T back = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return back;
    }

    /** Gets the item at the given.
     * @param index index.
     * @return item. */
    public T get(int index) {
        int start = nextFirst + 1;
        int cur = start + index;
        if (cur >= items.length) {
            cur -= items.length;
        }
        return items[cur];
    }

}

