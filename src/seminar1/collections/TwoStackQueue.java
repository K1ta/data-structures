package seminar1.collections;

import java.util.Iterator;

public class TwoStackQueue<Item> implements IQueue<Item> {

    private IStack<Item> stack1;
    private IStack<Item> stack2;

    public TwoStackQueue() {
        stack1 = new LinkedStack<>();
        stack2 = new LinkedStack<>();
    }

    @Override
    public void enqueue(Item item) {
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(item);
    }

    @Override
    public Item dequeue() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    @Override
    public boolean isEmpty() {
        return (stack1.size() == 0 && stack2.size() == 0);
    }

    @Override
    public int size() {
        return stack1.isEmpty() ? stack2.size() : stack1.size();
    }

    @Override
    public Iterator<Item> iterator() {
        /* TODO: implement it (optional) */
        return null;
    }

}
