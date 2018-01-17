import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import seminar1.collections.CyclicArrayDeque;
import seminar1.collections.IDeque;
import seminar1.collections.LinkedDeque;

/**
 * Класс тестирующий интерфейс {@link IDeque<Integer>} в двух реализациях:
 * 1) на массиве {@link CyclicArrayDeque<Integer>}
 * 2) на списке {@link LinkedDeque<Integer>}
 */
@RunWith(value = Parameterized.class)
public class TestDeque {

    @Parameterized.Parameter()
    public Class<?> testClass;

    private IDeque<Integer> deque;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Class<?>> data() {
        return Arrays.asList(
                CyclicArrayDeque.class,
                LinkedDeque.class
        );
    }

    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        try {
            deque = (IDeque<Integer>) testClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(deque.isEmpty());
        Assert.assertEquals(deque.size(), 0);
    }

    @Test
    public void testSize() {
        deque.pushFront(2);
        deque.pushFront(1);
        deque.pushBack(3);
        Assert.assertEquals(3, deque.size());
    }

    @Test
    public void testSizeAfterPushAndPop() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) deque.pushFront(i);
            else deque.pushBack(i);
        }
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) deque.popBack();
            else deque.popFront();
        }
        Assert.assertEquals(50, deque.size());
    }

    @Test
    public void testOnePushFront() {
        deque.pushFront(1);
        Integer expected = 1;
        Assert.assertEquals(1, deque.size());
        Assert.assertEquals(expected, deque.popFront());
    }

    @Test
    public void testThreePushFront() {
        deque.pushFront(1);
        deque.pushFront(100);
        deque.pushFront(1000);
        Integer expected = 1000;
        Assert.assertEquals(3, deque.size());
        Assert.assertEquals(expected, deque.popFront());
    }

    @Test
    public void testHundredPushFront() {
        for (int i = 0; i < 100; i++) {
            deque.pushFront(i);
        }
        Assert.assertEquals(100, deque.size());
        Integer expected = 99;
        Assert.assertEquals(expected, deque.popFront());
    }

    @Test
    public void testOnePushBack() {
        deque.pushBack(1);
        Integer expected = 1;
        Assert.assertEquals(1, deque.size());
        Assert.assertEquals(expected, deque.popBack());
    }

    @Test
    public void testThreePushBack() {
        deque.pushBack(1);
        deque.pushBack(100);
        deque.pushBack(1000);
        Integer expected = 1000;
        Assert.assertEquals(3, deque.size());
        Assert.assertEquals(expected, deque.popBack());
    }

    @Test
    public void testHundredPushBack() {
        for (int i = 0; i < 100; i++) {
            deque.pushBack(i);
        }
        Assert.assertEquals(100, deque.size());
        Integer expected = 99;
        Assert.assertEquals(expected, deque.popBack());
    }

    @Test
    public void testEmptyPopFront() {
        Assert.assertNull(deque.popFront());
    }

    @Test
    public void testThreePopFront() {
        deque.pushBack(1);
        deque.pushBack(2);
        deque.pushBack(3);
        Integer expected1 = 1;
        Integer expected2 = 2;
        Integer expected3 = 3;
        Assert.assertEquals(expected1, deque.popFront());
        Assert.assertEquals(expected2, deque.popFront());
        Assert.assertEquals(expected3, deque.popFront());
        Assert.assertNull(deque.popFront());
    }

    @Test
    public void testHundredPopFront() {
        for (int i = 0; i < 100; i++) {
            deque.pushBack(i);
        }
        Integer expected = 0;
        Assert.assertEquals(expected, deque.popFront());
    }

    @Test
    public void testEmptyPopBack() {
        Assert.assertNull(deque.popBack());
    }

    @Test
    public void testThreePopBack() {
        deque.pushFront(1);
        deque.pushFront(2);
        deque.pushFront(3);
        Integer expected1 = 1;
        Integer expected2 = 2;
        Integer expected3 = 3;
        Assert.assertEquals(expected1, deque.popBack());
        Assert.assertEquals(expected2, deque.popBack());
        Assert.assertEquals(expected3, deque.popBack());
        Assert.assertNull(deque.popBack());
    }

    @Test
    public void testHundredPopBack() {
        for (int i = 0; i < 100; i++) {
            deque.pushFront(i);
        }
        Integer expected = 0;
        Assert.assertEquals(expected, deque.popBack());
    }

    @Test
    public void testLinkedDequeIterator() {
        if (deque.getClass() == CyclicArrayDeque.class) {
            return;
        }
        deque.pushFront(2);
        deque.pushFront(1);
        deque.pushBack(3);
        deque.pushBack(4);
        Iterator<Integer> it = deque.iterator();
        Assert.assertTrue(it.hasNext());
        Assert.assertEquals((Integer) 1, it.next());
        Assert.assertEquals((Integer) 2, it.next());
        Assert.assertEquals((Integer) 3, it.next());
        Assert.assertEquals((Integer) 4, it.next());
    }
}
