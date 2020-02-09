package dataStruct.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public void mergerSort(int[] a,int n){
        mergeSortInternally(a,0,n-1);
    }

    /**
     * 递归调用函数
     * @param a  数组
     * @param p   下标是p
     * @param r    下标是r
     */
    private static void mergeSortInternally(int[] a, int p, int r) {
        // 递归终止条件
        if (p >= r){
            return;
        }

        // 取p到r之间的中间位置q,防止（p+r）的和超过int类型最大值
        int q = p + (r - p)/2;
        // 分治递归
        mergeSortInternally(a, p, q);
        mergeSortInternally(a, q+1, r);

        // 将A[p...q]和A[q+1...r]合并为A[p...r](合并的时候要注意排序)
        mergeBySecurity(a, p, q, r);
    }

    /**
     * 数组归并函数
     * @param a 数组
     * @param p
     * @param q
     * @param r
     */
    private static void merge(int[] a, int p, int q, int r) {
        int i = p;
        int j = q+1;
        // 初始化变量i, j, k
        int k = 0;
        int[] tmp = new int[r-p+1]; // 申请一个大小跟a[p...r]一样的临时数组
        while (i<=q && j<=r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++]; // i++等于i:=i+1
            } else {
                tmp[k++] = a[j++];
            }
        }
        // 判断哪个子数组中有剩余的数据
        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }

        // 将剩余的数据拷贝到临时数组tmp
        while (start <= end) {
            tmp[k++] = a[start++];
        }

        // 将tmp中的数组拷贝回a[p...r]
        for (i = 0; i <= r-p; ++i) {
            a[p+i] = tmp[i];
        }
    }


    /**
     * 归并函数（添加哨兵机制）
     * @param a  原数组
     * @param p
     * @param q
     * @param r
     */
    private static void mergeBySecurity(int[] a, int p,int q,int r){
        //(q+1) - p + 1
        int[] leftArr = new int[q-p+2];

        int[] rightArr = new int[r-q+1];

        //将数组分成两个数组然后进行比较
        for(int i =0 ;i < q+1-p; i++){
            leftArr[i] = a[p+i];
        }
        //数组的最后一个数添加哨兵
        leftArr[q-p+1] = Integer.MAX_VALUE;

        for(int i = 0;i < r-q; i++){
            rightArr[i] = a[q+1+i];
        }
        rightArr[r-q] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        int k = p;
        while(k<= r){
            //由于哨兵机制的存在，遍历到最后一个数的时候一定相等
            if(leftArr[i] <= rightArr[j]){
                a[k++] = leftArr[i++];
            }else{
                a[k++] = rightArr[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] ints = {2,3,4,642,4,5,76,85,3};
        System.out.println(Arrays.toString(ints));
       MergeSort mergeSort = new MergeSort();
       mergeSort.mergerSort(ints,ints.length);
        System.out.println(Arrays.toString(ints));
    }
}
