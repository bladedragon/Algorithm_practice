package dataStruct.sort;

import java.util.Arrays;

public class QuickSortByOthers {

    public static void quickSort(int[] a){
        int len = a.length;
        if(a == null || len ==0 || len ==1){
            return;
        }
        quickSortDetail(a,0,len-1);
    }

    private static void quickSortDetail(int[] a, int l, int r) {
        if(l >r){
            return ;
        }

        int base = a[l];
        int i = l, j  = r;
        while(i != j){
            //顺序很重要，先从右往左找
            while(a[j] >= base && i < j){
                j--;
            }

            while(a[i] <= base && i < j){
                i++;
            }

            //循环结束代表找到位置或者i>=j
            if(i < j){
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        //分治点 归位
        a[l] = a[i];
        a[i] = base;

        quickSortDetail(a,l,i-1);
        quickSortDetail(a,i+1,r);

    }

    public static void main(String[] args) {
        int[] a = {13,5,546,23,7,2,723,4};
        System.out.println(Arrays.toString(a));
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }

}
