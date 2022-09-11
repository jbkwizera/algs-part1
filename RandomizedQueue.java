import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private  Item[] a;
    private int n;

    // Construct an empty randomized queue
    public RandomizedQueue() {
        a = (Item[]) new Object[1];
    }

    // Is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // Return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // Add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Null keys not allowed.");
        
        if (n == a.length/2) resize(2*a.length);
        a[n++] = item;
    }

    // Remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Randomized Queue is empty.");
        
        int i = StdRandom.uniform(0, n);
        Item item = a[i];
        a[i] = a[--n];
        a[n] = null;
        if (n > 0 && n == a.length/4) resize(a.length/2);
        return item;
    }

    // Return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException("Randomized Queue is empty.");
        int i = StdRandom.uniform(0, n);
        return a[i];
    }

    // Resize array
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++)
            temp[i] = a[i];
        a = temp;
    }

    // Return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int i = 0;

        private RandomizedQueueIterator() {
            // Shuffle for each iterator
            for (int i = 0; i < n; i++) {
                int r = StdRandom.uniform(i+1);
                Item temp = a[r];
                a[r] = a[i];
                a[i] = temp;
            }
        }

        public boolean hasNext() {
            return i < n; 
        }

        public Item next() {
            if (i >= n)
                throw new NoSuchElementException("Randomized Queue is empty.");
            return a[i++];
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove not supported.");
        }
    }
    public static void main(String[] args) {

    }
}

