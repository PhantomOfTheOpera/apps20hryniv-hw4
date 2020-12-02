package ua.edu.ucu.collections.immutable;

public class Node {

    private Object value;
    private Node next;
    private Node previous;

    public Node Previous() {
        return previous;
    }
    public Node Next(){
        return next;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public Object Value(){
        return value;
    }

    public Node (Object value){
        this.value = value;
    }
}
