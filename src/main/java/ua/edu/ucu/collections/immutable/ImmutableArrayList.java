package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList{
    private final Object[] currentArray;

    public ImmutableArrayList(){
        currentArray = new Object[0];
    }

    public ImmutableArrayList(Object[] originArray){
        currentArray = originArray.clone();
    }

    public void checkCorrectIndex(int index){
        if (index >= currentArray.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }
    }

    @Override
    public ImmutableList add(Object e) {
        return add(currentArray.length, e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return addAll(index, new Object[] {e});

    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(currentArray.length, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {

        if (index > currentArray.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }

        int createdArraySize = currentArray.length + c.length;
        Object[] createdArray = new Object[createdArraySize];
        for(int i = 0; i < index; i++){
            createdArray[i] = currentArray[i];
        }
        for(int i = 0; i < c.length; i++){
            createdArray[i + index] = c[i];
        }
        for(int i = index; i < currentArray.length; i++){
            createdArray[c.length + i] = currentArray[i];
        }
        return new ImmutableArrayList(createdArray);
    }

    @Override
    public Object get(int index) {

        checkCorrectIndex(index);

        return currentArray[index];
    }

    @Override
    public ImmutableList remove(int index) {

        checkCorrectIndex(index);

        int createdArraySize = currentArray.length - 1;
        Object[] createdArray = new Object[createdArraySize];

        for (int i = 0; i < index; i++){
            createdArray[i] = currentArray[i];
        }
        for (int j = index + 1; j < currentArray.length; j ++){
            createdArray[j - 1] = currentArray[j];
        }

        return new ImmutableArrayList(createdArray);
    }

    @Override
    public ImmutableList set(int index, Object e) {

        checkCorrectIndex(index);
        Object[] createdArray = currentArray.clone();

        createdArray[index] = e;

        return new ImmutableArrayList(createdArray);
    }

    @Override
    public int indexOf(Object e) {
        for(int index = 0; index < currentArray.length; index++){
            if (currentArray[index] == e){
                return index;
            }
        }
        return -1;
    }

    @Override
    public int size()
    {
        return currentArray.length;
    }

    @Override
    public ImmutableList clear()
    {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty()
    {
        return currentArray.length == 0;
    }

    @Override
    public Object[] toArray()
    {
        return currentArray.clone();
    }
    @Override
    public String toString()
    {
        String str = Arrays.toString(currentArray);
        return str.substring(1,str.length() - 1);
    }
}
