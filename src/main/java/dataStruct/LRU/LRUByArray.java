package dataStruct.LRU;

import java.util.stream.IntStream;

public class LRUByArray {
    private  int  arrays[];
    private int size;
    /**
     * 计数器 ，全局维护，应该是类似指针的作用
     */
    private int count;

    private LRUByArray(int capacity){
        arrays = new int[capacity];
        this.size = capacity;
        this.count = 0;
    }

    private void insert(int data){
        int index = findValue(data);
        if(index != -1){
            delete(index);
        }else{
            if(count >= size){
                deleteToTail();
            }
        }

        insertToHead(data);
    }

    /**
     * 插入首部，就需要将数组中的其他元素往后面移动一位
     * @param data
     */
    private void insertToHead(int data) {
        //count>1意味着数组中存在数
        if(count >1){
            //因为有数组容量限制，所以为了防止下标越界，这里可能存在剔除尾部元素的操作
            for(int i =count-1; i > -1; --i){

                arrays[i+1] = arrays[i];
            }
        }
        arrays[0] = data;
        ++count;
    }

    private void deleteToTail() {
        count--;
    }

    private void delete(int index) {
        if(index <0 || index > count){
            System.out.println("error");
            return ;
        }

        for(int i = index+1; i<count; i++){
            arrays[i-1] = arrays[i];
        }

        count --;
    }

    private int findValue(int data) {

        for(int i =0; i < count; i++){
            if(arrays[i] == data){
                return i;
            }
        }
        return -1;
    }

    private void printAll(){
        for(int i=0; i < count;i++){
            System.out.print(arrays[i]+" ->");
        }
        System.out.println();

    }
    public static void main(String[] args) {
        int capacity = 10;
        LRUByArray lruByArray = new LRUByArray(capacity);
        IntStream.range(0, 10).forEach(lruByArray::insert);
        lruByArray.printAll();
        lruByArray.insert(10);
        lruByArray.printAll();
        lruByArray.insert(8);
        lruByArray.printAll();
    }
}
