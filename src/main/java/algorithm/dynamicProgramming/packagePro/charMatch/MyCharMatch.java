package algorithm.dynamicProgramming.packagePro.charMatch;

/**
 * 计算莱温斯坦距离
 *
 */
public class MyCharMatch {

    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();

    private int n = 6;
    private int m = 6;
    private int minDict = Integer.MAX_VALUE;

    /**
     * 回溯算法
     * @param i
     * @param j
     * @param editst
     */
    public void lwstBT(int i,int j,int editst){
        if(i == n || j == m){
            if(i<n){
                editst += n-i;
            }else if(j < m){
                editst += m-j;
            }
            if(editst < minDict){
                minDict = editst;
            }
            return;
        }

        if(a[i] == b[j]){
            lwstBT(i+1,j+1,editst);
        }else{
            lwstBT(i+1,j,editst+1);
            lwstBT(i,j+1,editst+1);
            lwstBT(i+1,j+1,editst+1);
        }
    }

    public int lwstDP(int[] a,int n,int[] b,int m){
        int[][] state = new int[n][m];
        for(int i =0;i< n; i++){
            if(a[i] == b[0]){
                state[i][0] = i;
            }else{
                if(i ==0){
                    state[i][0] = 1;
                }
                state[i][0]  = state[i-1][0] + 1;
            }
        }

        for(int j =0;j<m;j++){
            if(a[0] == b[j]){
                state[0][j] = j;
            }else{
                if(j == 0){
                    state[0][j] = 1;
                }
                state[0][j] = state[0][j-1] +1;
            }
        }

        for(int i = 1;i < n;i++){
            for(int j = 1;j < m;j++){
                if(a[i] == b[j]){
                    state[i][j] = min(state[i][j-1]+1,state[i-1][j]+1,state[i-1][j-1]);
                }else{
                    state[i][j] = min(state[i][j-1]+1,state[i-1][j]+1,state[i-1][j-1]+1);
                }
            }
        }

        return state[n-1][m-1];

    }

    public int min(int a,int b,int c){

        int min = Integer.MAX_VALUE;
        if(a < min){
            min = a;
        }
        if(b < min){
            min = b;
        }
        if(c < min){
            min = c;
        }
        return min;
    }

    public static void main(String[] args) {

    }

}
