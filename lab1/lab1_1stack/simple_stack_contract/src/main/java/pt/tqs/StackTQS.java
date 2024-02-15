package pt.tqs;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class StackTQS<T> 
{
    private LinkedList<T> stack;
    private Integer bound;

    public StackTQS() {
        this.stack = new LinkedList<T>();
    }

    public StackTQS(Integer bound) {
        this.stack = new LinkedList<T>();
        this.bound = bound;
    }

    public Integer getBound() {
        return bound;
    }

    public void push(T item) {
        stack.addFirst(item);
    }

    public T pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.removeFirst();
    }

    public T peek() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.getFirst();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
