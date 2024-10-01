import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class URLinkedList<E> implements URList<E> {
    private URNode<E> head;
    private URNode<E> tail;
    private int size;

    public URLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    private class URLinkedListIterator implements Iterator<E> {
        private URNode<E> curr = head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E data = curr.e;
            curr = curr.n;
            return data;
        }
    }

    // Appends the specified element to the end of this list
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    // Inserts the specified element at the specified position in this list
    public void add(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size + "!");
        }

        if (size == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            URNode<E> curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.n;
            }
            URNode<E> newNode = new URNode<>(element, curr.p, curr);
            URNode<E> prevNode = curr.p;
            prevNode.n = newNode;
            curr.p = newNode;
            size++;
        }
    }

    // Appends all of the elements in the specified collection to the end of this list,
    // in the order that they are returned by the specified collection's iterator
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Cannot add elements from null collection!");
        }

        if (c.isEmpty()) return false;

        for (E element : c) {
            addLast(element);
        }
        return true;
    }

    // Inserts all of the elements in the specified collection into this list
    // at the specified position
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size + "!");
        }

        if (c == null) {
            throw new NullPointerException("Cannot add elements from null collection!");
        }

        if (c.isEmpty()) return false;

        if (index == size) {
            return addAll(c);
        }

        URNode<E> nextNode = head;
        for (int i = 0; i < index; i++) {
            nextNode = nextNode.n;
        }

        URNode<E> prevNode = nextNode.p;

        for (E element : c) {
            URNode<E> newNode = new URNode<>(element, prevNode, null);
            if (prevNode == null) {
                head = newNode;
            } else {
                prevNode.n = newNode;
            }
            prevNode = newNode;
            size++;
        }
        return true;
    }

    // Removes all of the elements from this list
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Returns true if this list contains the specified element.
    public boolean contains(Object o) {
        URNode<E> curr = head;
        if (o == null) {
            while (curr != null) {
                if (curr.e == null) {
                    return true;
                }
                curr = curr.n;
            }
        } else {
            while (curr != null) {
                if (o.equals(curr.e)) {
                    return true;
                }
                curr = curr.n;
            }
        }
        return false;
    }

    // Returns true if this list contains all of the elements of the specified collection
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("The specified collection is null");
        }

        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }

        return true;
    }

    // Compares the specified object with this list for equality.
    // Returns true if both contain the same elements. Ignore capacity
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof URLinkedList)) return false;

        URLinkedList<E> that = (URLinkedList<E>) o;

        if (this.size != that.size) return false;

        URNode<E> thisNode = this.head;
        URNode<E> thatNode = that.head;

        while (thisNode != null) {
            if (!Objects.equals(thisNode.e, thatNode.e)) {
                return false;
            }
            thisNode = thisNode.n;
            thatNode = thatNode.n;
        }

        return true;
    }

    // Returns the element at the specified position in this list.
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size + "!");
        }

        if (index < size / 2) {
            URNode<E> curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.n;
            }
            return curr.e;
        } else {
            URNode<E> curr = tail;
            for (int i = size - 1; i > index; i--) {
                curr = curr.p;
            }
            return curr.e;
        }
    }

    // Returns the index of the first occurrence of the specified element in this list,
    // or -1 if this list does not contain the element.
    public int indexOf(Object o) {
        URNode<E> curr = head;
        int index = 0;
        if (o == null) {
            while (curr != null) {
                if (curr.e == null) {
                    return index;
                }
                curr = curr.n;
                index++;
            }
            return -1;
        } else {
            while (curr != null) {
                if (Objects.equals(curr.e, o)) {
                    return index;
                }
                curr = curr.n;
                index++;
            }
            return -1;
        }
    }

    // Returns true if this list contains no elements.
    public boolean isEmpty() {
        if (head == null && tail == null && size == 0) return true;
        else return false;
    }

    // Returns an iterator over the elements in this list in proper sequence.
    public Iterator<E> iterator() {
        return new URLinkedListIterator();
    }

    // Removes the element at the specified position in this list
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size + "!");
        }

        URNode<E> temp = new URNode<>(null, null, head);
        URNode<E> prev = temp;
        URNode<E> curr = head;

        for (int i = 0; i < index; i++) {
            prev = curr;
            curr = curr.n;
        }

        // Remove the node
        prev.n = curr.n;
        if (curr.n != null) {
            curr.n.p = prev;
        }

        // Update head and tail if necessary
        head = temp.n;
        if (index == size - 1) {
            tail = prev;
        }

        size--;
        return curr.e;
    }

    // Removes the first occurrence of the specified element from this list,
    // if it is present
    public boolean remove(Object o) {
        URNode<E> temp = new URNode<>(null, null, head);
        URNode<E> prev = temp;
        URNode<E> curr = head;

        if (o == null) {
            while (curr != null) {
                if (curr.e == null) {
                    prev.n = curr.n;
                    head = temp.n;
                    size--;
                    return true;
                }
                prev = curr;
                curr = curr.n;
            }
        } else {
            while (curr != null) {
                if (o.equals(curr.e)) {
                    prev.n = curr.n;
                    head = temp.n;
                    size--;
                    return true;
                }
                prev = curr;
                curr = curr.n;
            }
        }

        head = temp.n;
        return false;
    }

    // Removes from this list all of its elements that are contained
    //  in the specified collection
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }

        boolean removed = false;
        URNode<E> temp = new URNode<>(null, null, head);
        URNode<E> prev = temp;
        URNode<E> curr = head;

        while (curr != null) {
            if (c.contains(curr.e)) {
                // Element should be removed
                prev.n = curr.n;
                if (curr.n != null) {
                    curr.n.p = prev;
                }
                size--;
                removed = true;
            } else {
                // Element should be kept
                prev = curr;
            }
            curr = curr.n;
        }

        // Update head and tail
        head = temp.n;
        if (head == null) {
            tail = null;
        } else {
            head.p = null;
            // Update tail if necessary
            if (prev != temp) {
                tail = prev;
            }
        }

        return removed;
    }

    // Replaces the element at the specified position in this list
    // with the specified element
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size + "!");
        }

        URNode<E> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.n;
        }

        E oldElement = curr.e;
        curr.e = element;

        return oldElement;
    }

    // Returns the number of elements in this list.
    public int size() {
        return size;
    }

    // Returns a view of the portion of this list
    // between the specified fromIndex, inclusive, and toIndex, exclusive.
    public URList<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex > toIndex || toIndex > size) {
            throw new IndexOutOfBoundsException("Invalid index range: fromIndex=" + fromIndex + ", toIndex=" + toIndex + ", size=" + size);
        }

        URList<E> newList = new URLinkedList<>();
        URNode<E> curr = head;

        // Move to the fromIndex
        for (int i = 0; i < fromIndex; i++) {
            curr = curr.n;
        }

        // Add elements from fromIndex to toIndex-1
        for (int i = fromIndex; i < toIndex; i++) {
            newList.add(curr.e);
            curr = curr.n;
        }

        return newList;
    }


    // Returns an array containing all of the elements in this list
    //  in proper sequence (from first to the last element).
    public Object[] toArray() {
        Object[] array = new Object[size];
        URNode<E> curr = head;
        int index = 0;

        while (curr != null) {
            array[index] = curr.e;
            curr = curr.n;
            index++;
        }

        return array;
    }

    public void addFirst(E e) {
        URNode<E> newNode = new URNode<>(e, null, head);

        if (size == 0) {
            tail = newNode;
        } else {
            head.p = newNode;
        }
        head = newNode;
        size++;
    }

    public void addLast(E e) {
        URNode<E> newNode = new URNode<>(e, tail, null);

        if (size == 0) {
            head = newNode;
        } else {
            tail.n = newNode;
        }
        tail = newNode;
        size++;
    }

    public E peekFirst() {
        if (size == 0) {
            return null;
        }
        return head.e;
    }

    public E peekLast() {
        if (size == 0) {
            return null;
        }
        return tail.e;
    }

    public E pollFirst() {
        if (size == 0) {
            return null;
        }

        E element = head.e;
        URNode<E> nextNode = head.n;

        if (nextNode == null) {
            // List had only one element
            head = null;
            tail = null;
        } else {
            nextNode.p = null;
            head = nextNode;
        }

        size--;
        return element;
    }

    public E pollLast() {
        if (size == 0) {
            return null;
        }

        E element = tail.e;
        URNode<E> prevNode = tail.p;

        if (prevNode == null) {
            // List had only one element
            head = null;
            tail = null;
        } else {
            prevNode.n = null;
            tail = prevNode;
        }

        size--;
        return element;
    }
}