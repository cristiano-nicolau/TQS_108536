package pt.tqs;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest 
{
    StackTQS<String> stack;

    @BeforeEach
    void setUp(){
        stack = new StackTQS<String>();
    }

    @DisplayName("The Stack is empty on construction")
    @Test
    void isEmpty(){
        assertTrue(stack.isEmpty(), "Stack is not empty on construction");
    }

    @DisplayName("The stack has size 0 in construction")
    @Test
    void size0construction(){
        assertEquals(0, stack.size(), "The stack size is not 0 in construction");
    }

    @DisplayName("The stack has size n after pushing n elements")
    @Test
    void sizeAfterPush(){
        stack.push("a");
        stack.push("b");
        stack.push("c");
        assertEquals(3, stack.size(), "The stack size is not 3 after pushing 3 elements");
    }

    @DisplayName("After pushes element x to the satck and then pops, the value popped is x")
    @Test
    void pushPop(){
        stack.push("a");
        stack.push("b");
        stack.push("c");
        assertEquals("c", stack.pop(), "The value popped is not c");
        assertEquals("b", stack.pop(), "The value popped is not b");
        assertEquals("a", stack.pop(), "The value popped is not a");
    }

    @DisplayName("If one pushes x then peeks, the value returned is x, but the size stays the same") 
    @Test
    void pushPeek(){
        stack.push("a");
        stack.push("b");
        stack.push("c");
        assertEquals("c", stack.peek(), "The value returned is not c");
        assertEquals(3, stack.size(), "The stack size is not 3 after peeking");
    }

    @DisplayName("If the size is n, then after n pops, the stack is empty and has a size 0")
    @Test
    void popEmpty(){
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.pop();
        stack.pop();
        stack.pop();
        assertTrue(stack.isEmpty(), "The stack is not empty after n pops");
        assertEquals(0, stack.size(), "The stack size is not 0 after n pops");
    }

    @DisplayName("Popping from an empty stack does throw a NoSuchElementException")
    @Test
    void popEmptyStack(){
        assertThrows(java.util.NoSuchElementException.class, () -> {
            stack.pop();
        }, "Popping from an empty stack does not throw a NoSuchElementException");
    }

    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException")
    @Test
    void peekEmptyStack(){
        assertThrows(java.util.NoSuchElementException.class, () -> {
            stack.peek();
        }, "Peeking into an empty stack does not throw a NoSuchElementException");
    }
   
    @DisplayName("For bounded stacks only: pushing onto a full stack does throw  an IllegalStateException")
    @Test
    void pushFullStack(){
        StackTQS<String> boundedStack = new StackTQS<String>(2);
        boundedStack.push("a");
        boundedStack.push("b");
        assertThrows(IllegalStateException.class, () -> {
            boundedStack.push("c");
        }, "Pushing onto a full stack does not throw an IllegalStateException");
    }

    @AfterEach  
    void tearDown(){
        stack = null;
    }
}
