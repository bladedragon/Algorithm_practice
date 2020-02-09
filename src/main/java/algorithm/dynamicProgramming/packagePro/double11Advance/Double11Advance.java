package algorithm.dynamicProgramming.packagePro.double11Advance;

public class Double11Advance {

    /**
     *
     * @param items
     * @param n
     * @param w
     */
    public static void double11Adance(int[] items, int n, int w){

        //超过三倍就没有薅羊毛价值了，w指的是价值
        boolean[][] state = new boolean[n][3*w+1];

        state[0][0] = true;
        if(items[0] < w){
            state[0][items[0]] = true;
        }
        for(int i =1;i<n;i++){
            for(int j =0 ;j <= 3*w; j++){
                if(state[i-1][j] == true){
                    state[i][j] = state[i-1][j];
                }
            }
            for(int j =0; j<= w*3 -items[i];j++){
                if(state[i-1][j] == true){
                    state[i-1][j +items[i]] = true;
                }
            }
        }
        int j;
        for(j =w;j<3*w+1;j++){
            if(state[n-1][j] == true){
                break;
            }
        }
        if( j== 3*w+1){
            return;
        }
        for(int i =n-1; i>=1; i--){
            if(j-items[i] >= 0 && state[i-1][j-items[i]] == true){
                System.out.print(items[i] + " "); // 购买这个商品
                j = j-items[i];
            }
            //没有购买这个商品，j不变
        }
        if (j != 0){
            System.out.print(items[0]);
        }
    }
}
