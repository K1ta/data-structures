package seminar1.iterators;

import java.util.Comparator;
import java.util.Iterator;

import seminar1.collections.ArrayPriorityQueue;

/**
 * Итератор возвращающий последовательность из N возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 *
 * Time = O(n + k * log n),
 *  n — количество итераторов
 *  k — суммарное количество элементов
 */
public class MergingPeekingIncreasingIterator implements Iterator<Integer> {

    private Comparator<PeekingIncreasingIterator> comparator = (p1, p2) -> p1.peek().compareTo(p2.peek());
    PeekingIncreasingIterator[] iterators;
    ArrayPriorityQueue<Integer> heap;


    public MergingPeekingIncreasingIterator(IPeekingIterator... peekingIterator) {
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

    @Override
    public boolean hasNext() {
        return !heap.isEmpty();
    }

    @Override
    public Integer next() {
        return heap.extractMin();
    }
}
