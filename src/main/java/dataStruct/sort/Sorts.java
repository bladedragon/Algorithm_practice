package dataStruct.sort;

import java.util.Arrays;

public class Sorts {

    /**
     * 冒泡排序
     * @param a
     * @param n
     */
    public void bubbleSort(int[] a, int n){
        if(n <= 1){
            return;
        }
        int b = a[0];
        for(int i =0;i < a.length;i++){

            boolean flag = false;
            for(int j = 0;j<n-i-1;j++){
                if(a[j] <a[j+1]){
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    flag = true;
                }
            }

            if(!flag){
                break;
            }
        }
    }

    public void insertionSort(int[] a,int n){
        if(n<=1){
            return ;
        }
        for(int i =0; i<n;i++){
            int value = a[i];
            int j = i-1;

            for(;j > 0;j--){
                if(a[j] > value){
                    a[j+1] = a[j];
                }else{
                    break;
                }
            }

            a[j+1] = value;

        }
    }

    public void bubbleSortPlus(int[] a,int n){
        if(n <= 1){
            return ;
        }

        int sortBorder = n-1;
        int lastExchange = 0;
        for(int i =0;i <n ;i++ ){
            boolean flag = false;
            for(int j = 0;j < sortBorder;j++){
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    // 此次冒泡有数据交换
                    flag = true;
                    // 更新最后一次交换的位置
                    lastExchange = j;
                }
            }

            sortBorder = lastExchange;
            if(!flag){
                break;
            }
        }
    }

    /**
     * 选择排序
     * @param a
     * @param n
     */
    public void selectionSort(int[] a, int n){
        if(n<= 1){
            return ;
        }

        for(int i=0; i<n;i++){
            int minIndex = i;

            for(int j =i+1;j<n;j++){
                if(a[j] < a[minIndex]){
                    minIndex = j;
                }
            }

            int temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] ints = {2,3,4,642,4,5,76,85,3};
        System.out.println(Arrays.toString(ints));
        Sorts sorts = new Sorts();
        sorts.bubbleSort(ints,ints.length);
        System.out.println(Arrays.toString(ints));
    }

}
