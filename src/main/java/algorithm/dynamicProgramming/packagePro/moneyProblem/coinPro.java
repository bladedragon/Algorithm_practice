package algorithm.dynamicProgramming.packagePro.moneyProblem;

//钱币找零问题
public class coinPro {

    public int minCoin(int money){
        if(money == 1 || money == 3 || money == 5){
            return 1;
        }
        boolean[][] state = new boolean[money][money];
        //初始化
        if(money > 1){
            state[0][1] = true;
        }
        if(money > 3){
            state[0][3] = true;
        }
        if( money > 5){
            state[0][5] = true;
        }
        //i存放的是放入的钱币个数，j存放的是钱币的价值
        for(int i =1;i<money;i++){
            for(int j =1;j<=money;j++) {
                if (state[i - 1][j]) {
                    if (j + 1 <= money) {
                        state[i][j + 1] = true;
                    }
                    if (j + 3 <= money) {
                        state[i][j + 3] = true;
                    }
                    if (j + 5 <= money) {
                        state[i][j + 5] = true;
                    }
                }
            }
        }
        return money;
    }

}
