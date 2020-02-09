package dataStruct.heap;

import java.util.Arrays;

public class HeapSort {
    public static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        // 1、建堆
        buildHeap(arr);
        // 2、排序
        int k = arr.length - 1;
        while (k > 0) {
            // 将堆顶元素（最大）与最后一个元素交换位置
            swap(arr, 0, k);
            // 将剩下元素重新堆化，堆顶元素变成最大元素
            //k是堆化数组长度
            k--;
            //将arr[0]开始进行堆化
            heapify(arr, k, 0);

        }
    }

    private static void swap(int[] arr, int i, int k) {
        int temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }

    private static void heapify(int[] arr, int n, int i) {
        int maxPos;
        while(true){
            maxPos = i;
            if(2*i+1 <= n && arr[2*i+1] > arr[i]){
                maxPos = 2*i+1;
            }
            if(2*i+2 <=n && arr[2*i+2] > arr[i]){
                maxPos = 2*i+2;
            }
            if(maxPos == i){
                break;
            }
            swap(arr,arr[maxPos],arr[i]);
            i = maxPos;
        }
    }

    //大顶堆
    private static void buildHeap(int[] arr) {
        // (arr.length - 1) / 2 为最后一个叶子节点的父节点（存疑）
        // 也就是最后一个非叶子节点，依次堆化直到根节点
        for (int i = arr.length /2- 1 ; i >= 0; i--) {
            heapify(arr, arr.length - 1, i);
        }
        System.out.println("Heap is built:"+Arrays.toString(arr));
        System.out.println(arr[(arr.length - 1) / 2]);
    }


    public static void main(String[] args) {
        int[] a = {2,4,1,3,6,7,8,5};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
