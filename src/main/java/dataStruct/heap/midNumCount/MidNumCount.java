package dataStruct.heap.midNumCount;

import java.util.PriorityQueue;

public class MidNumCount {
    /** 求中位数的信息 */
    public static final MidNumCount INSTANCE = new MidNumCount();

    /** 大顶堆，用来存储前半部分的数据，如果完整为100，那此存储的为0-50
     *   o2>o1时return 1 可见是大顶堆
     *   */

    private PriorityQueue<Integer> firstBigHeap =
            new PriorityQueue<>(
                    51,
                    (o1, o2) -> {
                        if (o1 < o2) {
                            return 1;
                        } else if (o1 > o2) {
                            return -1;
                        }
                        return 0;
                    });

    /** 小顶堆,用来存储后半部分的数据，如果完整为100,那此存储的为51-100 */
    private PriorityQueue<Integer> afterLittleHeap = new PriorityQueue<>(51);

    private int count;

    public void putNum(int num){
        count++;
        if(firstBigHeap.isEmpty() && afterLittleHeap.isEmpty()){
            firstBigHeap.offer(num);
            return;
        }

        if(firstBigHeap.peek() < num){
            afterLittleHeap.offer(num);
        }else{
            firstBigHeap.offer(num);
        }

        int countValue = count/2;

        if(firstBigHeap.size()> countValue){
            move(firstBigHeap,afterLittleHeap,firstBigHeap.size()-countValue);
            return;
        }
    }

    public int getMidValue(){
        return firstBigHeap.peek();
    }

    private void move(PriorityQueue<Integer> firstBigHeap, PriorityQueue<Integer> afterLittleHeap, int runNum) {
        for(int i=0 ;i<runNum;i++){
            afterLittleHeap.offer(firstBigHeap.poll());
        }
    }

}
