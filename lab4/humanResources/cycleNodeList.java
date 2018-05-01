package humanResources;

import java.util.Arrays;
import java.util.LinkedList;

public class cycleNodeList<E> {
    private Node first;
    private Node last;
    private int size;
    private static final int DEFAULT_SIZE = 0;

    private class Node {
        private E item;
        private Node next;

        public Node(E item, Node next) {
            this.item = item;
            this.next = next;
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
                last.next = new Node(itemArr[size], first);
                last = last.next;
            }
        }
    }

    private void initDefaultList () {
        size = DEFAULT_SIZE;
        last = new Node(null,first);
        first = new Node(null,last);
    }

    public int length() {
        return this.size;
    }


    public void add(E e) {
        if (last.item == null) {
            last.item = e;
        } else {
            last.next = new Node(e, first);
            last = last.next;
        }
        size++;
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
            last.next = new Node(arr[i], first);
            last = last.next;
            size++;
        }
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

    public E getItem(int id) {
        if (id > size || id < 0) { throw new IllegalArgumentException("Illegal id :" + id); }
        Node tempLink = first;
        for (int i = -1; i < id; i++) {
            tempLink = tempLink.next;
        }
        return tempLink.item;
    }


    public boolean contains (E obj) {
        Node tempLink = first.next;
        try {
            for (int i = 0; i < size; i++) {
                if (tempLink.item.equals(obj)) {
                    return true;
                } else {
                    tempLink = tempLink.next;
                }
            }
        } catch (NullPointerException exp) {
            System.out.println("Cant execute .equals on null object");
        }
        return false;
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

}
