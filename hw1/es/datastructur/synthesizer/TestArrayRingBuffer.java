package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testBasics() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertEquals(0, arb.fillCount());
        assertEquals(10, arb.capacity());
    }

    @Test
    public void testEnqueue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(7);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(5);
        arb.enqueue(6);
        assertEquals(6, arb.fillCount());
        arb.enqueue(7);
        assertEquals(7, arb.fillCount());
        //arb.enqueue(8);
    }

    @Test
    public void testDeque() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        // arb.peek();
        // arb.dequeue();
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);
        arb.enqueue(4);
        Integer expect = 1;
        assertEquals(expect, arb.peek());
        assertEquals(4, arb.fillCount());
        assertEquals(expect, arb.dequeue());
        assertEquals(3, arb.fillCount());
        arb.enqueue(4);
        arb.enqueue(5);
        assertEquals(5, arb.fillCount());
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        arb.enqueue(-1);
        arb.enqueue(0);
        arb.enqueue(1);
        arb.dequeue();
        arb.enqueue(2);
        for (Integer i : arb) {
            System.out.print(i);
        }
        System.out.println();

    }

    @Test
    public void testEquals() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        arb.enqueue(-1);
        arb.enqueue(0);
        arb.enqueue(1);
        arb.dequeue();
        arb.enqueue(2);
        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(3);
        arb2.enqueue(0);
        arb2.enqueue(1);
        arb2.enqueue(2);
        assertTrue(arb.equals(arb2));

    }
}
