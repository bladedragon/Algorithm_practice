package algorithm.dynamicProgramming.packagePro.charMatch;

public class CharMatch {
    private static char[] a = "mitcmu".toCharArray();
    private static char[] b= "mtacnuu".toCharArray();

    private static int n =6;
    private static int m = 7;
    private int minDist = Integer.MAX_VALUE;

    public void lwstBT(int i, int j, int editst){
        if(i == n || j == m){

            //可以用Math.abs(j-i)替代
            if(i < n){
                editst = n-i;
            }
            if(j < m){
                editst = m-j;
            }
            if(editst < minDist){
                minDist = editst;
            }
            return;
        }
        //回溯
        if(a[i] == b[i]){
            lwstBT(i+1 ,j+1,editst);
        }else{
            lwstBT(i+1,j,editst+1);
            //删除b[j] 或 a[i] 前添加
            lwstBT(i,j+1,editst+1);
            //替换一个字符
            lwstBT(i+1,j+1,editst+1);
        }
    }
    public int lwstDP(char[] a,int n, char[] b, int m) {
        int[][] state = new int[n][m];
        //第一层初始化
        for (int i = 0; i < n; i++) {
            if (a[i] == b[0]) {
                state[i][0] = i;
            } else if (i > 0) {
                state[i][0] = state[i - 1][0] + 1;
            } else {
                state[i][0] = 1;
            }
        }
        for (int j = 0; j < m; j++) {
            if (a[0] == b[j]) {
                state[0][j] = j;
            } else if (j > 0) {
                state[0][j] = state[0][j - 1] + 1;
            } else {
                state[0][j] = 1;
            }
        }

        for (int i = 1; i < n; ++i) { // 按行填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]){
                    state[i][j] = min(
                            state[i - 1][j] + 1, state[i][j - 1] + 1, state[i - 1][j - 1]);
                }
                else {
                    state[i][j] = min(state[i - 1][j] + 1, state[i][j - 1] + 1, state[i - 1][j - 1] + 1);
                }
            }
        }
        return state[n - 1][m - 1];
    }


    /**
     * 状态转移表---解决两个字符串的编辑距离
     * 倒序比较，o:源字符串长度-1 d:目的字符串长的-1
     * 调用方式：System.out.println(lwstNew(origin.length-1, dest.length-1));
     * 修改逻辑简化成只修改原串
     * @param o
     * @param d
     * @param a
     * @param b
     * @return
     */
    public int lwstNew(int o, int d,char[] a,char[] b){
        // 如果源或目的已经到边界，此时只能插入。
        if( o < 0 || d < 0){
            return Math.abs(o-d);
        }
        // 字符相等此时直接移动指针，编辑次数无变化
        if(a[d] == b[o]){
            return lwstNew(o-1, d-1,a,b);
        }else{
            // 执行源字符的替换或插入，编辑次数加一，此时一定实现origin[d] == dest[o]，所以均移动
            int replaceOrInsert = lwstNew(o-1,d-1,a,b) + 1;
            // 执行源字符的删除，编辑次数加一，但是不确保origin[d] == dest[o-1]，所以需要继续递归
            int delete = lwstNew(o-1, d,a,b) + 1;
            return Math.min(replaceOrInsert, delete);
        }
    }

    private int min(int x, int y, int z) {
        int minv = Integer.MAX_VALUE;
        if (x < minv){
            minv = x;
        }
        if (y < minv){
            minv = y;
        }
        if (z < minv){
            minv = z;
        }
        return minv;
    }

    public static void main(String[] args) {
        CharMatch charMatch = new CharMatch();
        System.out.println(charMatch.lwstDP(a,n,b,m));
        System.out.println(charMatch.lwstNew(m-1,n-1,a,b));
    }
}
