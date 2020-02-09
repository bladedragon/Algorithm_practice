package algorithm.dynamicProgramming.packagePro.charMatch;

public class LongestPublicString {

    public void getLongestString(int[] a,int n, int[] b, int m) {
        int[][] match = new int[n][m];
        for (int i = 0; i < n; i++) {
            if (a[0] == b[i]) {
                match[0][i] = 1;
            } else if (i != 0) {
                match[0][i] = match[0][i - 1];
            } else {
                match[0][i] = 0;
            }
        }
        for (int i = 0; i < m; i++) {
            if (a[i] == b[0]) {
                match[i][0] = 1;
            } else if (i != 0) {
                match[i][0] = match[i - 1][0];
            } else {
                match[i][0] = 0;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) {
                    match[i][j] = max(match[i - 1][j - 1]+1, match[i - 1][j] , match[i][j - 1]);
                } else {
                    match[i][j] = max(match[i - 1][j],match[i][j - 1],match[i - 1][j - 1]);

                }
            }
        }
    }

    private int max(int a, int b, int c){
        int maxv = Integer.MIN_VALUE;
        if(a > maxv){
            maxv = a;
        }
        if(b > maxv){
            maxv = b;
        }
        if(c > maxv){
            maxv = c;
        }
        return maxv;
    }

}
