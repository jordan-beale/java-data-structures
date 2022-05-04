package uk.ac.warwick.cs126.structures;


public class Node<E extends Comparable<E>>{
    private E value;
    private int height;
    private Node<E> left;
    private Node<E> right;

    public Node(E value){
        this.value=value;
    }
    
    public E getValue(){
        return this.value;
    }

    public int getHeight(){
        return this.height;
    }

    public Node<E> getLeft() { 
        return this.left; 
    }

    public Node<E> getRight() {
        return this.right; 
    }
    
    public void setValue(E value) { 
        this.value = value; 
    }

    public void setHeight(int height){
        this.height=height;
    }

    public void setLeft(Node<E> node) {
        this.left = node; 
    }

    public void setRight(Node<E> node) {
        this.right = node; 
    }
}