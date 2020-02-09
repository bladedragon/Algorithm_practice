package dataStruct.heap.bigFileTopK;

import lombok.Data;

/**
 * 关键字信息
 */
@Data
public class KeyInfo {
    /**
     * 关键字
     */
    private String key;

    /**
     * 关键字次数
     */
    private int countNum;

    public KeyInfo(String key,int countNum){
        this.key = key;
        this.countNum = countNum;
    }




}
