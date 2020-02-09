package algorithm.dynamicProgramming.packagePro.packagePro;

/**
 * 背包问题升级版
 */
public class Demo2 {

    private int maxV = Integer.MIN_VALUE;
    private int[] items = {2,2,4,6,3};
    private int[] value = {3,4,8,9,6};
    private int n = 5;
    private int w = 9;

    /**
     * 回溯法求背包问题
     * @param i
     * @param cw
     * @param cv
     *
     */
    public void f(int i, int cw, int cv){
        //如果装满并物品考察完,更新追踪值
        if(cw == w || i == n){
            if(cw > maxV){
                maxV = cw;
            }
            return;
        }

        f(i+1,cw,cv);
        if(cw +items[i] <= w){
            f(i+1,cw+items[i],cv+value[i]);
        }
    }


    /**
     * 背包问题升级版，添加给定价值
     * @param weight
     * @param value
     * @param w
     * @param n
     * @return
     */
    public int knapsack3(int[] weight, int[] value,int w,int n){
            int[][] state = new int[n][w+1];
            for(int i =0; i < n;i++){
                for(int j =0; j < w; j++){
                    state[i][j] = -1;
                }
            }
            state[0][0] = 0;
            if(weight[0] <= w){
                //装填初值
                state[0][weight[0]] = value[0];
            }

            for(int i =1;i<n;i++){
                for(int j =0; j<w;j++){
                    if(state[i-1][j] >=0){
                        state[i][j] = state[i-1][j];
                    }
                }
                for(int j =0; j <= w-weight[i] ;j++){
                    if(state[i-1][j] >= 0){
                        int v = state[i-1][j] + value[i];
                        //为什么要多一个判断？因为要保持总价值最大
                        if(v > state[i][weight[i]+j]){
                            state[i][j+weight[i]] = v;
                        }
                    }
                }
            }

            int maxValue = -1;
            for(int i =0;i<w;i++){
                if(state[n-1][i] > maxValue){
                    maxValue = state[n-1][i];
                }
            }
            return maxValue;
    }


}
