package seminar1.iterators;

import seminar1.collections.ArrayPriorityQueue;

import java.util.Iterator;

/**
 * Итератор возвращающий последовательность из N возрастающих итераторов в порядке возрастания
 * <p>
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 * <p>
 * Time = O(n + k * log n),
 * n — количество итераторов
 * k — суммарное количество элементов
 */
public class MergingPeekingIncreasingIterator implements Iterator<Integer> {

    PeekingIncreasingIterator[] iterators;
    ArrayPriorityQueue<Integer> heap;

    /**
     * Конструктор итератора возвращающего возрастающую последовательность
     *
     * @param peekingIterator — массив из возрастающих итераторов с возможностью узнать следующий элемент
     * @throws NullPointerException если массив равен null
     */
    @SafeVarargs
    public MergingPeekingIncreasingIterator(IPeekingIterator<Integer>... peekingIterator) {
        this.iterators = new PeekingIncreasingIterator[peekingIterator.length];
        for (int i = 0; i < iterators.length; i++) {
            this.iterators[i] = (PeekingIncreasingIterator) peekingIterator[i];
        }
        heap = new ArrayPriorityQueue<>();
        for (int i = 0; i < iterators.length; i++) {
            while (iterators[i].hasNext()) {
                heap.add(iterators[i].next());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return !heap.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer next() {
        return heap.extractMin();
    }
}
