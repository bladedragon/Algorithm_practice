package dataStruct.sort;

import java.util.Arrays;

public class BucketSort {
    public static void bucketSort(int[] a, int bucketSize){
        if(a.length <2){
            return ;
        }
        int minValue = a[0];
        int maxValue = a[1];

        //桶根据数组中数值的大小划分
        for(int i =0 ;i< a.length;i++){
            if(a[i] < minValue){
                minValue = a[i];

            }
            if(a[i] > maxValue){
                maxValue = a[i];
            }
        }

        int bucketCount = (maxValue -minValue) /bucketSize +1;

        int[][] buckets = new int[bucketCount][bucketSize];
        //一个桶中的数组
        int[] indexArr = new int[bucketCount];


        //数组中的值分配到每个桶中
        for(int i =0 ; i < a.length; i++){
            int bucketIndex = (a[i] - minValue)/bucketSize;
            if(indexArr[bucketIndex] == buckets[bucketIndex].length){
                ensureCapacity(buckets,bucketIndex);
            }
            buckets[bucketIndex][indexArr[bucketIndex]++] = a[i];
        }


        //每个桶进行排序
        int k = 0;
        for(int i =0 ;i<buckets.length;i++){
            if(indexArr[i] == 0){
                continue;
            }
            QuickSort.quickSort(buckets[i],indexArr[i]);
            for(int j = 0;j<indexArr[i];j++){
                a[k++] = buckets[i][j];
            }
        }
    }

    private static void ensureCapacity(int[][] buckets, int bucketIndex) {
        int[] tempArr = buckets[bucketIndex];
        int[] newArr = new int[tempArr.length*2];

        for(int i =0;i<newArr.length;i++){
            newArr[i] = tempArr[i];
        }
        buckets[bucketIndex] = newArr;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,35,56,7,2,7,23,76,2,7645,26,23,21,652,623,6};
        System.out.println(Arrays.toString(arr));
        bucketSort(arr,5);
        System.out.println(Arrays.toString(arr));

    }
}
