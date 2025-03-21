/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item value;
        Node next;
        Node prev;
    }

    private Node sentinel;
    private int size;

    public Deque() {
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item value) {
        if (value == null) {
            throw new IllegalArgumentException("addFirst cannot be called with null");
        }
        Node node = new Node();
        node.next = sentinel.next;
        node.next.prev = node;
        node.prev = sentinel;
        node.value = value;
        sentinel.next = node;
        size++;
    }

    public void addLast(Item value) {
        if (value == null) {
            throw new IllegalArgumentException("addLast cannot be called with null");
        }
        Node node = new Node();
        node.next = sentinel;
        node.prev = sentinel.prev;
        node.prev.next = node;
        node.value = value;
        sentinel.prev = node;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Deque is empty");
        }
        Item value = sentinel.next.value;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return value;

    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Deque is empty");
        }
        Item value = sentinel.prev.value;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return value;
    }

    private class DequeIterator implements Iterator<Item> {
        Node node = sentinel;

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more items to return");
            }
            node = node.next;
            return node.value;
        }

        public boolean hasNext() {
            return node.next != sentinel;
        }
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        String[] words = {
                "level", "madam", "nursesrun", "racecar", "bubbub"
        };
        Deque<Character> deque = new Deque<>();
        int size = 0;
        assert deque.size() == 0;
        assert deque.isEmpty();

        for (String word : words) {
            for (int i = word.length() / 2 - 1; i >= 0; i--) {
                deque.addFirst(word.charAt(i));
                size++;
            }
            for (int i = word.length() / 2; i < word.length(); i++) {
                deque.addLast(word.charAt(i));
                size++;
            }
            System.out.println(word);
            for (char c : deque) {
                System.out.println("deque: " + c);
            }
            System.out.println();
            assert deque.size() == size;
            while (!deque.isEmpty()) {
                if (deque.size() == 1) {
                    deque.removeFirst();
                    size--;
                    assert deque.size() == size;
                    break;
                }
                char a = deque.removeFirst();
                size--;
                assert deque.size() == size;
                char b = deque.removeLast();
                size--;
                assert deque.size() == size;
                assert a == b;
            }
        }
    }
}
