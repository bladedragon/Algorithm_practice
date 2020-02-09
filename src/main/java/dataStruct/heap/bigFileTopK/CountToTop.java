package dataStruct.heap.bigFileTopK;

import java.util.*;

public class CountToTop {
    /*
    静态实例
     */
    public final static CountToTop INSTANCE = new CountToTop();

    /**
     * 进行统计的map操作
     */
    private Map<String,Integer> countMap = new HashMap<>(10240);


    /**
     * 自定义比较器用于优先级队列插入
     * 实现的是最小堆
     */
    private Comparator<KeyInfo> comp = (o1,o2) ->{
        if(o1.getCountNum() > o2.getCountNum()){
            return 1;
        }else if(o1.getCountNum() < o2.getCountNum()){
            return -1;
        }else{
            return 0;
        }
    };

    public void dataClear(){
        countMap.clear();
    }

    /**
     * 将数据放到map进行统计
     * @param data
     */
    public void putData(String data){
        Integer outValue = countMap.get(data);
        if(outValue ==null){
            outValue = 0;
        }
        outValue++;
        countMap.put(data,outValue);
    }

    public KeyInfo[] getTopK(int numk){
        //优先级队列，底层用堆实现
        PriorityQueue<KeyInfo> topN = new PriorityQueue<>(numk,comp);

        Iterator<Map.Entry<String,Integer>> iterator = countMap.entrySet().iterator();

        Map.Entry<String,Integer> entry;

        while(iterator.hasNext()){

            entry = iterator.next();
            if(topN.size()<numk){
                topN.offer(new KeyInfo(entry.getKey(),entry.getValue()));
            }else{
                //判断待加入元素是否大于堆顶元素与，大于则加入，不然就丢弃
                if(topN.peek().getCountNum() < entry.getValue()){
                    topN.poll();
                    topN.offer(new KeyInfo(entry.getKey(),entry.getValue()));
                }
            }
        }

        //结果集
        KeyInfo[] result = new KeyInfo[numk];
        topN.toArray(result);

        return result;
    }

    /**
     * 最终topN求解
     * @param list
     * @param numk
     * @return
     */
    public KeyInfo[] getTopK(List<KeyInfo[]> list, int numk) {

        // 进行求解
        PriorityQueue<KeyInfo> topN = new PriorityQueue<>(numk, comp);

        for (KeyInfo[] keys : list) {

            for (KeyInfo busi : keys) {

                if (busi == null) {
                    break;
                }

                if (topN.size() < numk) {
                    topN.offer(new KeyInfo(busi.getKey(), busi.getCountNum()));
                } else {
                    // 如果当前数据比小顶求的队头大，则加入，否则丢弃
                    if (topN.peek().getCountNum() < busi.getCountNum()) {
                        topN.poll();
                        topN.offer(busi);
                    }
                }
            }
        }

        // 结果集
        KeyInfo[] result = new KeyInfo[numk];
        topN.toArray(result);

        return result;
    }
}
