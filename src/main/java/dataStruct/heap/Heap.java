package dataStruct.heap;

public class Heap {
    private int[] arr;
    private int n;
    private int count;
    Heap(int capacity){
        arr = new int[capacity];
        n = capacity;
        count =0;
    }

    /**
     * 插入
     * 从叶子节点插入，然后自下而上堆化，因此不能使用堆化函数（自上而下）
     * 从0开始存储数据
     * @param data
     */
    public void insert(int data){
        if(count >= n){
            return;
        }
        arr[count] =data;
        int i = count;
        while((i/2-1) >0 && arr[i/2-1] <arr[i]){
            swap(arr,i,i/2-1);
            i = i/2;
        }
        count++;
    }

    /**
     * 删除元素
     * 将叶子节点放到堆顶，然后堆化
     * 否则无法符合完全二叉树定义
     * @param data
     */
    public void remove(int data){
        if(n <= 0){
            return;
        }
        arr[0] = arr[count];
        count--;
        heapify(arr,count,0);

    }

    private void heapify(int[] arr,int n ,int k) {
        int maxPos ;
        while(true){
            maxPos = k;
            if(2*k+1 <=n && arr[2*k+1] > arr[k]){
                maxPos = 2*k+1;
            }
            if(2*k+2 <= n && arr[2*k+2] > arr[k]){
                maxPos = 2*k+2;
            }
            if(maxPos == k){
                break;
            }
            swap(arr,maxPos,k);
            //很重要，因为交换了所以标志位也要同步
            k = maxPos;
        }
    }

    private void swap(int[] arr, int maxPos, int k) {
        int temp = arr[maxPos];
        arr[maxPos] = arr[k];
        arr[k] = temp;
    }
}
