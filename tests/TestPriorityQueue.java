import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import seminar1.collections.ArrayPriorityQueue;
import seminar1.collections.IPriorityQueue;

import java.util.NoSuchElementException;

/**
 * Класс тестирующий интерфейс {@link IPriorityQueue<Integer>} на основе {@link ArrayPriorityQueue<>}
 */
public class TestPriorityQueue {

    private IPriorityQueue<Integer> queue;

    @Before
    public void init() {
        queue = new ArrayPriorityQueue<>();
    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(queue.size(), 0);
    }

    @Test
    public void isNotEmpty() {
        queue.add(0);
        Assert.assertFalse(queue.isEmpty());
        Assert.assertEquals(queue.size(), 1);
    }

    @Test
    public void testOneAdd() {
        queue.add(0);
        Integer expected = 0;
        Assert.assertEquals(expected, queue.peek());
    }

    @Test
    public void testTwoAdd() {
        queue.add(2);
        queue.add(1);
        Integer expected = 1;
        Assert.assertEquals(expected, queue.peek());
    }

    @Test
    public void testThreeAdd() {
        queue.add(1);
        queue.add(2);
        queue.add(3);
        Integer expected1 = 1;
        Integer expected2 = 2;
        Assert.assertEquals(expected1, queue.extractMin());
        Assert.assertEquals(expected2, queue.extractMin());
    }

    @Test
    public void testTenAdd() {
        for(int i = 0; i < 10; i++) {
            queue.add(i);
        }
        Integer expected = 0;
        Assert.assertEquals(expected, queue.peek());
    }

    @Test
    public void testElevenAdd() {
        for(int i = 0; i < 11; i++) {
            queue.add(i);
        }
        Integer expected = 0;
        Assert.assertEquals(expected, queue.peek());
    }

    @Test(expected = NullPointerException.class)
    public void testEmptyQueueAdd() {
        queue.add(null);
    }

    @Test
    public void testPeek() {
        Integer min = 100;
        for(int i = 0; i < 100; i++) {
            Integer key = (int)(Math.random() * 100);
            queue.add(key);
            if(key < min) min = key;
        }
        Assert.assertEquals(min, queue.peek());
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyQueuePeek() {
        queue.peek();
    }

    @Test
    public void testOneExtractMin() {
        queue.add(1);
        Integer expected = 1;
        Assert.assertEquals(expected, queue.extractMin());
    }

    @Test
    public void testTenExtractMin() {
        for(int i = 0; i < 10; i++) {
            Integer key = (int)(Math.random() * 100);
            queue.add(key);
        }
        Integer prev = -1;
        for(int i = 0; i < 10; i++) {
            Integer key = queue.extractMin();
            Assert.assertTrue(prev <= key);
            prev = key;
        }
    }

    @Test
    public void testHundredExtractMin() {
        for(int i = 0; i < 100; i++) {
            Integer key = (int)(Math.random() * 100);
            queue.add(key);
        }
        Integer prev = -1;
        for(int i = 0; i < 100; i++) {
            Integer key = queue.extractMin();
            Assert.assertTrue(prev <= key);
            prev = key;
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmptyQueueExtractMin() {
        queue.extractMin();
    }

    @Test
    public void testOneSize() {
        queue.add(1);
        Assert.assertEquals(1, queue.size());
    }

    @Test
    public void testTenSize() {
        for(int i = 0; i < 11; i++) {
            queue.add(i);
        }
        Assert.assertEquals(11, queue.size());
    }

    @Test
    public void testSize() {
        for(int i = 0; i < 20; i++) {
            queue.add(i);
        }
        for(int i = 0; i < 15; i++) {
            queue.extractMin();
        }
        Assert.assertEquals(5, queue.size());
    }
}
