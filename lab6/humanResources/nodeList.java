package humanResources;

import java.util.*;

public class nodeList<E> implements Iterable<E>, List<E> {
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
        public void setItem(E e) {
            this.item = e;
        }
    }

    private Node<E> getNode(int index) {
        Node<E> result = first;

        for (int i = 0; i < index; i++) {
            result = result.next;
        }

        return result;
    }

    public nodeList() {
        initDefaultList();
    }

    public nodeList(E[] itemArr) {
        initDefaultList();
        if (itemArr != null) {
            last.item = itemArr[0];
            size++;
            for (; size < itemArr.length; size++) {
                last.next = new Node<>(itemArr[size], null);
                last = last.next;
            }
        }
    }

    private void initDefaultList () {
        size = DEFAULT_SIZE;
        last = new Node<>(null,null);
        first = new Node<>(null,last);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(E e) {
        if (last.item == null) {
            last.item = e;
        } else {
            last.next = new Node<>(e, null);
            last = last.next;
        }
        size++;
        return true;
    }

    public boolean addAll(Collection<? extends E> c) {
        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Wrong index");
        }

        Node<E> buffer = first;

        int insertIndex = 0;

        while (this.listIterator().hasNext()) {

            if (insertIndex == index) {
                for (E e : c) {
                    buffer.next = new Node<>(e, buffer.next);
                }
                return true;
            }

            insertIndex++;
        }
        return false;
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
            last.next = new Node<>(arr[i], null);
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

    @Override
    public E set(int index, E element) {
        Node<E> node = getNode(index);

        E e = node.item;
        node.item = element;

        return e;
    }

    @Override
    public void add(int index, E element) {

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
        while (n.next != null) {
            n = n.next;
        }
        return n;
    }

    public E remove(int id) {
        if (id > size || id < 0) { throw new IllegalArgumentException("id:" + id + " illegal id"); }
        if (size == 0) { return null; }
        Node tempLink = first, returnNode = null;
        for (int i = 0; i < id; i++) {
            tempLink = tempLink.next;
        }   // в tempLink попадает Node перед удаляемым
        if (id == size-1) {
            returnNode = tempLink.next;
            tempLink.next = null;
        } else {
            returnNode = tempLink.next;
            tempLink.next = tempLink.next.next;
        }
        size--;
        return (E)returnNode.item;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int count = -1;
        int result = -1;
        for (E e : this) {
            count++;
            if (e.equals(o)) {
                result = count;
            }
        }
        return result;
    }

    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (size != 0 && index != 0) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Wrong Index");
            }
        }

        return new ListIterator<E>() {

            Node<E> buffer = first;
            E result;
            int count = 0;

            {
                while (count < index) {
                    buffer = buffer.next;
                    count++;
                }
            }


            @Override
            public boolean hasNext() {
                return buffer != null;
            }

            @Override
            public E next() {
                count++;
                result = buffer.item;
                buffer = buffer.next;
                return result;
            }

            @Override
            public boolean hasPrevious() {
                return count > 0;
            }

            @Override
            public E previous() {
                //ура односвязный список ееее
                count--;
                return get(count);
            }

            @Override
            public int nextIndex() {
                return count;
            }

            @Override
            public int previousIndex() {
                return count - 1;
            }

            @Override
            public void remove() {

                Node<E> previous = getNode(previousIndex());

                previous.next = previous.next.next;

                //хз как тестить
            }

            @Override
            public void set(E e) {
                buffer.setItem(e);
            }

            @Override
            public void add(E e) {
                //хз как тестить
                Node<E> previous = getNode(previousIndex());

                previous.next = new Node<E>(e, buffer);
            }
        };
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= size || toIndex < 0 || toIndex >= size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("Incorrect index");
        }

        List<E> result = new LinkedList<>();
        Node<E> indexNode = getNode(fromIndex);

        for (int i = fromIndex; i <= toIndex; i++) {
            result.add(indexNode.getItem());
            indexNode = indexNode.next;
        }
        return result;
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
        for (Node x = first; x != null; x = x.next)
            result[i++] = x.item;

        if (a.length > size)
            a[size] = null;

        return a;
    }

    public void clear() {
        size = 0;
        first.next = last;
        last.next = null;
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
