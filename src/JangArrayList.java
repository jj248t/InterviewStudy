import com.sun.java.util.jar.pack.ConstantPool;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

// List class that uses an array for it backing.
// Uses growth in power of 2s.
public class JangArrayList implements List<Integer> {
    private static final int INITIAL_SIZE = 1;
    private int currentSize = INITIAL_SIZE; // get rid of this
    private int currentIndex = 0;
    private int increaseSizeBy = INITIAL_SIZE; // get rid of thsi
    private boolean isEmpty = true; // get rid of this
    private Integer[] arr;

    public JangArrayList() {
        arr = new Integer[INITIAL_SIZE];
    }

    @Override
    public int size() {
        return currentIndex;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(o)) {
                return true;
            }
        }

        return false;
    }

    // do later
    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Integer[] copy = new Integer[currentIndex];

        for (int i = 0; i < currentIndex; i++) {
            copy[i] = arr[i];
        }

        return copy;
    }

    // skip
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Integer integer) {
        resizeIfNecessary(1);

        arr[currentIndex] = integer;
        currentIndex++;
        return true;
    }

    // Probably can use your addAll method
    @Override
    public void add(int index, Integer element) {
        if (index > currentIndex) {
            throw new IndexOutOfBoundsException(index);
        }

        else {
            resizeIfNecessary(1);
            Integer[] temp = new Integer[currentIndex - index];
            int j = 0;
            for (int i = index; i < currentIndex; i++) {
                temp[j] = arr[i];
                j++;
            }

            currentIndex = index;

            add(element);

            for (int i = 0; i < temp.length; i++) {
                add(temp[i]);
            }

        }
    }

    @Override
    public boolean remove(Object o) {
        JangArrayList temp = new JangArrayList();
        temp.add((Integer) o);

        return temp.removeAll(temp);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Collection<Integer> ic = (Collection<Integer>) c;

        for (Integer i : ic) {
            if (contains(i) == false) return false;
        }

        return true;

    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        resizeIfNecessary(c.size());

        for (Integer i : c) {
            add(i);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
        resizeIfNecessary(c.size());
        int thisIndex = index;
        for (Integer i : c) {
            add(thisIndex, i);
            thisIndex++;
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Collection<Integer> ic = (Collection<Integer>) c;

        boolean hasO = false;
        int nullCount = 0;

        for (Integer i : ic) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == i) {
                    hasO = true;
                    arr[j] = null;
                    nullCount++;
                }
            }
        }

        Integer[] temp = new Integer[arr.length - nullCount];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                temp[i] = arr[i];
            }
        }

        arr = temp;

        return hasO;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // tony wrote this
        Collection<Integer> ic = (Collection<Integer>) c;
        Integer[] newArray = new Integer[arr.length];
        int i = 0;
        for (Integer num : ic) {
            if (contains(num)) {
                newArray[i++] = num;
            }
        }
        arr = newArray;
        currentIndex = i;
        return true;
    }

    @Override
    public void clear() {
        currentIndex = 0;
    }

    @Override
    public Integer get(int index) {
        return null;
    }

    @Override
    public Integer set(int index, Integer element) {
        return null;
    }



    @Override
    public Integer remove(int index) {
        if (index >= currentIndex) {
            throw new IndexOutOfBoundsException(index);
        }

        else {
            return arr[index];
        }
    }

    @Override
    public int indexOf(Object o) {
        if(contains(o)) {
            for (int i = 0; i < currentIndex; i++) {
                if (arr[i].equals(o)) {
                    return i;
                }
            }
        }

        else {
            return -1;
        }
    }

    @Override
    public int lastIndexOf(Object o) {
        if(contains(o)) {
            for (int i = currentIndex - 1; i >= 0; i--) {
                if(arr[i].equals(o)) {
                    return i;
                }
            }
        }

        else {
            return -1;
        }
    }

    // do later
    @Override
    public ListIterator<Integer> listIterator() {
        return null;
    }

    // do later
    @Override
    public ListIterator<Integer> listIterator(int index) {
        return null;
    }

    // look at sublist documentation.
    @Override
    public List<Integer> subList(int fromIndex, int toIndex) {
        JangArrayList temp = new JangArrayList;

        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException(fromIndex);
        }
        else if (toIndex > currentIndex) {
            throw new IndexOutOfBoundsException(toIndex);
        }
        else if (fromIndex > toIndex) {
            throw new IllegalArgumentException();
        }
        else {
            for (int i = 0; i < toIndex; i++) {
                temp.add(arr[i]);
            }
        }

        return temp;
    }

    private void resizeIfNecessary(int size) {
        int neededSize = currentIndex + size;
        int newSize = arr.length;

        if (neededSize > arr.length) {
            while (newSize < neededSize ) {
                newSize *= 2;
            }

            Integer[] newArr = new Integer[newSize];

            for (int i = 0; i < currentIndex; i++) {
                newArr[i] = arr[i];
            }

            arr = newArr;
        }
    }
}