package dataStruct.sort;

import java.util.Arrays;

public class RadixSort {
    /**
     * 基数排序 最好用于位数相同的数字排序
     * 如果位数不同，
     * @param a
     */
    public static void radixSort(int[] a){
        int maxValue = a[0];
        for(int i =0;i<a.length;i++) {
            if (a[i] > maxValue) {
                maxValue = a[i];
            }
        }

            for(int exp = 1;maxValue /exp >0;exp *=10){
                countingSort(a,exp);
            }
    }


    /**
     * 使用计数排序
     * @param a
     * @param exp  最大值的被除数
     */
    private static void countingSort(int[] a, int exp) {
        if(a.length <=1 ){
            return ;
        }

        //一位数 总共0~9共9个桶
        int[] bucket = new int[10];
        for(int i = 0 ;i<a.length; i++){
            //每个值放入桶中
            //(a[i]/exp)%10 如果exp偏大，这样整除后得到0，排序的时候依然不会有问题
            //取余是为了获得最后一位数
            bucket[(a[i] /exp)% 10]++;
        }

        for(int i =1 ;i<bucket.length;i++){
            bucket[i] = bucket[i-1]+ bucket[i];
        }

        //注意必须从后往前遍历
        int[] res = new int[a.length];
        for(int i=a.length-1;i>=0;i--){

            res[bucket[(a[i]/exp)%10]-1] = a[i];
            bucket[(a[i]/exp)%10]--;
        }

        for(int i=0;i<a.length;i++){
            a[i] = res[i];
        }
    }

    public static void main(String[] args) {
        int[] teles  = {99,1000000000,123451,21345,32456,54646,12121,22222,33333,44444,55555,66666};
        System.out.println(Arrays.toString(teles));
        radixSort(teles);
        System.out.println(Arrays.toString(teles));
    }
}
