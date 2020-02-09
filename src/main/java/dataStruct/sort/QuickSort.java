package dataStruct.sort;


import java.util.Arrays;

public class QuickSort {
    /**
     * 快速排序基本调用
     * @param a
     * @param n
     */
    public static void quickSort(int[] a,int n){
        quickSortInternally(a,0,n-1);
    }

    private static void quickSortInternally(int[] a, int p, int r) {
        if(p>= r){
            return;
        }
        //递归
        int q = patition2(a,p,r);
        quickSortInternally(a,p,q-1);
        quickSortInternally(a,q+1,r);
    }

    /**
     * 在保证空间复杂度的情况下，找到分治点
     * （确保分治点前的数比分治点小，分治点后的数比分治点大）
     * @param a
     * @param p
     * @param r
     * @return
     */
    private static int patition(int[] a, int p ,int r){
        //先选取最右边的数作为分治点
        int pivot = a[r];
        int i = p;
        for(int j = p; j< r;j++){
            //小于分治点，满足条件
            if(a[j] < pivot){
                //如果相等说明没有出现大于分治点的情况，此时i和j都++
                if(a[j] == a[i]){
                    i++;
                }else {
                    //出现i！=j说明出现比分治点大的数，此时将这个数往后移动
                    int temp = a[i];
                    a[i++] = a[j];
                    a[j] = temp;
                }
            }
        }
        //分治点移动到前面
        int temp = a[i];
        a[i] = a[r];
        a[r] = temp;
        return i;

    }



    /**
     * 三数取中法求分治点
     * @param a
     * @param left
     * @param right
     * @return
     */
    private static int patition2(int[] a,int left, int right){
        //这句话应该存在可优化的空间
//        int middle = (left+right)/2;
        //取中点用这句，可以尽可能避免栈溢出
        int middle = left+(right-left)/2;

        int pivot = a[middle];
        int val = a[right];
        //最右边的数置为a[middle]
        a[right] = pivot;
        //中间值和最后一个数交换
        a[middle] = val;
        int i = left;
        for(int j = left;  j < right; j++){
            if(a[j] <pivot){
                if(i==j){
                    ++i;
                }else{
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }
        int tmp = a[i];
        a[i] = a[right];
        a[right] = tmp;
        return i;
    }

    /**
     * 三向切分快速排序法
     * 存在大量可重复 数据的时候
     * @param a
     * @param left
     * @param right
     */
    private static void quickSort3(int[] a, int left,int right){
        if(left > right){
            return;
        }
        int l = left;
        //k是分治点
        int k = left+1;
        int r = right;
        int pivot = a[l];
        while(k <= r){
            //小于分治点，数往后移动
            if(a[k] <= pivot){
                int temp = a[l];
                a[l] =  a[k];
                a[k] =  temp;
                k++;
                l++;
                //等于分支点，k往后移动
            }else if(a[k] == pivot){
                k++;
            }else{
                //大于分治点，将这个大的数和右边a[r]比较
                //a[r]大，是正常情况
                if(a[r] > pivot){
                    r--;
                    //相等，说明分治点
                }else if(a[r] == pivot){
                    int temp = a[k];
                    a[k] = a[r];
                    a[r] = temp;
                    k++;
                    r--;
                }else{
                    //如果r小于pivot,此时r小于pivot，k大于pivot，i==pivot，需要将r和l调换位置就行
                    int temp = a[l];
                    a[l] = a[r];
                    a[r] = a[k];
                    a[k] = temp;
                    l++;
                    k++;
                    r--;
                }
            }
        }

        //分治点排序完成后l之前的小于分治点，r之后的大于分治点
        quickSort3(a,left,l-1);
        quickSort3(a,r+1,right);

    }

    /**
     * 双轴快排
     * @param A
     * @param L
     * @param R
     */
    public static void QuickSortDualPivot(int[] A, int L, int R){
        if(L >= R){
            return;
        }

        if(A[L] > A[R]){
            Swap(A, L, R); //保证pivot1 <= pivot2
        }

        int pivot1 = A[L];
        int pivot2 = A[R];

        //如果这样初始化 i = L+1, k = L+1, j = R-1,也可以
        //但代码中边界条件, i,j先增减，循环截止条件，递归区间的边界都要发生相应的改变
        int i = L;
        int k = L+1;
        int j = R;


        while(k < j){
            if(A[k] < pivot1){
                i++;//i先增加，首次运行pivot1就不会发生改变
                Swap(A, i, k);
                k++;
            }else
            if(A[k] >= pivot1 && A[k] <= pivot2){
                k++;
            }else{
                while(A[--j] > pivot2){//j先增减，首次运行pivot2就不会发生改变
                    if(j <= k){//当k和j相遇
                        break ;
                    }
                }
                if(A[j] >= pivot1 && A[j] <= pivot2){
                    Swap(A, k, j);
                    k++;
                }else{
                    i++;
                    Swap(A, j, k);
                    Swap(A, i, k);
                    k++;
                }
            }
        }
        Swap(A, L, i);//将pivot1交换到适当位置
        Swap(A, R, j);//将pivot2交换到适当位置

        //一次双轴切分至少确定两个元素的位置，这两个元素将整个数组区间分成三份
        QuickSortDualPivot(A, L, i-1);
        QuickSortDualPivot(A, i+1, j-1);
        QuickSortDualPivot(A, j+1, R);
    }



    public static void Swap(int[] A, int i, int j){
        int tmp;
        tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static int  sort(int[] a, int l, int r ,int k){
        if(l >= r){
            return 0;
        }
        int p = patition2(a,l,r);
        if((p+1) == k ){
            return a[p];
        }else if((p+1) < k ){
            return sort(a,p+1,r,k);
        }else {
            return sort(a,l,p-1,k);
        }
    }


    public static void main(String[] args) {
//        int[] a = {1,23,34,546,2,12,1};
//
//        System.out.println(Arrays.toString(a));
////        QuickSortDualPivot(a,0,a.length-1);
//        quickSort(a,a.length);
//        System.out.println(Arrays.toString(a));

        int[] arr = {2, 1, 5, 6, 8, 4, 12, 11, 13, 15, 7, 9, 0, -1};
        System.out.println(Arrays.toString(arr));
        int res = sort(arr,0,arr.length-1,5);
        System.out.println(Arrays.toString(arr));
        System.out.println("res = "+res);
    }
}
