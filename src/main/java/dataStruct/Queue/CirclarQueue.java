package dataStruct.Queue;

public class CirclarQueue {

    private String[] items;
    private int head;
    private int tail;
    private int n;

    CirclarQueue(int capacity){
        this.items = new String[capacity];
        this.head = 0;
        this.tail = 0;
        this.n = capacity;
    }

    //队列满的时候 (tail+1) % n == head
    public boolean enqueue(String item){
        if((tail+1 )% n  == head){
            return false;
        }

        items[tail] = item;
        tail = (tail+1) % n;
        return true;
    }

    //队列为空的时候
    public String dequeue(){
        if(head == tail){
            return null;
        }

        String res = items[head];
        head  = (head+1) % n;
        return res;
    }

    public void printAll(){
        if(n == 0){
            return ;
        }
        for(int i = head ;i %n != tail ;i = (i+1)%n){
            System.out.print(items[i]);
        }
        System.out.println();
    }
}
