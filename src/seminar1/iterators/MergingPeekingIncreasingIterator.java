package seminar1.iterators;

import java.util.Iterator;

/**
 * Итератор возвращающий последовательность из N возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 * <p>
 * Time = O(n + k * log n),
 * n — количество итераторов
 * k — суммарное количество элементов
 */
public class MergingPeekingIncreasingIterator implements Iterator<Integer> {

    @SafeVarargs
    public MergingPeekingIncreasingIterator(IPeekingIterator<Integer>... peekingIterator) {
        /* TODO: implement it */
//        for (int i = 0; i < peekingIterator.length; i++) {
//            peekingIterator[i].hasNext();
//        }
    }

    @Override
    public boolean hasNext() {
        /* TODO: implement it */
        return false;
    }

    @Override
    public Integer next() {
        /* TODO: implement it */
        return null;
    }
}
