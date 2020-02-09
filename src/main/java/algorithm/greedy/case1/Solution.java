package algorithm.greedy.case1;


//假设我们有一个可以容纳 100kg 物品的背包，
//可以装各种物品。我们有以下 5 种豆子，
//每种豆子的总量和总价值都各不相同。
//为了让背包中所装物品的总价值最大，
//我们如何选择在背包中装哪些豆子？
//每种豆子又该装多少呢？
//
//物品           重量(KG)          总价值（元)
//黄豆           100               100
//绿豆           30                90
//红豆           60                120
//黑豆           20                80
//青豆           50                75

import javafx.scene.layout.Priority;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 */
public class Solution {

    private PriorityQueue<Goods> goodsList = new PriorityQueue<>(
            (o1,o2) -> {
                if(o1.getUnitPrice() < o2.getUnitPrice()){
                    return 1;
                }else if(o1.getUnitPrice() > o2.getUnitPrice()){
                    return -1;
                }else{
                    return 0;
                }
            }
    );

    public void addGoods(Goods gs) {
        this.goodsList.add(gs);
    }

    public List<Goods> getMaxValueGoods(int weight){
        List<Goods> list = new ArrayList<>();
        int surplus = weight;

        while(!goodsList.isEmpty()){
            Goods goods = goodsList.poll();
            if (surplus >= goods.getWeight()) {
                surplus = surplus - goods.getWeight();
                list.add(goods);
        }else{
                int surplusVals = goods.getWeight() - surplus;
                goods.setWeight(surplusVals);
                goodsList.offer(goods);
                Goods result =
                        new Goods(goods.getName(), surplus, goods.getPrice(), goods.getUnitPrice());
                list.add(result);
                break;
            }
            }
        return list;

    }
}
