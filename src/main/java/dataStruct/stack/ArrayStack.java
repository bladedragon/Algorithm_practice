package dataStruct.stack;

public class ArrayStack {
    private String[] items;
    private int count;
    private int n;

    ArrayStack(int capacity){
        this.items = new String[capacity];
        this.count = 0;
        this.n = capacity;
    }

    private boolean put(String item){
        if(count >= n){
            return false;
        }
        items[count] = item;
        count ++;
        return true;
    }

    private String pop(){
        if(count<=0){
            return null;
        }
        String popItem = items[count-1];
        count--;
        return popItem;
    }





}
