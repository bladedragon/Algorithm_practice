package algorithm.dynamicProgramming.packagePro.yanghuiTriangle;

public class YanghuiTriangle {
    private static int[][] matrix = {{5},{7,8},{2,3,4},{4,9,6,1},{9,7,5,4,2}};

    public int yanghuiTriangle(int[][] matrix) {
        int[][] state = new int[matrix.length][matrix.length];
        state[0][0] = matrix[0][0];

        for(int i =1; i < matrix.length; i++){
            for(int j =0; j < matrix[i].length; j++){
                if(j==0){
                    state[i][j] = state[i-1][j] +matrix[i][j];
                }else if (j == matrix.length - 1){
                    state[i][j] = state[i-1][j-1] + matrix[i][j];
                }else{
                    int top1 = state[i - 1][j - 1];
                    int top2 = state[i - 1][j];
                    state[i][j] = Math.min(top1, top2) + matrix[i][j];
                }
            }
        }
        int minDis = Integer.MAX_VALUE;
        for(int i =0; i < matrix.length-1;i++){
            if(state[matrix.length-1][i] < minDis){
                minDis = state[matrix.length-1][i];
            }
        }
        return minDis;
    }


    public static void main(String[] args) {
        YanghuiTriangle yanghuiTriangle = new YanghuiTriangle();
        int res = yanghuiTriangle.yanghuiTriangle(matrix);
        System.out.println(res);
    }
}
