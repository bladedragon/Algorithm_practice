package algorithm.backTracking.packageZoneOne;

public class Demo {
    /**
     * maxW是存储背包总重量的最大值
     */
    public int maxW = Integer.MIN_VALUE;

    /**
     *
     * @param i 考察到哪个物品了
     * @param cw 当前已经装入背包的总重量
     * @param items  每个物品的重量
     * @param n  物品的个数
     * @param w  物品的总重量
     */
    public void f(int i,int cw,int[] items,int n,int w){
            if(cw ==w || i == n){
                if( cw >maxW){
                    cw = maxW;
                }
                return;
            }
            //不选择这个物品，直接考虑下一个
            f(i+1,cw,items,n,w);
            //考虑这个物品，增加背包重量
            if(cw+items[i] <= w){
                f(i+1,cw+items[i],items,n,w);
            }
    }
}
