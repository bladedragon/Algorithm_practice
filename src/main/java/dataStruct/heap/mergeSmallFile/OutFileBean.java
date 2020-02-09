package dataStruct.heap.mergeSmallFile;

import lombok.Data;

import java.io.FileOutputStream;

@Data
public class OutFileBean {

    //当前写入的索引
    private int outIndex;

    //最大写入的索引
    private int maxIndex;

    //预写入的缓冲区数据
    private byte[] buffer;

    private String outPath;

    private FileOutputStream output;

    public OutFileBean(int maxIndex){
        this.maxIndex = maxIndex;
        buffer = new byte[maxIndex];

    }
}
