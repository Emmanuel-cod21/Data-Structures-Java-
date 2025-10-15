import java.util.Collection;

public interface MyList<E> extends Collection<E> {
    /** Add a new element at the specified index in this list */
    public void add(int index, E e);

    /** Return the element from this list at the specified index */
    public E get(int index);

    /** Return the index of the first matching element in this list.
     * Return -1 if no match. */
    public int indexOf(Object e);

    /** Return the index of the last matching element in this list
     * Return -1 if no match. */
    public int lastIndexOf(E e);

    /** Remove the element at the specified position in this list
     * Shift any subsequent elements to the left.
     * Return the element that was removed from the list. */
    public E remove(int index);

    /** Replace the element at the specified position in this list
     * with the specified element and returns the new set. */
    public E set(int index, E e);

    @Override
    /** Add a new element at the end of this list */
    public default boolean add(E e) {
        add(size(), e);
        return true;
    }

    @Override
    /** Return true if this list contains no elements */
    public default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    /** Remove the first occurrence of the element e
     * from this list. Shift any subsequent elements to the left.
     * Return true if the element is removed. */
    public default boolean remove(Object e) {
        if (indexOf(e) >= 0) {
            remove(indexOf(e));
            return true;
        }
        else return false;
    }

    @Override
    /** Return true if this list contains all elements in the specified collection */
    public default boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    /** Add all elements from the specified collection to this list */
    public default boolean addAll(Collection<? extends E> c) {
        boolean modified = false;
        for (E element : c) {
            if (add(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    /** Remove all elements from this list that are contained in the specified collection */
    public default boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object element : c) {
            while (remove(element)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    /** Retain only the elements in this list that are contained in the specified collection */
    public default boolean retainAll(Collection<?> c) {
        boolean modified = false;
        java.util.Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (!c.contains(iterator.next())) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    /** Return an array containing all elements in this list */
    public default Object[] toArray() {
        Object[] result = new Object[size()];
        for (int i = 0; i < size(); i++) {
            result[i] = get(i);
        }
        return result;
    }

    @Override
    /** Return an array containing all elements in this list; 
     * the runtime type of the returned array is that of the specified array */
    @SuppressWarnings("unchecked")
    public default <T> T[] toArray(T[] array) {
        if (array.length < size()) {
            // Create a new array of the same type but with the correct size
            return (T[]) java.lang.reflect.Array.newInstance(
                array.getClass().getComponentType(), size());
        }
        
        // Copy elements to the provided array
        for (int i = 0; i < size(); i++) {
            array[i] = (T) get(i);
        }
        
        // If array is larger than the list, set the element after the last list element to null
        if (array.length > size()) {
            array[size()] = null;
        }
        
        return array;
    }
}