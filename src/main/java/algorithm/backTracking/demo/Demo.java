package algorithm.backTracking.demo;

public class Demo {
    private int minDist =Integer.MAX_VALUE;

    public void getMinDist(int i, int j, int dist, int[][] w, int n){
        if(i == n && j == n){
            if(minDist > dist){
                minDist = dist;
            }
            return;
        }

        if(i < n){
            getMinDist(i+1,j,dist+ w[i][j],w,n);
        }
        if(j < n){
            getMinDist(i,j+1,dist+w[i][j],w,n);
        }
    }
}
