package dataStruct.LinkedList;

import dataStruct.LRU.Node;

public class CircleLinkedList {
    private Node head;
    private Node tail;
    private int count;

    CircleLinkedList(){
        head = null;
        tail = null;
        count = 0;
    }

    private void insertHead(int data){

        Node node = new Node(data,null);
        if(count== 0){
            head = node;
            tail = node;
        }else{
            node.next = head;
            tail.next = node;
            head = node;
            count++;
        }
    }

    private void insertIndex(int index,int data){
        Node node = new Node(data,null);

        int num = 0;
        while(num <index){

        }
    }
}
