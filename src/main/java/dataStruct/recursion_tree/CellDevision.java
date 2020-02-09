package dataStruct.recursion_tree;

public class CellDevision {

    /**
     * 细胞分裂问题
     * 最短路径是n/4，最长路径是n，平均时间复杂度是O(2^n)
     * @param n
     * @return
     */
    public static int GetCellCount(int n){
        if(n < 0){
            return 0;
        }
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return 2;
        }
        if(n == 2){
            return 4;
        }
        if(n == 3){
            return 7;
        }
        return 2* GetCellCount(n-1)-GetCellCount(n-4);

    }

    public static void main(String[] args) {
        System.out.println(GetCellCount(4));
    }
}
