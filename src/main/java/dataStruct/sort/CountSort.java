package dataStruct.sort;

import java.util.Arrays;

public class CountSort {
    public static void CountingSort(int[] a,int n){
        if(a.length <2 ){
            return ;
        }

        int maxValue = a[0];
        for(int i = 1; i<a.length;i++){
            if(a[i] > maxValue){
                maxValue = a[i];
            }
        }
        //将 数据放入桶中
        int[] bucket = new int[maxValue+1];
        for(int i =0;i<n;i++){
            bucket[a[i]]++;
        }

        for(int i = 1; i<maxValue+1;i++){
            bucket[i] = bucket[i-1]+bucket[i];
        }

        int[] res = new int[n];
        //注意必须从后往前遍历，从而使得排序成为稳定排序
        for(int i =n-1 ;i>=0;i--){
            int index = bucket[a[i]]-1;
            res[index] = a[i];
            bucket[a[i]]--;
        }

        //结果回拷
        for(int i =0 ;i<n;i++){
            a[i] = res[i];
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,4,5,6,7,8,4,6,7,8,8,2,1,5,3};
        System.out.println(Arrays.toString(a));
        CountingSort(a,a.length);
        System.out.println(Arrays.toString(a));

    }
}
