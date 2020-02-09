package algorithm.divideAndConquer;

public class Demo {

    private int num =0;

    /**
     * 求解逆序对数（2，1）（4，3）类似这种
     * @param a
     * @param n
     * @return
     */
    public int counting(int[] a, int n){
        num = 0;
        mergeCounting(a,0,n-1);
        return num;
    }

    private void mergeCounting(int[] a, int p, int r) {
        if(a.length <=1){
            return;
        }
        int q = (r + p)/2;
        mergeCounting(a,0,q);
        mergeCounting(a,q+1,r);
        merge(a,p,q,r);
    }

    private void merge(int[] a, int p, int q, int r) {

        //这里的q+1是怎么回事
        int i =p, j = q+1, k = 0;
        int[] tmp = new int[r-p+1];
        while (i<=q && j <= r){
            if(a[i] <= a[j]){

                tmp[k++] = a[i++];
            }else{
                //统计比a[j]大的元素。这些都是和a[j]形成逆序的的数
                //统计p-q之间，比a[j]大的元素个数
                num += (q-i+1);
                tmp[k++] = a[j++];
            }
        }
        while(i<=q){
            tmp[k++] = a[i++];
        }
        while(j <= r){
            tmp[k++] = a[j++];
        }
        for(i = 0; i<= r-p;i++){
            a[p+i] = tmp[i];
        }
    }
    //  private void merge(int[] a, int p, int q, int r) {
    //    int i = p, j = q + 1, k = 0;
    //    int[] tmp = new int[r - p + 1];
    //    while (i <= q && j <= r) {
    //      if (a[i] <= a[j]) {
    //      //主要在这里，当a[i] <= a[j]的时候求比a[i]大的
    //        num += (j - q - 1);
    //        tmp[k++] = a[i++];
    //      } else {
    //        tmp[k++] = a[j++];
    //      }
    //    }
    //    while (i <= q) {、
    //      //主要这里也有,此时a[i]的逆序数是整个数组长度
    //      num += (j - q - 1);
    //      tmp[k++] = a[i++];
    //    }
    //    while (j <= r) {
    //      tmp[k++] = a[j++];
    //    }
    //    for (i = 0; i <= r - p; ++i) {
    //      a[p + i] = tmp[i];
    //    }
    //  }

    public static void main(String[] args) {

    }
}
