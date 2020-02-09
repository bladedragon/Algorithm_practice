package dataStruct.LinkedList;

import dataStruct.LRU.Node;

/**
 * 单链表
 */
public class SingleList {
    private Node head;


    /**
     * 尾部插入
     * @param value
     */
    private void insertToTail(int value){

        Node node =  new Node(value,null);
        if(null == head){
              head = node;
        }else {
            Node q = head;
            while(q.next!= null ){
                  q = q.next;
            }

            //关键步骤，不能调换顺序
            node.next = q.next;
            q.next = node;
        }

    }

    /**
     * 指定位置插入值
     * @param value
     */
    private void insert(int value,int index){
        Node node = head;
        Node preNode  = null;
        int pos = 0;
        while(node != null && index != pos){
           preNode = node;
            node = node.next;
        }

        Node newNode= new Node(value,node.next);
        if(node == null){
            return ;
        }
        if(preNode ==null){
            newNode.next = head;
            head = newNode;
        }else{
            newNode.next = preNode.next;
            preNode.next = newNode;
        }

    }

    /**
     * 删除特定值
     * @param data
     */
    private void deleteByNode(int data){

        if(head == null){
            return ;
        }
        Node node = head;
        //应该是保存插入节点的上一个节点
        Node q = null;
        while(node!= null && node.data != data){
            //保存上一个节点
            q = node;
            //使得循环继续下去
            node = node.next;
        }

        //说明没有找到节点
        if(node == null){
            return;
        }

        //删除head头节点的时候，此时q依然为null(没有进入循环)，必须超常处理，实在是太精髓了
        if(q == null){
            head = head.next;
        }else{
            q.next = q.next.next;
        }
    }

    /**
     * 通过索引查询
     * @param index
     * @return
     */
    private Node findByIndex(int index){
        if(index<0){
            return null;
        }

        Node node= head;
        int pos = 0;

        while(node != null && pos != index){

            node = node.next;
            ++pos;

        }

        return node;

    }

    /**
     * 通过值查询
     * @return
     */
    private Node findByValue(int data){
        Node node = head;

        while(node != null){
            node = node.next;
        }

        return node;
    }


    public static void main(String[] args) {


    }

}
