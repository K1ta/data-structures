package seminar1.iterators;

import java.util.Iterator;

/**
 * Итератор возвращающий последовательность из двух возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 * <p>
 * Time = O(k),
 * k — суммарное количество элементов
 */
public class MergingIncreasingIterator implements Iterator<Integer> {

    private IncreasingIterator first;
    private IncreasingIterator second;
    private Integer firstNext, secondNext;

    public MergingIncreasingIterator(IncreasingIterator first, IncreasingIterator second) {
        this.first = first;
        this.second = second;
        firstNext = first.next();
        secondNext = second.next();
    }

    @Override
    public boolean hasNext() {
        return (first.hasNext() || second.hasNext());
    }

    @Override
    public Integer next() {
        Integer res = null;
        if (first.hasNext() && !second.hasNext()) {
            firstNext = res = first.next();
        }
        if (!first.hasNext() && second.hasNext()) {
            secondNext = res = second.next();
        }
        if (first.hasNext() && second.hasNext()) {
            if (firstNext < secondNext) {
                res = firstNext;
                if (first.hasNext()) {
                    firstNext = first.next();
                }
            } else {
                res = secondNext;
                if (second.hasNext()) {
                    secondNext = second.next();
                }
            }
        }
        return res;
    }
}
