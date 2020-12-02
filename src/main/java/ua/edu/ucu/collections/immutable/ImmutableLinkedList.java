package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private Node tail;
    private int queueSize;

    public ImmutableLinkedList() {
        this.head = this.tail = null;
    }


    public ImmutableLinkedList(Object[] origin) {
        this();
        if (origin.length != 0) {
            this.head = this.tail = new Node(origin[0]);
            queueSize = origin.length;

            Node current = this.tail;

            for (int i = 1; i < origin.length; i += 1) {
                Node next = new Node(origin[i]);
                next.setPrevious(current);
                current.setNext(next);
                current = next;
            }
            this.tail = current;

        }
    }

    public void checkCorrectIndex(int index) {
        if (index >= queueSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }
    }

    @Override
    public ImmutableList add(Object e) {
        return add(queueSize, e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(queueSize, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index > queueSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }

        ImmutableArrayList elements = new ImmutableArrayList(this.toArray());
        ImmutableArrayList complete = (ImmutableArrayList) elements.addAll(index, c);

        return new ImmutableLinkedList(complete.toArray());
    }

    private Node getNode(int index) {
        checkCorrectIndex(index);
        Node current = this.head;

        for (int i = 0; i < index; i++) {
            current = current.Next();
        }
        return current;
    }

    @Override
    public Object get(int index) {
        return getNode(index).Value();
    }

    @Override
    public ImmutableList remove(int index) {
        checkCorrectIndex(index);
        ImmutableArrayList elements = new ImmutableArrayList(this.toArray());
        ImmutableArrayList complete = (ImmutableArrayList) elements.remove(index);

        return new ImmutableLinkedList(complete.toArray());

    }

    @Override
    public ImmutableList set(int index, Object e) {
        checkCorrectIndex(index);
        Object[] elements = this.toArray();
        elements[index] = e;
        return new ImmutableLinkedList(elements);
    }

    @Override
    public int indexOf(Object e) {
        Node current = head;
        for (int i = 0; i < queueSize; i++) {
            if (current.Value().equals(e)) {
                return i;
            }
            current = current.Next();
        }
        return -1;
    }

    @Override
    public int size() {
        return queueSize;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return queueSize == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] elementsArray = new Object[queueSize];
        Node current = head;
        for (int i = 0; i < queueSize; i++) {
            elementsArray[i] = current.Value();
            current = current.Next();
        }
        return elementsArray;
    }

    @Override
    public String toString() {
        String str = Arrays.toString(this.toArray());
        return str.substring(1, str.length() - 1);
    }

// Додаткові методи (завдання 2)
    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList)add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList)add(e);
    }

    public Object getFirst() {
        return get(0);
    }

    public Object getLast() {
        return get(queueSize - 1);
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList)remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList)remove(queueSize - 1);
    }
}
