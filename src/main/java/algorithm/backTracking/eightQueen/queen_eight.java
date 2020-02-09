package algorithm.backTracking.eightQueen;

public class queen_eight {
    int[] result = new int[8];
    public void cal8Queens(int row){
        if( row >= 8){
            return ;
        }
        //遍历column
        for(int i =0;i<8;i++){
            if(isOK(row,i)){
                result[row] = i;
                cal8Queens(row+11);
            }
        }
    }

    private boolean isOK(int row, int column) {
        int leftup = column-1,rightup = column +1;

        for(int i =row-1;i >= 0;i--){
            if(result[i] ==  column){
                return false;
            }
            if(leftup >= 0 && result[i] == leftup){
                return false;
            }
            if(rightup <=8 && result[i] == rightup){
                return false;
            }
            leftup ++ ;
            rightup --;
        }

        return true;
    }

    private void printQueens(int[] result) {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (result[row] == column) {
                    System.out.println("Q ");
                } else {
                    System.out.println("* ");
                }
            }
            System.out.println(" ");
        }
        System.out.println(" ");;
    }
}
