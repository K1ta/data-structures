package seminar1.collections;

import java.util.Iterator;

public class LinkedStack<Item> implements IStack<Item> {

    private Node<Item> head;
    private int size;

    public LinkedStack() {
        head = null;
        size = 0;
    }

    @Override
    public void push(Item item) {
        Node<Item> h = head;
        Node<Item> newNode = new Node(item, h);
        head = newNode;
        size++;
    }

    @Override
    public Item pop() {
        if (size > 0) {
            Node<Item> node = head;
            head = head.next;
            size--;
            return node.item;
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
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<Item> {
        Node<Item> cur = new Node(null, head);

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

    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
}
