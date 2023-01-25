
public class MyArrayList<E> implements MyList<E> {
    Object[] data;
    int size;

    public MyArrayList () {
        this.data = new Object[5];
        this.size  = 0;
    }

    public MyArrayList (int  initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        else {
            this.data = new Object[initialCapacity];
            this.size = 0;
        }
    }

    public MyArrayList (E[] Arr) {
        if (Arr == null) {
            this.data = new Object[5];
            this.size  = 0;
        }
        else {
            this.data = Arr;
            this.size = Arr.length;
        }
    }

    public void expandCapacity (int requiredCapacity) {
        if (this.data.length == 0) {
            this.data = new Object[5];
        }
        else {
            if ((requiredCapacity - this.data.length) <= 3) {
                Object[] temp = new Object[this.data.length + 3];
                for (int i = 0; i < this.data.length; i++) {
                    temp[i] = this.data[i];
                }
                this.data = temp;
            }
            else {
                Object[] temp = new Object[requiredCapacity];
                for (int i = 0; i < this.data.length; i++) {
                    temp[i] = this.data[i];
                }
                this.data = temp;
            }
        }
    }

    public int getCapacity () {
        return this.data.length;
    }

    @SuppressWarnings("unchecked")
    public void insert (int index, E element) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }
        else {
            if (this.size == this.data.length) {
                expandCapacity(this.data.length + 1);
            }
            
            E prevelem = (E)this.data[index];
            E nextelem;
            this.data[index] = element;
            for (int i = index + 1; i < this.data.length; i++) {
                nextelem = (E)this.data[i];
                this.data[i] = prevelem;
                prevelem = nextelem;
            }

            this.size++;
        }
    }

    public void append (E element) {
        if (this.size == this.data.length) {
            expandCapacity(this.data.length + 1);
        }

        int i;
        for (i = 0; i < this.data.length; i++) {
            if (this.data[i] == null) {
                this.data[i] = element;
                break;
            }
        }
        if (i == this.data.length - 1) {
            this.data[this.data.length - 1] = element;
        }
        this.size++;
    }

    @SuppressWarnings("unchecked")
    public void prepend (E element) {
        if (this.size == this.data.length) {
            expandCapacity(this.data.length + 1);
        }
        
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

    @SuppressWarnings("unchecked")
    public E get (int index) {
        if (index < 0 || index >= this.data.length) {
            throw new IndexOutOfBoundsException();
        }
        else{
            return (E)this.data[index];
        }
    }

    @SuppressWarnings("unchecked")
    public E set (int index, E element) {
        if (index < 0 || index >= this.data.length) {
            throw new IndexOutOfBoundsException();
        }
        else{
            E cum = (E)this.data[index];
            this.data[index] = element;
            return cum;
        }
    }

    @SuppressWarnings("unchecked")
    public E remove (int index) {
        if (index < 0 || index >= this.data.length) {
            throw new IndexOutOfBoundsException();
        }
        else{
            E cum = (E)this.data[index];

            E nextelem = (E)this.data[this.data.length - 1];
            E prevelem;
            this.data[this.data.length - 1] = null;
            for (int i = this.data.length - 2; i > index; i--){
                prevelem = (E)this.data[i - 1];
                this.data[i] = nextelem;
                nextelem = prevelem;
            }

            this.size--;
            return cum;
        }
    }

    public int size () {
        return this.size;
    }
}