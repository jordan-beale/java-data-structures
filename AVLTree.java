package uk.ac.warwick.cs126.structures;


import uk.ac.warwick.cs126.structures.Node;
import uk.ac.warwick.cs126.structures.MyArrayList;

public class AVLTree<E extends Comparable<E>>{
    private Node<E> root;
    private int size;

    public AVLTree(){
        this.size=0;
        this.root=null;
    }

    public int getSize(){
        return this.size;
    }

    public void insert(E value){
        this.root=insert(root,value);
        this.size++;
    }

    public void delete(E value){
        this.root=delete(root,value);
    }

    public Node getRoot(){
        return this.root;
    }

    private int height(){
        if(this.root==null){
            return -1;
        }else{
            return this.root.getHeight();
        }
    }

    public E find(E value){
        Node temp = this.root;
        while(temp!=null){
            if(temp.getValue().compareTo(value)==0){
                break;
            }

            if(temp.getValue().compareTo(value)<0){
                temp=temp.getRight();
            }else{
                temp=temp.getLeft();
            }
        }
        return (E) temp.getValue();
    }

    //inserts element into ordered position; checks if it needs to balance tree.
    private Node insert(Node node, E value){
        if(node==null){
            return new Node(value);
        }else if(node.getValue().compareTo(value)>0){
            node.setLeft(insert(node.getLeft(),value));
        }else if(node.getValue().compareTo(value)<0){
            node.setRight(insert(node.getRight(),value));
        }else{
            this.size=this.size-3;
            return null;
        }
        return balance(node);
    }

    //left-most object in tree
    private Node head(Node node){
        Node temp=node;
        while(temp.getLeft()!=null){
            temp=temp.getLeft();
        }
        return temp;
    }

    private Node balance(Node c){
        updateHeight(c);
        int balance=getBalance(c);
        if(balance>1){
            if(height(c.getRight().getRight())>height(c.getRight().getLeft())){
                c=rotateLeft(c);
            }else{
                c.setRight(rotateRight(c.getRight()));
                c=rotateLeft(c);
            }
        }else if(balance<-1){
            if(height(c.getLeft().getLeft())>height(c.getLeft().getRight())){
                c=rotateRight(c);
            }else{
                c.setLeft(rotateLeft(c.getLeft()));
                c=rotateRight(c);
            }
        }
        return c;
    }

    private Node delete(Node node, E value){
        if(node==null){
            return node;
        }else if(node.getValue().compareTo(value)>0){
            node.setLeft(delete(node.getLeft(),value));
        }else if(node.getValue().compareTo(value)<0){
            node.setRight(delete(node.getRight(),value));
        }else{
            if(node.getLeft()==null || node.getRight()==null){
                if(node.getLeft()==null){
                    node=node.getRight();
                }else{
                    node=node.getLeft();
                }
            }else{
                Node head=head(node.getRight());
                node.setValue(head.getValue());
                node.setRight(delete(node.getRight(),value));
            }
        }
        if(node!=null){
            node=balance(node);
        }
        this.size--;
        return node;
    }

    private Node rotateRight(Node b){
        Node<E> a=b.getLeft();
        Node<E> c=a.getRight();
        a.setRight(b);
        b.setLeft(c);
        updateHeight(b);
        updateHeight(a);
        return a;
    }

    private Node rotateLeft(Node b){
        Node<E> a=b.getRight();
        Node<E> c=a.getLeft();
        a.setLeft(b);
        b.setRight(c);
        updateHeight(b);
        updateHeight(a);
        return a;
    }

    private int max(int a, int b){
        if(a>=b){
            return a;
        }else{
            return b;
        }
    }

    private void updateHeight(Node node){
        node.setHeight(1+max(height(node.getLeft()),height(node.getRight())));
    }

    private int height(Node node){
        if(node==null){
            return -1;
        }else{
            return node.getHeight();
        }
    }

    private int getBalance(Node node){
        if(node==null){
            return 0;
        }else{
            return height(node.getRight())-height(node.getLeft());
        }
    }
    
    public void inOrder(Node node){
        if (node!=null){
            inOrder(node.getLeft());
            System.out.println(node.getValue());
            inOrder(node.getRight());
        }
    }
    
    public void preOrder(Node node){
        if (node!=null){
            System.out.println(node.getValue());
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }
    
    public void postOrder(Node node){
        if (node!=null){
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.println(node.getValue());
        }
    }
    
}
