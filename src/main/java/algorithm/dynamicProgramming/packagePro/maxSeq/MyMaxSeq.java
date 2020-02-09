package algorithm.dynamicProgramming.packagePro.maxSeq;

import java.util.Arrays;

public class MyMaxSeq {
    public int max_length = Integer.MIN_VALUE;

    /**
     * 最大问题：没办法保证求得最大得递增数，会存在部分递增得情况
     * @param a
     * @param index
     * @param maxSeq
     */
    public void recruitBackMaxSeq(int[] a,int index,int maxSeq){

        if(index == a.length-1){
            if(max_length < maxSeq){
                max_length = maxSeq;
            }
            return;
        }

        for(int i =index+1 ;i<a.length;i++){
            if(a[index] < a[i]){
                recruitBackMaxSeq(a,index+1,maxSeq+1);
            }else{
                recruitBackMaxSeq(a,index+1,maxSeq);
            }
        }
    }

    public  int lengthOfLIS(int[] a){
        return lengthofLIS(a,Integer.MIN_VALUE,0);
    }

    private int lengthofLIS(int[] a, int prevs, int curpos) {
        if(curpos == a.length){
            return 0;
        }
        int taken = 0;
        if(a[curpos] > a[prevs]){
            taken = 1+lengthofLIS(a,curpos,curpos+1);
        }
        int nottaken =lengthofLIS(a,prevs,curpos+1);
        return Math.max(taken,nottaken);
    }

    /**
     * 找到所有的子序列
     * @param a
     * @return
     */
    public int lengthOfLTS2(int[] a){
        int[][] mem = new int[a.length+1][a.length];
        for(int[] l: mem){
            Arrays.fill(l,-1);
        }
        return lengthofLTS2(a,-1,0,mem);
    }

    private int lengthofLTS2(int[] a, int previndex, int curpos, int[][] mem) {
        if(curpos == a.length){
            return 0;
        }
        if(mem[previndex+1][curpos] != -1){
            return mem[previndex+1][curpos];
        }

        int taken = 0;
        if(previndex <0 || a[previndex] < a[curpos]){
            taken = 1+lengthofLTS2(a,curpos,curpos+1,mem);
        }
        int nottaken = lengthofLTS2(a,previndex,curpos+1,mem);
        mem[previndex][curpos] = Math.max(taken,nottaken);
        return mem[previndex][curpos];
    }

    public int getMaxDP(int a[], int n){
        if(n <=0){
            return 0;
        }
        int[] state = new int[n];
        //表示这个阶段为止的最大长度
        state[0] = 1;
        int ans = 1;
        for(int i = 0; i < n; i++){
            //临时保存状态值，确保state[j]达到最大值
            int maxval = 0;
            for(int j =0; j < i; j++){
                if(a[i] > a[j]){
                    maxval = Math.max(maxval,state[j]);
                }
            }
            //每次更新自己的状态,把自己的加上，如果全部不匹配至少自己还存在1
            state[i]  = maxval+1;
            //就是把之后的遍历省去
            ans = Math.max(state[i],ans);
        }
        return ans;
    }


    public static void main(String[] args) {
        MyMaxSeq myMaxSeq = new MyMaxSeq();
        int[] a= {2, 9, 3, 6, 5, 1, 7,1,2};
        int[] b = {5,4,3,2,1};
        myMaxSeq.recruitBackMaxSeq(a,0,1);
//        System.out.println(myMaxSeq.max_length);
//        System.out.println(myMaxSeq.lengthOfLIS(a));
        System.out.println(myMaxSeq.getMaxDP(b,b.length));
    }
}
