package seminar1.collections;

import java.util.Iterator;

public class CyclicArrayQueue<Item> implements IQueue<Item> {

    private static final int DEFAULT_CAPACITY = 10;

    private Item[] elementData;
    int size = 0;
    int head = 0;
    int tail = 0;

    public CyclicArrayQueue() {
        elementData = (Item[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        head = 0;
        tail = 0;
    }

    @Override
    public void enqueue(Item item) {
        elementData[tail] = item;
        size++;
        tail = (tail + 1) % elementData.length;
        if (tail == head) {
            grow();
        }
    }

    @Override
    public Item dequeue() {
        if (size == 0) {
            return null;
        }
        Item item = elementData[head];
        if (item != null) {
            elementData[head] = null;
            size--;
            head = (head + 1) % elementData.length;
            if (elementData.length >= 4 * size) {
                shrink();
            }
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

    private void grow() {
        int newCapacity = (int) (elementData.length * 1.5f);
        Item a[] = (Item[]) new Object[newCapacity];
        int length = elementData.length - head;
        System.arraycopy(elementData, 0, a, 0, tail);
        System.arraycopy(elementData, head, a, a.length - length, length);
        elementData = a;
        head = a.length - length;
    }

    private void shrink() {
        int newCapacity = Math.max(elementData.length >> 1, DEFAULT_CAPACITY);
        Item a[] = (Item[]) new Object[newCapacity];
        if (head < tail) {
            System.arraycopy(elementData, head, a, 0, tail - head);
            tail -= head;
            head = 0;
        } else {
            int length = elementData.length - head;
            System.arraycopy(elementData, 0, a, 0, tail);
            System.arraycopy(elementData, head, a, a.length - length, length);
            head = a.length - length;
        }
        elementData = a;
    }

    @Override
    public Iterator<Item> iterator() {
        return new CyclisArrayQueueIterator();
    }

    private class CyclisArrayQueueIterator implements Iterator<Item> {

        private int currentPos = head;

        @Override
        public boolean hasNext() {
            return currentPos != tail;
        }

        @Override
        public Item next() {
            Item item = elementData[currentPos];
            currentPos = (currentPos + 1) % elementData.length;
            return item;
        }
    }
}
