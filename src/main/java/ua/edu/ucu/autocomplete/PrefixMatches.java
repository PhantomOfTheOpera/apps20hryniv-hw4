package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

/**
 *
 * @author andrii
 */
public class PrefixMatches
{

    private Trie trie;

    public PrefixMatches(Trie trie)
    {
        this.trie = trie;
    }

    public int load(String... strings)
    {
        if (strings == null)
        {
            return 0;
        }
        int added = 0;
        for (String s : strings)
        {
            if (s == null) {
                continue;
            }
            String[] words = s.split("\\s+");

            for (String word : words) {
                if (!trie.contains(s) && word.length() > 2)
                {
                    trie.add(new Tuple(word, word.length()));

                    added++;
                }
            }
        }
        return added;
    }


    public boolean contains(String word) { return trie.contains(word); }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref)
    {
        if (pref.length() < 2)
        {
            throw new IllegalArgumentException("Fatal error! Prefix length less than 2!");
        }
        return trie.wordsWithPrefix(pref);
    }


    public Iterable<String> wordsWithPrefix(String pref, int k)
    {
        throw new UnsupportedOperationException("Not supported yet.");        
    }

    public int size() {
        return trie.size();
    }
}
