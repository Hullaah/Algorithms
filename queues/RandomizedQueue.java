/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] queue;
    private int last;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[16];
    }

    // is the randomized queue empty
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items in the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        resize();
        if (item == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        queue[last++] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        resize();
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int i = StdRandom.uniformInt(size);
        Item item = queue[i];
        queue[i] = queue[--last];
        queue[last] = null;
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        int i = StdRandom.uniformInt(size);
        return queue[i];
    }

    private void resize() {
        final double RESIZE_FACTOR = 1.5;
        int n;
        if (queue.length == size) {
            n = (int) (queue.length * RESIZE_FACTOR);
        }
        else if (size < (int) (queue.length * 0.25)) {
            n = (int) (queue.length / RESIZE_FACTOR);
        }
        else return;
        Item[] newQueue = (Item[]) new Object[n];
        System.arraycopy(queue, 0, newQueue, 0, size);
        queue = newQueue;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        {
            StdRandom.shuffle(queue, 0, size);
        }

        int i = 0;

        public Item next() {
            return queue[i++];
        }

        public boolean hasNext() {
            return i < size;
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        // Test isEmpty and size
        System.out.println("Is the queue empty? " + queue.isEmpty()); // Expected: true
        System.out.println("Queue size: " + queue.size()); // Expected: 0

        // Test enqueue
        System.out.println("\nAdding elements to the queue...");
        queue.enqueue("First");
        queue.enqueue("Second");
        queue.enqueue("Third");
        queue.enqueue("Fourth");

        // Test size after enqueue
        System.out.println("Queue size: " + queue.size()); // Expected: 4

        // Test sample (should return a random element, but not remove it)
        System.out.println("Random sample from the queue: " + queue.sample());
        System.out.println("Queue size after sample: " + queue.size()); // Expected: 4

        // Test dequeue (removes a random element)
        System.out.println("Random dequeue from the queue: " + queue.dequeue());
        System.out.println("Queue size after dequeue: " + queue.size()); // Expected: 3

        // Test iterator
        System.out.println("\nIterating over queue elements:");
        for (String item : queue) {
            System.out.println(item);
        }

        // Test isEmpty after removing all elements
        while (!queue.isEmpty()) {
            queue.dequeue();
        }
        System.out.println("Is the queue empty? " + queue.isEmpty()); // Expected: true
    }
}
