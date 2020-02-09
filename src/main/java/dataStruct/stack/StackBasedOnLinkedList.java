package dataStruct.stack;

import dataStruct.LRU.Node;

public class StackBasedOnLinkedList {
    private Node top = null;

    public void push(int value){
        Node newNode = new Node(value,null);
        if(top == null){
            top = newNode;

        }else{
            newNode.next = top;
            top = newNode;
        }
        return ;
    }

    public int pop(){
        if(top == null){

            return -1;
        }
        int value = top.data;
        top = top.next;
        return value;
    }

}
