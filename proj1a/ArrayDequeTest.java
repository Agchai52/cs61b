public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<Integer>();
        arrayDeque.addFirst(1);
        arrayDeque.addFirst(0);
        arrayDeque.addLast(2);
        arrayDeque.addLast(3);
        arrayDeque.addLast(4);
        arrayDeque.addLast(5);
        arrayDeque.addFirst(-1);
        arrayDeque.addFirst(-2);
        arrayDeque.addFirst(-3);
        arrayDeque.addLast(6);
        arrayDeque.printDeque();
        arrayDeque.removeFirst();
        arrayDeque.printDeque();
        arrayDeque.removeLast();
        arrayDeque.printDeque();
        System.out.println(arrayDeque.get(0));
    }
}
