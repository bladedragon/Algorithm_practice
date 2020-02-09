package dataStruct.string_match;

public class BM {

    private static final int SIZE = 256;

    /**
     * 建造散列表
     * @param b
     * @param m
     * @param bc  散列表
     */
    private void generateBC(char[] b,int m,int[] bc){
        for(int i =0;i<SIZE;i++){
            bc[i] = -1;
        }
        for(int i =0;i<m;i++){
            int ascii = (int)bc[i];
            bc[ascii] = i;
        }
    }

    /**
     * BM算法
     * @param a
     * @param n
     * @param b
     * @param m 模式串的长度
     * @return
     */
    public int bm(char[] a,int n,char[] b,int m){
            int[] bc = new int[SIZE];
            //创建坏字符散列表
            generateBC(b,m,bc);
            int[] suffix = new int[m];
            boolean[] prefix = new boolean[m];
            //创建公共后缀子串
            generateGS(b,m,suffix,prefix);
            int i =0;
            while(i < n-m){
                int j; //j是主串和模式串匹配的第一个字符
                //向后匹配
                for(j = m-1;j >= 0;j--){
                    if(a[i+j] != b[j] ){
                        break;
                    }
                }

                //匹配成功
                if(j < 0){
                    return i;
                }

                int x = j -bc[(int) a[i+j]];
                int y = 0;
                if(j < m-1){
                    //如果有好后缀的话
                    y = moveByGS(j,m,suffix,prefix);
                }

                //匹配坏字符和好后缀的最佳滑动方案
                i = i+Math.max(x,y);
            }

            return -1;
    }

    /**
     *
     * @param j  坏字符对应的模式串的字符下边
     * @param m 模式串的长度
     * @param suffix
     * @param prefix
     * @return
     */
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        int k = m - 1 - j;
        if(suffix[k] != -1 ){
            return j - suffix[k] + 1;
        }

        for(int r = j+2;r< m-1;r++){
            //好后缀的后缀子串有可匹配的前缀子串的话
            if(prefix[m-r] == true){
                return r;
            }
        }
        return m;
    }

    /**
     * 创建公共后缀字串
     * @param b
     * @param m
     * @param suffix
     * @param prefix
     */
    private void generateGS(char[] b,int m, int[] suffix, boolean[] prefix){
        for( int i= 0; i<m;i++){
            suffix[i] = -1;
            prefix[i] = false;
        }

        for(int i =0;i<m-1;i++){
            int j = i;
            int k = 0;
            while(j>=0 && b[j] == b[m-1-k]){
                --j;
                ++k;
                suffix[k] = j+1;
            }
            if(j == -1){
                prefix[k] = true;
            }
        }
    }



}
