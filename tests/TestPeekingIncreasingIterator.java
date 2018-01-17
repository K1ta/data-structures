import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

import seminar1.iterators.PeekingIncreasingIterator;

/**
 * Класс тестирующий {@link seminar1.iterators.PeekingIncreasingIterator}
 */
public class TestPeekingIncreasingIterator {

    @Test(expected = NoSuchElementException.class)
    public void testEmptyIterator() {
        new PeekingIncreasingIterator(10, 10, 0, 1).next();
    }

    @Test
    public void testEmptyHasNext() {
        Assert.assertFalse(new PeekingIncreasingIterator(10, 10, 0, 1).hasNext());
    }

    @Test
    public void testHasNext() {
        PeekingIncreasingIterator it = new PeekingIncreasingIterator(10, 10, 1, 1);
        Assert.assertTrue(it.hasNext());
        it.next();
        Assert.assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyNext() {
        new PeekingIncreasingIterator(10, 10, 0, 1).next();
    }

    @Test
    public void testNext() {
        PeekingIncreasingIterator it = new PeekingIncreasingIterator(10, 300, 30, 3);
        Integer prev = Integer.MIN_VALUE;
        Integer cur;
        while(it.hasNext()) {
            cur = it.next();
            Assert.assertTrue(cur >= prev);
            prev = cur;
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyPeek() {
        new PeekingIncreasingIterator(10, 10, 0, 1).peek();
    }

    @Test
    public void testPeek() {
        PeekingIncreasingIterator it = new PeekingIncreasingIterator(10, 300, 2, 3);
        Assert.assertTrue(it.peek() >= it.next());
    }
}
