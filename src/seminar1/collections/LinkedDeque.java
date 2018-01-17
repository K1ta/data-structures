package seminar1.collections;

import java.util.Iterator;

public class LinkedDeque<Item> implements IDeque<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    public LinkedDeque() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void pushFront(Item item) {
        Node<Item> h = head;
        Node<Item> newNode = new Node<>(item, null, h);
        head = newNode;
        if (h == null) {
            tail = newNode;
        } else {
            h.prev = newNode;
        }
        size++;
    }

    @Override
    public void pushBack(Item item) {
        Node<Item> t = tail;
        Node<Item> newNode = new Node<>(item, t, null);
        tail = newNode;
        if (t == null) {
            head = newNode;
        } else {
            t.next = newNode;
        }
        size++;
    }

    @Override
    public Item popFront() {
        if (head != null) {
            Item item = head.item;
            Node<Item> next = head.next;
            if (next == null) {
                tail = null;
            } else {
                next.prev = null;
            }
            head = next;
            size--;
            return item;
        }
        return null;
    }

    @Override
    public Item popBack() {
        if (tail != null) {
            Item item = tail.item;
            Node<Item> prev = tail.prev;
            if (prev == null) {
                head = null;
            } else {
                prev.next = null;
            }
            tail = prev;
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
        return new LinkedDequeIterator();
    }

    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;

        public Node(Item item, Node<Item> prev, Node<Item> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private class LinkedDequeIterator implements Iterator<Item> {
        Node<Item> cur = new Node<>(null, null, head);

        @Override
        public boolean hasNext() {
            return cur.next != null;
        }

        @Override
        public Item next() {
            cur = cur.next;
            return cur.item;
        }
    }
}
