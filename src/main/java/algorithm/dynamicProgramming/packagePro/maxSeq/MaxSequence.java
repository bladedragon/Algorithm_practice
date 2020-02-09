package algorithm.dynamicProgramming.packagePro.maxSeq;

//
public class MaxSequence {

    /**
     * 还是有bug
     * @param array
     * @return
     */
    public int longString(int[] array){
        if(array.length <2){
            return array.length;
        }
        int maxLen = Integer.MIN_VALUE;
        int[][] state = new int[array.length][array.length];
        state[0][0]  = 0;
        for(int i =1 ; i < array.length;i++){
            for(int j =0; j < i;j++){
                if(array[i] > array[j]){
                    state[i][j] = state[i-1][j]+1;
                }else{
                    //继承上一层的数据
                    state[i][j] = state[i-1][j];
                }
            }
        }
        for(int i = 0;i<array.length;i++){
            if(maxLen < state[array.length-1][i]){
                maxLen = state[array.length-1][i];
            }
        }
        return maxLen;
    }

    public int longestIncreaseSubArrayDP(int[] array) {
        if (array.length < 2){
            return array.length;
        }
        int[] state = new int[array.length];
        state[0] = 1;//哨兵是1
        for (int i = 1; i < state.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    if (state[j] > max) {
                        max = state[j];
                    }
                }
            }
            state[i] = max + 1;
        }
        int result = 0;
        for (int i = 0; i < state.length; i++) {
            if (state[i] > result){
                result = state[i];
            }
        }
        return result;
    }



    public static void main(String[] args) {
//        int[] a = {4,1,5,3,6,7,9,1,1};
        int[] a = {10,9,2,5,3,7,101,18};
        MaxSequence maxSequence = new MaxSequence();
        System.out.println(maxSequence.longString(a));
        System.out.println(maxSequence.longestIncreaseSubArrayDP(a));
    }
}
