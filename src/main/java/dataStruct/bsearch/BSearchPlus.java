package dataStruct.bsearch;

public class BSearchPlus {
    /**
     * 查找第一个等于给定值的元素
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bSearch1(int[] a,int n,int value){
        int low =0;
        int high = n-1;
        while(low < high){
            int mid = low + ((high - low) >> 1);

            if(a[mid]>value){
                high = mid-1;
            }else if(a[mid] < value){
                low = mid+1;
            }else{
                //这句话可以和mid>value合并 ==> mid>=value 从而保证当出现重复元素的时候
                if(a[mid] == value && a[mid-1] != value){
                    return value;
                }else{
                    high = mid-1;
                }
            }
        }


        return -1;
    }

    //精简写法，不是很实用
//    public int dataStruct.bsearch(int[] a, int n, int value) {
//        int low = 0;
//        int high = n - 1;
//        while (low <= high) {
//            int mid = low + ((high - low) >> 1);
//            if (a[mid] >= value) {
//                high = mid - 1;
//            } else {
//                low = mid + 1;  //当a[mid] ==value时
//            }
//        }
//
//        if (low < n && a[low]==value) return low;
//        else return -1;
//    }

}
