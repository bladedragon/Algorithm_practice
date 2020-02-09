package algorithm.dynamicProgramming.packagePro.maxSeq;

import java.util.Arrays;

/**
 * 回溯算法有问题
 */
public class MaxSeq_BackTracking {

    public int max_length = Integer.MIN_VALUE;
    public void recusingCount(int[] arrays,int index, int maxSeq){
        int maxLength = arrays.length;
        if(index == maxLength -1){
            if(max_length < maxSeq){
                max_length = maxSeq;
            }
            return;
        }
        for(int i = index+1 ; i < maxLength; i++){
            if(arrays[index] < arrays[i]){
                recusingCount(arrays,index+1, maxSeq+1);
            }else {
                recusingCount(arrays,index+1,maxSeq);
            }
        }
    }

    public void recursionCount2(int[] arrays, int i) {

        if (i == arrays.length) {
            return;
        }
        int[] status = new int[arrays.length];

        int maxSeqNum = 0;
        boolean firstGet = true;
        int maxSeqValue = Integer.MAX_VALUE;
        for (int j = 0; j < i; j++) {
            // 1,查到比当前字符小的
            if (arrays[j] < arrays[i]) {
                if (firstGet) {
                    maxSeqValue = arrays[j];
                    firstGet = false;
                    status[maxSeqNum] = arrays[j];
                    maxSeqNum = maxSeqNum + 1;
                    status[maxSeqNum] = arrays[i];
                } else {
                    if (arrays[j] > maxSeqValue) {
                        maxSeqValue = arrays[j];
                        status[maxSeqNum] = arrays[j];
                        maxSeqNum = maxSeqNum + 1;
                        status[maxSeqNum] = arrays[i];
                    }
                }
            }
        }

        System.out.println(Arrays.toString(status));

        status[i] = maxSeqNum;
        recursionCount2(arrays, i + 1);
    }

    public static void main(String[] args) {
        int[] a = {2, 9, 3, 6, 5, 1, 7};
        MaxSeq_BackTracking maxSeq_backTracking = new MaxSeq_BackTracking();
//        int maxLen = 0;
//        maxSeq_backTracking.recusingCount(a,0,1);
        maxSeq_backTracking.recursionCount2(a,0);
        System.out.println(maxSeq_backTracking.max_length);
//        System.out.println(maxLen);
    }
}
