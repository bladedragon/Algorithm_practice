package dataStruct.bsearch;

import java.util.Arrays;

public class Bsearch {

    public static int bsearch(int[] a,int l,int r ,int value){

        if(l>r){
            return -1;
        }
        //注意>>的优先级，单符号优先级大于多符号
        int mid = l+((r-l)>>1);
        if(a[mid] ==value){
            return mid;
        }else if(a[mid]<value){
            return bsearch(a,mid+1,r,value);
        }else{
            return bsearch(a,l,mid-1,value);
        }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,5,6,7,8,9,12};
        System.out.println(Arrays.toString(a));
        int res = bsearch(a,0,a.length-1,5);
        System.out.println(res);
    }

}
