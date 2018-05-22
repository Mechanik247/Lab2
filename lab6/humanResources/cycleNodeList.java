package humanResources;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class cycleNodeList<E> implements Iterable<E>{
    private Node first;
    private Node last;
    private int size;
    private static final int DEFAULT_SIZE = 0;

    private class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E item, Node next) {
            this.item = item;
            this.next = next;
        }
        public Node() {
            this.item = null;
            this.next = null;
        }
        public E getItem() {
            return this.item;
        }
    }

    public cycleNodeList() {
        initDefaultList();
    }

    public cycleNodeList(E[] itemArr) {
        initDefaultList();
        if (itemArr != null) {
            last.item = itemArr[0];
            size++;
            for (; size < itemArr.length; size++) {
                last.next = new Node<>(itemArr[size], first);
                last = last.next;
            }
        }
    }

    private void initDefaultList () {
        size = DEFAULT_SIZE;
        last = new Node<>(null,first);
        first = new Node<>(null,last);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        if (last.item == null) {
            last.item = e;
        } else {
            last.next = new Node<>(e, first);
            last = last.next;
        }
        size++;
    }

    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }
        return true;
    }

    public void addArray(E[] arr) {
        if (arr == null) { throw new NullPointerException("Array is null"); }
        int i = 0;
        if (size == 0) {
            last.item = arr[0];
            i++;
            size++;
        }
        for (; i < arr.length; i++) {
            last.next = new Node<>(arr[i], first);
            last = last.next;
            size++;
        }
    }

    public E get(int index) {
        if (size == 0 || index < 0) return null;
        index %= size;
        Node<E> n = first;
        int i = 0;
        while (i < index) {
            i++;
            n = n.next;
        }
        return n.item;
    }

    public E getLast() {
        if (size == 0) return null;
        Node<E> n = first;
        int i = 0;
        while (i < size - 1) {
            i++;
            n = n.next;
        }
        return n.item;
    }

    private Node<E> getLastNode() {
        if (size == 0) return null;
        Node<E> n = first;
        int i = 0;
        while (n.next != first) {
            n = n.next;
        }
        return n;
    }

    public boolean remove(int id) {
        if (id > size || id < 0) { throw new IllegalArgumentException("id:" + id + " illegal id"); }
        if (size == 0) { return false; }
        Node tempLink = first;
        for (int i = 0; i < id; i++) {
            tempLink = tempLink.next;
        }   // в tempLink попадает Node перед удаляемым
        if (id == size-1) {
            tempLink.next = first;
        } else {
            tempLink.next = tempLink.next.next;
        }
        size--;
        return true;
    }

    public boolean remove(Object object) {
        if (size == 0) { return false; }
        Node tempLink = first;
        for (int i = 0; i < size; i++) {
            if(tempLink.next.item.equals(object))
            {
                tempLink.next = tempLink.next.next;
                size--;
                return true;
            }
            tempLink = tempLink.next;
        }
        return false;
    }

    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (Object o : c) {
            if (this.contains(o)) {
                result = true;
                this.remove(o);
            }
        }
        return result;
    }

    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        for (E e : this) {
            if (!c.contains(e)) {
                this.remove(e);
                result = true;
            }
        }

        return result;
    }

    public boolean contains(Object o) {

        E e = (E) o;

        if (size == 0) return false;
        if (size == 1) return first.item.equals(e);
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (node.item.equals(e)) return true;
            node = node.next;
        }
        return false;

    }

    public boolean containsAll(Collection<?> c) {
        for (E e : this) {
            if (!c.contains(e)) {
                return false;
            }
        }
        return true;
    }

    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[])java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (Node x = first; x != first; x = x.next)
            result[i++] = x.item;

        if (a.length > size)
            a[size] = null;

        return a;
    }

    public void clear() {
        size = 0;
        first.next = last;
        last.next = first;
        first = null;
        last = null;
    }

    @Override
    public String toString () {
        Node tempLink = first.next;
        StringBuilder str = new StringBuilder("List with ");
        str.append(size).append(" elements\n");
        for (int i = 0; i < size; i++) {
            str.append(tempLink.item).append("\n");
            tempLink = tempLink.next;
        }
        return str.toString();
    }

    public Object[] toArray() {
        Object[] result = new Object[this.size];
        int count = 0;

        for (E e : this) {
            result[count++] = e;
        }

        return result;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int count = 0;
            Node<E> buffer = first;
            E result;

            @Override
            public boolean hasNext() {

                if (buffer == null) {
                    return false;
                }

                if (buffer.equals(first) && count != 0) {
                    return false;
                }

                if (count >= size) {
                    return false;
                }
                return true;
            }

            @Override
            public E next() {
                count++;
                result = buffer.item;
                buffer = buffer.next;
                return result;
            }
        };
    }

}
