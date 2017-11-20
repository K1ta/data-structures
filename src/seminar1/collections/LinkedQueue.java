package seminar1.collections;

import java.util.Iterator;

public class LinkedQueue<Item> implements IQueue<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    public LinkedQueue() {
        head = null;
        tail = null;
        size = 0;
    }


    @Override
    public void enqueue(Item item) {
        Node<Item> t = tail;
        Node<Item> newNode = new Node<>(item, t);
        tail = newNode;
        if (t == null) {
            head = newNode;
        }
        size++;
    }

    @Override
    public Item dequeue() {
        if (head != null) {
            Item item = head.item;
            if (size == 1) {
                head = null;
                tail = null;
                size = 0;
                return item;
            }
            Node<Item> prev = tail;
            while (prev.next != head) {
                prev = prev.next;
            }
            prev.next = null;
            head = prev;
            size--;
            return item;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<Item> {
        Node<Item> cur = new Node<>(null, tail);

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public Item next() {
            return dequeue();
        }

    }

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
}
