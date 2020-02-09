package dataStruct.string_match;

public class KMP {

    private static  int kmp(char[] a,int n,char[] b,int m){

        int[] next = getNext(b,m);
        int j = 0;
        for(int i =0;i< n;i++){
            while(j>0 && a[i] != b[j]){
                j = next[j-1]+1;
            }
            if(a[i] == b[j]){
                j++;
            }
            if(j == m){
                return i-m+1;
            }
        }
        return -1;
    }

    private static int[] getNext(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k  = -1;
        for(int i =0; i<m;i++){
            while(k >=0 && b[k+1] != b[i]){
                //求次长匹配长度，递归
                k = next[k];
            }
            if(b[k+1] == b[i]){
                ++k;
            }
            next[i] = k;
        }
        return next;
    }

}
