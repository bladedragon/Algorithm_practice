package dataStruct.LRU;

public class LRUByLinkedList{

    private Node head;
    private int size;
    private int count;

    public LRUByLinkedList(int capacity){
        head = null;
        size = capacity;
        count = 0;
    }


    private void insert(int data){
        Node preNode = findNode(data);
        if(null != preNode){
            delete(preNode);
        }else{
            if(count >= size){
                deleteToTail();
            }
        }
        insertToHead(data);
    }

    private void insertToHead(int data) {
        Node node = new Node(data,null);
        if(null ==head){
            head = node;
        }else{
            node.next = head;
        }
        head = node;
        ++count;
    }

    private void deleteToTail() {
        Node node = head;
        Node curNode = null;
        while(null != node.next){
            curNode = node;
            node = node.next;
        }

        if(null != curNode){
            curNode.next = null;
            System.out.println("删除尾部元素");
        }
        --count;
    }

    private void delete(Node preNode) {

        preNode.next = preNode.next.next;
        --count;
    }

    private Node findNode(int data) {
        Node node = head;
        while(null != node && null != node.next){
            Node nextNode = node.next;
            if(data == nextNode.data){
                return node;
            }
            node = nextNode;
        }
        return  null;
    }

    private void printAll(){
        Node node  = head;
        while(null != node){
            System.out.print(node.data+" -> ");
            node = node.next;
        }
        System.out.println();;
    }


    public static void main(String[] args) {
        int capacity = 10;
        LRUByLinkedList lruByLinkedList = new LRUByLinkedList(capacity);
        for(int i =0;i<10;i++){
            lruByLinkedList.insert(i);
        }
        lruByLinkedList.printAll();
        lruByLinkedList.insert(10);
        lruByLinkedList.printAll();
        lruByLinkedList.insert(8);
        lruByLinkedList.printAll();
    }

}