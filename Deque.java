import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private class Node<Item> {
        Item item;
        Node<Item> prev;
        Node<Item> next;
    }

    // Construct an empty deque
    public Deque() {}

    // Is the deque empty?
    public boolean isEmpty()
    {   return n == 0; }

    // Return the number of items on the deque
    public int size()
    {   return n; }

    // Add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null keys not allowed.");
        }
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        if (isEmpty()) last = first;
        else           oldfirst.prev = first;
        n++;
    }

    // Add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Null keys not allowed.");
        }
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        if (isEmpty()) first = last;
        else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        n++;
    }

    // Remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    // Remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty.");
        }
        Item item = last.item;
        last = last.prev;
        if (last != null) last.next = null;
        else              first = last;
        n--;
        return item;
    }

    // Return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {   return new DequeIterator(); }

    private class DequeIterator implements Iterator<Item> {
        public Node<Item> current = first;

        public boolean hasNext()
        {   return current != null; }

        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("Deque is empty.");
            }
            Item item = current.item;
            current   = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported.");
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(0);
        deque.addFirst(-1);
        deque.addFirst(-2);
        deque.removeFirst();
        deque.removeLast();
        deque.removeFirst();
        deque.addFirst(-5);
        deque.addLast(5);

        System.out.println("Deque is empty: " + deque.isEmpty());
        for (int x: deque) {
            System.out.printf("%2d ", x);
        }
        System.out.println();
        System.out.println("Size of deque: " + deque.size());

    }
}
