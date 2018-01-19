package seminar1.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayPriorityQueue<Key extends Comparable<Key>> implements IPriorityQueue<Key> {

    private Key[] elementData;
    private Comparator<Key> comparator;
    private int size;
    private static int DEFAULT_CAPACITY = 10;

    public ArrayPriorityQueue() {
        size = 0;
        elementData = (Key[]) new Comparable[DEFAULT_CAPACITY];
    }

    public ArrayPriorityQueue(Comparator<Key> comparator) {
        this.comparator = comparator;
        size = 0;
        elementData = (Key[]) new Comparable[DEFAULT_CAPACITY];
    }

    @Override
    public void add(Key key) {
        if(key == null) {
            throw new NullPointerException();
        }
        elementData[size] = key;
        siftUp(size);
        size++;
        if (size == elementData.length) {
            grow();
        }
    }

    @Override
    public Key peek() {
        if(size == 0) throw new NoSuchElementException();
        return elementData[0];
    }

    @Override
    public Key extractMin() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Key min = elementData[0];
        size--;
        elementData[0] = elementData[size];
        elementData[size] = null;
        siftDown(0);
        if (elementData.length >= 4 * size) {
            shrink();
        }
        return min;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void siftUp(int index) {
        if (size == 0) {
            return;
        }
        if (index == 0) {
            return;
        }
        int parent = (index - (2 - (index & 1)) >>> 1);
        if (greater(parent, index)) {
            Key temp = elementData[index];
            elementData[index] = elementData[parent];
            elementData[parent] = temp;
            siftUp(parent);
        }

    }

    private void siftDown(int index) {
        if (size == 0) {
            return;
        }

        int left = 2 * index + 1;
        int right = left + 1;

        if (right < size && greater(left, right) && greater(index, right)) {
            Key temp = elementData[right];
            elementData[right] = elementData[index];
            elementData[index] = temp;
            siftDown(right);
        } else if (left < size && greater(index, left)) {
            Key temp = elementData[left];
            elementData[left] = elementData[index];
            elementData[index] = temp;
            siftDown(left);
        }
    }

    private void grow() {
        int newSize = (int) (size * 1.5);
        newSize &= Integer.MAX_VALUE;
        Key[] a = (Key[]) new Comparable[newSize];
        System.arraycopy(elementData, 0, a, 0, size);
        elementData = a;
    }

    private void shrink() {
        int newSize = elementData.length >> 1;
        newSize = Math.max(newSize, DEFAULT_CAPACITY);
        Key[] a = (Key[]) new Comparable[newSize];
        System.arraycopy(elementData, 0, a, 0, size);
        elementData = a;
    }

    private boolean greater(int i, int j) {
        return comparator == null
                ? elementData[i].compareTo(elementData[j]) > 0
                : comparator.compare(elementData[i], elementData[j]) > 0
                ;
    }

    @Override
    public Iterator<Key> iterator() {
        /* TODO: implement it */
        return null;
    }
}
