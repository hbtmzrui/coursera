/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	MyLinkedList<Integer> endList;
	MyLinkedList<Integer> addList;
	MyLinkedList<Integer> setList;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		setList = new MyLinkedList<Integer>();
		
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
		endList = new MyLinkedList<Integer>();
		endList.add(1);
		endList.add(2);
		endList.add(3);
		
		addList = new MyLinkedList<Integer>();
		addList.add(12);
		addList.add(23);
		addList.add(34);
		
		
		setList = new MyLinkedList<Integer>();
		setList.add(1);
		setList.add(2);
		setList.add(3);
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		assertEquals("Remove: check prev link is correct ", list1.head, list1.head.next.prev);
		
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			list1.remove(list1.size());
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		LLNode<Integer> last = endList.tail.prev;
		LLNode<Integer> tail = endList.tail;
		
		boolean success = endList.add(4);
		assertEquals("AddEnd: check return value ", true, success);
		assertEquals("AddEnd: check list size ", 4, endList.size());
		assertEquals("AddEnd: check last element ", (Integer)4, endList.get(3));
		assertEquals("AddEnd: check tail's prev link ", (Integer)4, tail.prev.data);
		assertEquals("AddEnd: check last element's next ", tail, tail.prev.next);
		assertEquals("AddEnd: check last element's prev link ", last, tail.prev.prev);
		assertEquals("AddEnd: check prev element's next link ", endList.tail.prev, last.next);
		
		// test adding null pointer
		try {
			endList.add(null);
			fail("fail to prevent null pointer");
		} catch (NullPointerException e) {

		}
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("AddEnd: check return value ", 0, emptyList.size());
		assertEquals("AddEnd: check return value ", 2, shortList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{	
		// test adding null pointer
		try {
			addList.add(0, null);
			fail("fail to prevent null pointer");
		} catch (NullPointerException e) {

		}
				
		try {
			addList.add(-1, 99);
			fail("Check out of bound");
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			addList.add(4, 99);
			fail("Check out of bound");
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		// test adding item at the end
		LLNode<Integer> last = addList.tail.prev;
		LLNode<Integer> tail = addList.tail;
		addList.add(3, 99);
		assertEquals("AddAtIndex: check list size ", 4, addList.size());
		assertEquals("AddAtIndex: check last element ", (Integer)99, addList.get(3));
		assertEquals("AddAtIndex: check last element's prev link ", last, tail.prev.prev);
		assertEquals("AddAtIndex: check prev element's next link ", tail.prev, last.next);
		assertEquals("AddAtIndex: check tail's prev link ", (Integer)99, tail.prev.data);
		
		// test adding item in the beginning
		LLNode<Integer> first = addList.head.next;
		LLNode<Integer> head = addList.head;
		addList.add(0, 999);
		assertEquals("AddAtIndex: check list size ", 5, addList.size());
		assertEquals("AddAtIndex: check first element ", (Integer)999, addList.get(0));
		assertEquals("AddAtIndex: check first element's prev link ", head, head.next.prev);
		assertEquals("AddAtIndex: check head's next link ", (Integer)999, head.next.data);
		assertEquals("AddAtIndex: check first node's prev link ", (Integer)999, first.prev.data);
		
		// test adding item in the middle
		LLNode<Integer> prev = addList.head.next.next;
		LLNode<Integer> next = prev.next;
		addList.add(2, 9999);
		assertEquals("AddAtIndex: check list size ", 6, addList.size());
		assertEquals("AddAtIndex: check element at inserted index ", (Integer)9999, addList.get(2));
		assertEquals("AddAtIndex: check the value of prev data ", prev.data, addList.get(1));
		assertEquals("AddAtIndex: check the value of next data ", next.data, addList.get(3));
		assertEquals("AddAtIndex: check next item's prev link ", (Integer)9999, next.prev.data);
		assertEquals("AddAtIndex: check prev item's next link ", (Integer)9999, prev.next.data);
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		assertEquals("set: check element prior to set ", (Integer)1, setList.get(0));
		setList.set(0,  22);
		assertEquals("set: check element after set ", (Integer)22, setList.get(0));
		
		//test setting null pointer
		try {
			setList.set(0,  null);
			fail("fail to prevent null pointer");
		} catch (NullPointerException e) {

		}
		
		//test out of bound
		try {
			setList.set(-1,  0);
			fail("set out of bound");
		} catch (IndexOutOfBoundsException e) {

		}
		
		try {
			setList.set(3,  0);
			fail("set out of bound");
		} catch (IndexOutOfBoundsException e) {

		}
	}
	
	
	// TODO: Optionally add more test methods.
	
}
