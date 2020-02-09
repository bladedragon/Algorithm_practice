package algorithm.greedy.case1;

import lombok.Data;

@Data
public class Goods {

    private String name;
    private int weight;
    private float price;
    private float unitPrice;

    Goods(String name,int weight,float price,float unitPrice){
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.unitPrice = unitPrice;
    }



}
