package ua.edu.ucu.tries;

import java.util.HashMap;

public class Node
{

    private static final int R = 256;
    private int value;
    public Node[] next;


    public Node ()
    {
        this.value = -1;
        this.next = new Node[R];

    }
    private Node previous;

    public Node getPrevious() {
        return previous;
    }

    public Node[] getNext(){
        return next;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public void setNext(Node[] next){
        this.next = next;
    }

    public int getValue(){
        return value;
    }

    public Node setValue(int value)
    {
        this.value = value;
        return this;
    }


}
