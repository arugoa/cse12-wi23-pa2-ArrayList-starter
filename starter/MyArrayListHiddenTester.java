import static org.junit.Assert.*;

import org.junit.*;

public class MyArrayListHiddenTester {

    static final int DEFAULT_CAPACITY = 5;
    static final int MY_CAPACITY = 3;

    Object[] arr = null;
    Integer[] validIntegers = {1 , null, 4, 6, 9, null, 11};
    Integer[] nullToAppend = {34, 5, 567, 8, 89};
    Integer[] randomIntegers = {3, 1, null, 5, null, null, null};

    private MyArrayList nullarraylist, invalidarray, validArrayList, normalArr, nullArrayAppend;


    // Do not change the method signatures!
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test 
     */
    @Before
    public void setUp() throws Exception {
        nullarraylist = new MyArrayList(arr);
        validArrayList = new MyArrayList<Integer>(validIntegers);
        normalArr = new MyArrayList<>(nullToAppend);
        nullArrayAppend = new MyArrayList<>(randomIntegers);
    }

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test
    public void testConstructorInvalidArg(){
        try {
            invalidarray = new MyArrayList<>(-3);
            fail();
        }
        catch (IllegalArgumentException e) {}
    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg(){
        assertEquals("Checking for null array", 5, nullarraylist.getCapacity());
    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull(){
        assertEquals("Checking for correct length with valid arguments", 7, nullArrayAppend.getCapacity());
        assertEquals("Checking for correct length with valid arguments", 5, nullArrayAppend.size());
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        normalArr.append(598);
        assertEquals("Checking if length changed correctly", 6, normalArr.size);
        assertEquals("Checking for correct capacity", 8, normalArr.getCapacity());
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull(){
        nullArrayAppend.append(null);
        assertEquals("Checking if null appended and size changed correctly", 5, nullArrayAppend.size());
        assertEquals("Checking correct capacity", 7, nullArrayAppend.getCapacity());
    }

    /**
     * Aims to test the prepend method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testPrependAtCapacity(){
        normalArr.prepend(4);
        assertEquals("Checking if length changed correctly", 6, normalArr.size);
        assertEquals("Checking for correct capacity", 8, normalArr.getCapacity());
    }
    
    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
        try{
            normalArr.prepend(null);
            fail();
        }
        catch ( IllegalArgumentException e) {}
        //assertEquals("Checking if length changed correctly", 5, normalArr.size);
        //assertEquals("Checking for correct capacity", 5, normalArr.getCapacity());
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test
    public void testInsertOutOfBound(){
        try {
            normalArr.insert(7, 7);
            fail();
        }
        catch (IndexOutOfBoundsException e) {}
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        for (int i = 0; i < 5; i++) {
            normalArr.insert(i, i*101);
        }
        assertEquals("Checking if size changed correctly", 10, normalArr.size());
        assertEquals("Checking if capacity changed correctly", 11, normalArr.getCapacity());
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test
    public void testGetOutOfBound(){
        try {
            normalArr.get(9348);
            fail();
        }
        catch (IndexOutOfBoundsException e) {}
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test
    public void testSetOutOfBound(){
        try {
            normalArr.set(1111, 111);
            fail();
        }
        catch (IndexOutOfBoundsException e) {}
    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple(){
        assertEquals("Checking if correct value removed",  89, normalArr.remove(4));
        assertEquals("Checking if correct value removed", 5, normalArr.remove(1));
        assertEquals("Checking size", 3, normalArr.size());
    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test
    public void testRemoveOutOfBound(){
        try {
            normalArr.remove(923929329);
            fail();
        }
        catch (IndexOutOfBoundsException e) {}
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test
    public void testExpandCapacitySmaller(){
       try {
        normalArr.expandCapacity(3);
        fail();
       }
       catch (IllegalArgumentException e) {}
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than current capacity+3 and default capacity
     */
    @Test
    public void testExpandCapacityLarge(){
        normalArr.expandCapacity(349);
        assertEquals("Checking if capacity expanded correctly", 349, normalArr.getCapacity());
    }
    
}
