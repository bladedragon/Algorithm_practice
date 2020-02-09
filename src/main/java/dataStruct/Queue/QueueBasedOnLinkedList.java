package dataStruct.Queue;



public class QueueBasedOnLinkedList {
    private Node head;
    private Node tail;

    public void enqueue(String value) {
        Node node = new Node(value);
        if (tail == null) {
            head = node;
            tail = node;

        } else {
            tail.next = node;
            tail = tail.next;
        }

    }

    public String dequeue(){
        if(head == null){
            return null;
        }
        String value = head.value;
        head = head.next;
        if(head.next == null){
            tail = null;
        }
        return value;
    }

    class Node {
        private String value;
        private Node next;

        Node(String value){
            this.value = value;
            this.next=  null;
        }

    }
}
