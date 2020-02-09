package algorithm.dynamicProgramming.packagePro.countTwo;

public class Demo {

    /**
     * 状态转移表法
     * @param matrix
     * @param n
     * @return
     */
    public int minDist(int[][] matrix, int n){
        int[][] state = new int[matrix.length][matrix.length];
        int sum = 0;
        state[0][0] = matrix[0][0];

        //两边初始化
        for(int i=0; i<n;i++){
            sum +=  matrix[i][0];
            state[0][i] = sum;
        }
        sum =0;
        for(int i =0;i < n; i++){
            sum += matrix[0][i];
            state[0][i] = sum;
        }

        for(int i =1; i<matrix.length-1;i++){
            for(int j =0; j < matrix.length-1;j++){
                state[i][j] = matrix[i][j]+Math.min(state[i-1][j],state[i][j-1]);
            }
        }
        return state[n-1][n-1];
    }

    private int[][] matrix = {{1,3,5,9}, {2,1,3,4},{5,2,6,7},{6,8,4,3}};
    private int n = 4;
    //备忘录
    private int[][] mem = new int[4][4];


    /**
     * 状态转移方程
     * 递归加备忘录
     * @return
     */
    public int minDist(int i,int j){
        if(i ==0 && j==0){
            return matrix[0][0];
        }
        //备忘录
        if(mem[i][j] > 0){
            return mem[i][j];
        }
        int minLeft = Integer.MAX_VALUE;
        if(j-1 > 0){
            minLeft = minDist(i,j-1);
        }
        int minUp = Integer.MAX_VALUE;
        if(i-1 >0){
            minUp = minDist(i-1,j);
        }
        int currMinDist = matrix[i][j] + Math.min(minLeft,minUp);
        mem[i][j] = currMinDist;
        return currMinDist;

    }
}
