package ua.edu.ucu.tries;


import ua.edu.ucu.iterators.TrieIterator;

import java.util.Collections;

public class RWayTrie implements Trie
{

    public static final int R;

    static
    {
        R = 256;
    }

    private Node root;
    private int len;

    public RWayTrie()
    {
        root = new Node();
        this.len = 0;
    }

    public int getNode(String word)
    {
        Node node = getNode(root, word, 0);
        if (node == null)
        {
            return -1;
        }
        return node.getValue();
    }

    private Node getNode(Node node, String term, int ind)
    {
        if (node == null)
        {
            return null;
        }
        if (ind == term.length())
        {
            return node;
        }
        char c = term.charAt(ind);
        return getNode(node.next[c], term, ind + 1);
    }


    @Override
    public void add(Tuple t)
    {

        root = add(root, t.term, t.weight, 0);
        len += 1;
    }

    private Node add(Node node, String term, int weight, int d)
    {
        if (node == null)
        {
            node = new Node();
        }

        assert term != null;
        if (d == term.length())
        {
           node.setValue(weight);
            return node;
        }
        char c = term.charAt(d);
        node.next[c] = add(node.next[c], term, weight, d+1);
        return node;
    }

    @Override
    public boolean contains(String word)
    {
        return getNode(word) != -1;
    }


    @Override
    public boolean delete(String word)
    {
      if (size() == 0 || !contains(word))
      {
          return false;
      }
      else
      {
          len -= 1;
          root = delete(root, word, 0);
          return true;
      }
    }


    private Node delete(Node node, String key, int d)
    {
        if (node == null)
        {
            return null;
        }
        if (d == key.length())
            node.setValue(-1);
        else
        {
            char c = key.charAt(d);
            node.next[c] = delete(node.next[c], key, d+1);
        }
        if (node.getValue() != -1) return node;

        for (char c = 0; c < R; c++)
            if (node.next[c] != null) return node;
        return null;
    }

    @Override
    public Iterable<String> words()
    {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Node node = getNode(root, s, 0);
         return TrieIterator.wordsIter(node, s);
    }



    @Override
    public int size() {
       return len;
    }

}


