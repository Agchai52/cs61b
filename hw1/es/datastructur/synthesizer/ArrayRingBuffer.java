package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T>  implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    /* Variable for the capacity. */
    private int capacity;


     /* Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int c) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[c];
        first = 0;
        last = 0;
        fillCount = 0;
        capacity = c;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }
    /**
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        fillCount += 1;
        if (fillCount > capacity) {
            throw new RuntimeException("Ring Buffer overflow!");
        }
        rb[last] = x;
        last += 1;
        last %= capacity;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow!");
        }
        T returnRing = rb[first];
        rb[first] = null;
        first += 1;
        first %= capacity;
        fillCount -= 1;
        return returnRing;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer underflow!");
        }
        return rb[first];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    private class ARBIterator implements Iterator<T> {
        private int wizPos;
        private int counter;
        public ARBIterator() {
            wizPos = first;
            counter = 0;
        }
        public boolean hasNext() {
            return counter < fillCount;
        }
        public T next() {
            T returnItem = rb[wizPos];
            wizPos += 1;
            counter += 1;
            wizPos %= capacity;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ARBIterator();
    }

    @Override
    public boolean equals(Object o) {

        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;

        if (this.fillCount != other.fillCount) {
            return false;
        }

        if (this.capacity != other.capacity) {
            return false;
        }

        boolean isTrue = true;
        for (int i = 0; i < fillCount; i++) {
            T actual = this.dequeue();
            T expect = other.dequeue();
            this.enqueue(actual);
            other.enqueue(expect);
            if (actual != expect) {
                isTrue = false;
            }
        }
        return isTrue;
    }

}
    // TODO: Remove all comments that say TODO when you're done.
