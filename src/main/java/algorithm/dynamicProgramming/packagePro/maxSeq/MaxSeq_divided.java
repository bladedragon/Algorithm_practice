package algorithm.dynamicProgramming.packagePro.maxSeq;

import java.util.regex.Pattern;

public class MaxSeq_divided {

    public int lengthOfLTS(int[] a){
        if(a.length <= 1){
            return a.length;
        }

        int[] tails = new int[a.length];
        tails[0] = 0;

        int end = 0;
        for(int i = 1; i < a.length; i++){
            if(tails[end] < a[i]){
                end++;
                tails[end] = a[i];
            }else{
                int left = 0;
                int right = end;
                while(left < right){
                    int mid = left+(right-left)/2;
                    if(tails[mid] < a[i]){
                        left = mid+1;
                    }else{
                        right = mid;
                    }
                }
                tails[left] = a[i];
            }

        }
        end++;
        return end;
    }

}
