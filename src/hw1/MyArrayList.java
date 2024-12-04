import java.util.Collection;
import java.util.Comparator;
import java.util.Random;


public class MyArrayList<E> {
    private Object[] array;
    private int currentIndex = 0;
    private int currentCapacity;

    public MyArrayList() {
        currentCapacity = 16;
        array = new Object[currentCapacity];
    }

    public MyArrayList(int maxSize) {
        array = new Object[maxSize];
        currentCapacity = maxSize;
    }

    public void add(E element) {
        if (currentIndex == currentCapacity) {
            Object[] newArray = increaseSize();
            System.arraycopy(array, 0, newArray, 0, currentIndex);
            array = newArray;
        }
        array[currentIndex++] = element;
    }

    public void add(int index, E element) {
        if (index < 0) throw new IllegalArgumentException("This index is negative.");
        if (index < currentIndex && currentIndex == currentCapacity) {
            Object[] newArray = increaseSize();
            System.arraycopy(array, 0, newArray, 0, index);
            newArray[index] = element;
            System.arraycopy(array, index, newArray, index + 1, currentIndex - index);
            array = newArray;
            currentIndex++;
        }
        if (index == currentIndex) add(element);
        else {
            System.arraycopy(array, index, array, index + 1, currentIndex - index);
            array[index] = element;
            currentIndex++;
        }
    }

    public void addAll(Collection<? extends E> collection) {
        for (E element : collection) {
            add(element);
        }
    }

    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < currentIndex; i++) {
            array[i] = array[i + 1];
        }
        array[currentIndex] = null;
        currentIndex--;
    }

    public void remove(Object o) {
        for (int i = 0; i < currentIndex; i++) {
            if (o.equals(array[i])) {
                array[i] = array[i + 1];
            }
        }
        array[currentIndex] = null;
        currentIndex--;
    }

    public Object get(int index) {
        checkIndex(index);
        return array[index];
    }

    public void display() {
        for (int i = 0; i < currentIndex; i++) {
            System.out.println(array[i]);
        }
    }

    public void clear() {
        for (Object o : array) {
            o = null;
        }
        currentIndex = 0;
    }

    public void sort(Comparator<? super E> comparator) {
        quickSort(comparator, 0, currentIndex - 1);
    }

    private void quickSort(Comparator<? super E> comparator, int left, int right) {
        if (right > left) {
            int index = partition(left, right, comparator);
        }
    }

    private int partition(int left, int right, Comparator<? super E> comparator) {
        final Random RND = new Random();
        int index = left + RND.nextInt(right - left + 1);
        Object pivot = array[index];
        swap(index, right);
        for (int i = index = left; i < right; ++i) {
            if (comparator.compare((E) array[i], (E)pivot) <= 0) {
                swap(index++, i);
            }
        }
        swap(index, right);
        return index;
    }

    private void swap(int a, int b) {
        Object tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    public boolean isEmpty() {
        return currentIndex == 0;
    }

    public int getCurrentSize() {
        return currentIndex;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void checkIndex(int index) {
        if (isEmpty()) throw new ArrayIndexOutOfBoundsException("This arraylist has no elements.");
        if (index < 0) throw new IllegalArgumentException("This index is negative.");
        if (index > currentCapacity)
            throw new ArrayIndexOutOfBoundsException("This index is out of bounds for this arraylist.");
    }

    public Object[] increaseSize() {
        currentCapacity = currentCapacity * 2;
        return new Object[currentCapacity];
    }
}
