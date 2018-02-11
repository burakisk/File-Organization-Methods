package com.bigame.kdtree;

import com.bigame.DataStruct.Node;

/**
 * @author burakisik
 */
public class KD {

    KDNode head;
    boolean flag = true;
    
    public KD(){
        head = null;
    }

    public boolean isEmpty(){
        return head == null;
    }

    public void insert(String name,int height,int weight){
        flag = true;

        KDNode newNode = new KDNode(name,height,weight);
        if(head==null){
            head = newNode;
            return;
        }

        KDNode current = head;
        KDNode parent = null;

        while (true){

            parent = current;

            if(flag) {
                flag = !flag;
                if ( height < current.getHeight() ) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
            else {
                flag = !flag;
                if (weight < current.getWeight()) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void inorder()
    {
        inorder(head);
    }
    public void preorder()
    {
        preorder(head);
    }

    public void postorder()
    {
        postorder(head);
    }

    public void inorder(KDNode head){

        if(head!=null){
            inorder(head.left);
            System.out.print(head.getName()+" "+head.getHeight()+" "+head.getWeight());
            inorder(head.right);
        }
    }

    public void preorder(KDNode head){

        if(head!=null){
            System.out.print(head.getName()+" "+head.getHeight()+" "+head.getWeight());
            inorder(head.left);
            inorder(head.right);
        }
    }

    public void postorder(KDNode head){
        if(head!=null){
            inorder(head.left);
            inorder(head.right);
            System.out.print(head.getName()+" "+head.getHeight()+" "+head.getWeight());
        }
    }

    public void display() {
        display("\t\t", head, false);
    }

    public void display(String prefix, KDNode n, boolean isLeft) {
        if (n != null) {
            System.out.println (prefix + (isLeft ? "|-- " : "\\-- ") + n.getName()+"("+n.getHeight()+","+n.getWeight()+")");
            display(prefix + (isLeft ? "|   " : "    "), n.left, true);
            display(prefix + (isLeft ? "|   " : "    "), n.right, false);
        }
    }
}

