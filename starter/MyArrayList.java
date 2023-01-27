/*
 * Name: Arihant Jain
 * Email: arj003@ucsd.edu
 * PID: A17317535
 * Sources used: None
 * 
 * This file implements MyList as MyArrayList and implements all the functions
 * in MyList. It makes an array with a specific size and capacity which can be 
 * changed using the expandcapacity function. You can append, remove  and insert 
 * items from the array.
 */

/**
 * This class creates an array called MyArayList which is implemented using the 
 * List ADT
 * 
 * Instance variables:
 * data - a list which stores all the valid items in the array
 * size - the size of the array
 */
public class MyArrayList<E> implements MyList<E> {

    /**Instance variables */
    Object[] data;
    int size;

    /**
     * Default no arg constructor. Initializes data to an empty list and size to
     * 0.
     */
    public MyArrayList () {
        this.data = new Object[5];
        this.size  = 0;
    }

    /**
     * Constructor with integer arg. size initialized to 0 and data to a valid 
     * initialCapacity size.
     * 
     * @param initialCapacity - size of the initial list
     */
    public MyArrayList (int  initialCapacity) {
        //Checking for valid initialCapacity
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Invalid capacity");
        }
        else {
            this.data = new Object[initialCapacity];
            this.size = 0;
        }
    }

    /**
     * Constructor with list arg. Size initialized to the valid members of list.
     * Data initialized to a shallow copy of list.
     * 
     * @param Arr - array to be made into MyArrayList
     */
    public MyArrayList (E[] Arr) {
        //Checking if null array
        if (Arr == null) {
            this.data = new Object[5];
            this.size  = 0;
        }
        else {
            this.data = Arr;
            this.size = 0;
            //Using for loop to check for invalid inputs in the array
            for (int i = 0; i < Arr.length; i++) {
                if (Arr[i] != null) {
                    this.size += 1;
                }
                else {
                    if (i > 0 && Arr[i - 1] != null) {
                        this.size++;
                    }
                }
            }
        }
    }

    /**
     * Increases capacity of array as needed. If the required increase is less
     * than 3 more than the arrays capacity, 3 is added to the capacity. If the
     * array was initially of size 0, add 5 to capacity then check if we need 
     * more space.
     * 
     * @param requiredCapacity - total required capacity of the array
     */
    public void expandCapacity (int requiredCapacity) {
        //Checking if requiredCapacity is valid
        if (requiredCapacity < this.data.length) {
            throw new IllegalArgumentException();
        }
        else {
            //Checking if data has a capacity of 0
            if (this.data.length == 0) {
                this.data = new Object[5];
            }
            else {
                //Increasing capacity by 3 if requiredCapacity is not 3 more
                if ((requiredCapacity - this.data.length) <= 3) {
                    Object[] temp = new Object[this.data.length + 3];
                    //Copying the data into the new array
                    for (int i = 0; i < this.data.length; i++) {
                        temp[i] = this.data[i];
                    }
                    this.data = temp;
                }
                else {
                    //Increasing the capacity if requiredCapacity is significant
                    Object[] temp = new Object[requiredCapacity];
                    for (int i = 0; i < this.data.length; i++) {
                        temp[i] = this.data[i];
                    }
                    this.data = temp;
                }
            }
        }
    }

    /**
     * @return the capacity of the array
     */
    public int getCapacity () {
        return this.data.length;
    }

    /**
     * Inserts a valid element into the valid index in the array. Expands the 
     * array if needed.
     * 
     * @param index - index to insert to
     * @param element - element to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert (int index, E element) {
        //Checking for valid index
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        else {
            //Checking if we need to expand the list size
            if (this.size == this.data.length) {
                expandCapacity(this.data.length + 1);
            }
            
            //Inserting the element into the list and moving all the elements up
            // by one index
            E prevelem = (E)this.data[index];
            E nextelem;
            this.data[index] = element;
            for (int i = index + 1; i < this.data.length; i++) {
                nextelem = (E)this.data[i];
                this.data[i] = prevelem;
                prevelem = nextelem;
            }

            //Increasing size
            this.size++;
        }
    }

    /**
     * Appends an element to the end of the array. Expands size if needed.
     * 
     * @param element - element to be appended at end of array
     */
    public void append (E element) {
        //Expanding size if needed
        if (this.size == this.data.length) {
            expandCapacity(this.data.length + 1);
        }

        //Appending the element to the end of the array
        int i;
        for (i = this.data.length - 1; i >= 0; i--) {
            if (this.data[i] != null && i < this.data.length - 1) {
                this.data[i + 1] = element;
                break;
            }
            if (i == 0) {
                this.data[i] = element;
                break;
            }
        }

        //Increasing size only if element is valid
        if (element != null) {
            this.size++;
        }
    }

    /**
     * Prepends an element to the beginning of the array. Expands size if needed
     * 
     * @param element - element to be prepended.
     */
    @SuppressWarnings("unchecked")
    public void prepend (E element) {
        //Increasing capacity if needed
        if (this.size == this.data.length) {
            expandCapacity(this.data.length + 1);
        }
            
        //Adding the element to first index and moving the rest up by one.
        E prevelem = (E)this.data[0];
        E nextelem;
        this.data[0] = element;
        for (int i = 1; i < this.data.length; i++) {
            nextelem = (E)this.data[i];
            this.data[i] = prevelem;
            prevelem = nextelem;
        }

        this.size++;
    }

    /**
     * Returns element at provided index.
     * 
     * @param index - index to be checked
     * @return E - the element at index
     */
    @SuppressWarnings("unchecked")
    public E get (int index) {
        //Checking for valid index
        if (index < 0 || index >= this.data.length) {
            throw new IndexOutOfBoundsException();
        }
        else{
            return (E)this.data[index];
        }
    }

    /**
     * Sets a new value at indexx provided and returns the overwritten value.
     *
     * @param index - index to be changed
     * @param element - element to be set at index
     * @return E - the element that has been replaced
     */
    @SuppressWarnings("unchecked")
    public E set (int index, E element) {
        //Checking if valid index
        if (index < 0 || index >= this.data.length) {
            throw new IndexOutOfBoundsException();
        }
        else{
            //Replacing the value at the index with element
            E cum = (E)this.data[index];
            this.data[index] = element;
            return cum;
        }
    }

    /**
     * Removes the element at valid index and returns the value removed.
     * 
     * @param index - index to remove element
     * @return E - element removed
     */
    @SuppressWarnings("unchecked")
    public E remove (int index) {
        //Checking for valid index
        if (index < 0 || index >= this.data.length) {
            throw new IndexOutOfBoundsException();
        }
        else{
            E cum = (E)this.data[index];

            //Using a for loop to move all elements after index down by 1
            E nextelem = (E)this.data[this.data.length - 1];
            E prevelem;
            this.data[this.data.length - 1] = null;
            for (int i = this.data.length - 2; i > index; i--){
                prevelem = (E)this.data[i - 1];
                this.data[i] = nextelem;
                nextelem = prevelem;
            }

            //Reducing size of the array by 1
            this.size--;
            return cum;
        }
    }

    /**
     * @return size of the array
     */
    public int size () {
        return this.size;
    }
}