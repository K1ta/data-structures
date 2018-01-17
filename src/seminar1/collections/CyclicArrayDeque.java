package seminar1.collections;

import java.util.Iterator;

public class CyclicArrayDeque<Item> implements IDeque<Item> {

    private static final int DEFAULT_CAPACITY = 10;

    private Item[] elementData;
    private int size;
    private int head;
    private int tail;

    public CyclicArrayDeque() {
        elementData = (Item[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        head = 0;
        tail = 0;
    }

    @Override
    public void pushFront(Item item) {
        head--;
        if (head < 0) {
            head = elementData.length - 1;
        }
        elementData[head] = item;
        size++;
        if (size == elementData.length) {
            grow();
        }
    }

    @Override
    public void pushBack(Item item) {
        if (tail >= elementData.length) {
            tail = 0;
        }
        elementData[tail] = item;
        tail++;
        size++;
        if (size == elementData.length) {
            grow();
        }
    }

    @Override
    public Item popFront() {
        if (size == 0) {
            return null;
        }
        Item item = elementData[head];
        elementData[head] = null;
        head++;
        if (head >= elementData.length) {
            head = 0;
        }
        size--;
        if (elementData.length >= 4 * size) {
            shrink();
        }
        return item;
    }

    @Override
    public Item popBack() {
        if (size == 0) {
            return null;
        }
        tail--;
        if (tail < 0) {
            tail = elementData.length - 1;
        }
        Item item = elementData[tail];
        elementData[tail] = null;
        size--;
        if (elementData.length >= 4 * size) {
            shrink();
        }
        return item;
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
        if (tail >= elementData.length) {
            tail = 0;
        }
        int newLength = (int) (elementData.length * 1.5);
        int l = elementData.length - tail;
        Item[] a = (Item[]) new Object[newLength];
        System.arraycopy(elementData, 0, a, 0, tail);
        System.arraycopy(elementData, tail, a, newLength - l, l);
        elementData = a;
        head = newLength - l;
    }

    private void shrink() {
        int newLength = elementData.length >>> 1;
        if (newLength < DEFAULT_CAPACITY) {
            newLength = DEFAULT_CAPACITY;
        }
        Item a[] = (Item[]) new Object[newLength];
        if (tail < head) {
            int l = elementData.length - head;
            System.arraycopy(elementData, 0, a, 0, tail + 1);
            System.arraycopy(elementData, head, a, newLength - l, l);
            head = newLength - l;
        } else {
            //System.arraycopy(elementData, tail, a, 0, head - tail + 1);
            int l = tail - head;
            System.arraycopy(elementData, head, a, 0, l);
            //head = head - tail + 1;
            //tail = newLength - 1;
            head = 0;
            tail = l;
        }
        elementData = a;
    }

    @Override
    public Iterator<Item> iterator() {
        /* TODO: implement it */
        return null;
    }
}
