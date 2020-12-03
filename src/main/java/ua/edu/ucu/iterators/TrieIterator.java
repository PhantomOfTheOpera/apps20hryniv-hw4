package ua.edu.ucu.iterators;

import java.util.Iterator;
import ua.edu.ucu.tries.Node;
import ua.edu.ucu.collections.Queue;

public class TrieIterator implements  Iterator <String>
{

    private Queue queue;
    private String next;

    public TrieIterator(Node node, String s)
    {
        queue = new Queue();
        queue.enqueue(new Object[]{node, s});
        findNext();
    }
    public void findNext()
    {
        while (!queue.isEmpty())
        {
            Node currentNode = (Node) ((Object[]) queue.peek())[0];
            String pref = (String) ((Object[]) queue.dequeue())[1];

            for (int c = 0; c < 256; c++)
            {
                if (currentNode.getNext()[c] != null)
                {
                    queue.enqueue(new Object[]{currentNode.getNext()[c], pref + (char) c});
                }
            }

            if (currentNode.getValue() != -1)
            {
                next = pref;
                return;
            }

        }
        next = null;

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

    public static Iterable<String> wordsIter(Node node, String s)
    {
        return () -> new TrieIterator(node, s);
    }
}
