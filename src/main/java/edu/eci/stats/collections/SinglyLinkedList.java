package edu.eci.stats.collections;

import java.util.*;
import java.util.function.Consumer;

public class SinglyLinkedList<E> implements List<E> {

    private static final class Node<E> {
        E value;
        Node<E> next;
        Node(E value) { this.value = value; }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public boolean add(E e) {
        Node<E> n = new Node<>(e);
        if (head == null) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
        return true;
    }


    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean changed = false;
        for (E e : c) changed |= add(e);
        return changed;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean contains(Object o) { return indexOf(o) >= 0; }

    @Override
    public int indexOf(Object o) {
        int i = 0;
        for (Node<E> cur = head; cur != null; cur = cur.next) {
            if (Objects.equals(cur.value, o)) return i;
            i++;
        }
        return -1;
    }


    @Override
    public int lastIndexOf(Object o) {
        int i = 0, last = -1;
        for (Node<E> cur = head; cur != null; cur = cur.next) {
            if (Objects.equals(cur.value, o)) last = i;
            i++;
        }
        return last;
    }


    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        for (Node<E> cur = head; cur != null; cur = cur.next) arr[i++] = cur.value;
        return arr;
        }


    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        int i = 0; Object[] arr = a;
        for (Node<E> cur = head; cur != null; cur = cur.next) arr[i++] = cur.value;
        if (a.length > size) a[size] = null;
        return a;
    }


    @Override
    public E get(int index) { return nodeAt(index).value; }


    @Override
    public E set(int index, E element) {
        Node<E> n = nodeAt(index);
        E old = n.value;
        n.value = element;
        return old;
    }
    @Override
    public boolean remove(Object o) {
        Node<E> prev = null, cur = head;
        while (cur != null) {
            if (Objects.equals(cur.value, o)) {
                unlink(prev, cur);
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index) {
        checkElementIndex(index);
        Node<E> prev = null, cur = head;
        for (int i = 0; i < index; i++) { prev = cur; cur = cur.next; }
        E val = cur.value;
        unlink(prev, cur);
        return val;
    }


    private void unlink(Node<E> prev, Node<E> cur) {
        if (prev == null) { // removing head
            head = cur.next;
        } else {
            prev.next = cur.next;
        }
        if (cur == tail) tail = prev;
        size--;
    }


    private Node<E> nodeAt(int index) {
        checkElementIndex(index);
        Node<E> cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur;
    }


    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    // --------- Métodos no necesarios marcados como no soportados ---------
    @Override public void add(int index, E element) { throw new UnsupportedOperationException(); }
    @Override public boolean addAll(int index, Collection<? extends E> c) { throw new UnsupportedOperationException(); }
    @Override public ListIterator<E> listIterator() { throw new UnsupportedOperationException(); }
    @Override public ListIterator<E> listIterator(int index) { throw new UnsupportedOperationException(); }
    @Override public List<E> subList(int fromIndex, int toIndex) { throw new UnsupportedOperationException(); }
    @Override public boolean containsAll(Collection<?> c) { for (Object o : c) if (!contains(o)) return false; return true; }
    @Override public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override public boolean removeAll(Collection<?> c) { boolean ch=false; for(Object o:c) ch|=remove(o); return ch; }
    @Override public boolean equals(Object o) { if (this==o) return true; if (!(o instanceof List<?> other)) return false; return Objects.equals(this.size, other.size()) && Arrays.equals(this.toArray(), other.toArray()); }
    @Override public int hashCode() { return Arrays.hashCode(toArray()); }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> current = head;
            Node<E> previous = null;
            boolean canRemove = false;


            @Override public boolean hasNext() { return current != null; }
            @Override public E next() {
                if (current == null) throw new NoSuchElementException();
                E val = current.value;
                previous = current;
                current = current.next;
                canRemove = true;
                return val;
            }
            @Override public void remove() {
                if (!canRemove) throw new IllegalStateException();
                SinglyLinkedList.this.remove(previous.value);
                canRemove = false;
            }
            @Override public void forEachRemaining(Consumer<? super E> action) { Objects.requireNonNull(action); while (hasNext()) action.accept(next()); }
        };
    }
}