package algorithm.dynamicProgramming.packagePro.packagePro;

import javax.management.remote.rmi._RMIConnection_Stub;

public class Demo {

    /**
     *
     * @param weight 物品的重量
     * @param w  背包可承受的重量
     * @param n  物品的个数
     */
    public int knapsack(int[] weight, int w, int n){
        boolean[][] state = new boolean[n][w+1];
        state[0][0] = true;
        if(weight[0] <= w){
            state[0][weight[0]] = true;
        }

        for(int i = 1; i < n;i++){

            for(int j = 0; j <= w; j++){
                if(state[i-1][j] == true){
                    state[i][j] = true;
                }
            }
            for(int j = 0; j <= w-weight[i]; j++){
                if(state[i-1][j] == true){
                    state[i][j+weight[i]] = state[i-1][j];
                }
            }

        }
        for(int i =w; i >= 0; i--){
            if(state[n-1][i] == true){
                return i;
            }
        }
        return 0;
    }

    /**
     *
     * @param weight
     * @param w
     * @param n
     * @return
     */
    public int knapsack2(int[] weight, int w, int n){
        boolean[] state = new boolean[w+1];
        if(weight[0] <= w){
            state[weight[0]] = true;
        }

        for(int i=1; i < n; i++){
            //必须倒序，因为倒序遍历到的第一个一定是之前的数值相加得到的（因为是最大的）
            for(int j = w-weight[i]; j >=0; j--){
                if(state[j] == true){
                    state[j+weight[i]] = true;
                }
            }
        }
        for(int i = w; i >= 0; i--){
            if(state[i] == true){
                return i;
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        Demo demo = new Demo();
        int[] a = {1,2,3,4,5,6};
        int res = demo.knapsack2(a,5,a.length);
        System.out.println(res);
    }

}
