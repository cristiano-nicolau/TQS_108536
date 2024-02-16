/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tqs.sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import tqs.sets.BoundedSetOfNaturals;

/**
 * @author ico0
 */
class BoundedSetOfNaturalsTest {
    private BoundedSetOfNaturals setA;
    private BoundedSetOfNaturals setAA;
    private BoundedSetOfNaturals setB;
    private BoundedSetOfNaturals setC;
    private BoundedSetOfNaturals setD;
    private BoundedSetOfNaturals setE;




    @BeforeEach
    public void setUp() {
        setA = new BoundedSetOfNaturals(1);
        setAA= new BoundedSetOfNaturals(2);
        setB = BoundedSetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});
        setC = BoundedSetOfNaturals.fromArray(new int[]{50, 60});
        setD = BoundedSetOfNaturals.fromArray(new int[]{5,6,7,8});
        setE = BoundedSetOfNaturals.fromArray(new int[]{40,60});

    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = null;
    }

    @DisplayName("Test of add method, of class BoundedSetOfNaturals.")
    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        assertThrows(IllegalArgumentException.class, () -> setB.add(11), "add: adding element to full set did not throw exception.");

        setAA.add(99);
        assertThrows(IllegalArgumentException.class, () -> setAA.add(99), "add: adding duplicate element did not throw exception.");
   
        assertThrows(IllegalArgumentException.class, () -> setAA.add(-1), "add: adding negative element did not throw exception.");
    }

    @DisplayName("Test of add from array method, of class BoundedSetOfNaturals.")
    @Test
    public void testAddFromBadArray() {
        int[] elems = new int[]{10, -20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems));
    }

    @DisplayName("Test of contains method, of class BoundedSetOfNaturals.")
    @Test
    public void testContains() {
        assertTrue(setB.contains(10), "contains: element not found in set.");
        assertFalse(setB.contains(11), "contains: element found in set.");
    }

    @DisplayName("Test of intersects method, of class BoundedSetOfNaturals.")
    @Test
    public void testIntersects() {
        assertTrue(setB.intersects(setC), "intersects: sets do not intersect.");
        assertFalse(setB.intersects(setD), "intersects: sets intersect.");
        assertTrue(setB.intersects(setE), "intersects: sets do not intersect.");
    }

    @DisplayName("Test of size method, of class BoundedSetOfNaturals.")
    @Test
    public void testSize() {
        assertEquals(6, setB.size(), "size: wrong size.");
    }

}
