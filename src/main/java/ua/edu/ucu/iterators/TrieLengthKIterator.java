package ua.edu.ucu.iterators;

import java.util.Collections;
import java.util.Iterator;

public class TrieLengthKIterator implements Iterator<String>
{
    private Iterator<String> iter;
    private int k;
    private String next;

    private int wordHighestLength = -1;
    private int numOfWordsDiffLength = 0;

    public TrieLengthKIterator(Iterator<String> iter, int k)
    {
        if (k <= 0)
        {
            this.iter = Collections.emptyIterator();
        }
        else
        {
            this.k = k;
            this.iter = iter;
            findNext();
        }
    }

    private void findNext()
    {
        if (!iter.hasNext())
        {
            next = null;
            return;
        }
        next = iter.next();

        if (numOfWordsDiffLength == k && next.length() > wordHighestLength)
        {
            next = null;
            return;
        }

        if (next.length() > wordHighestLength)
        {
            numOfWordsDiffLength++;
            wordHighestLength = next.length();
        }
    }


    @Override
    public boolean hasNext()
    {
        return next != null;
    }

    @Override
    public String next()
    {
        String inter = next;
        findNext();
        return inter;
    }

    public static Iterable<String> words(Iterable<String> iter, int k)
    {
        return () -> new TrieLengthKIterator(iter.iterator(), k);
    }
}