package dataStruct.heap.mergeSmallFile;

import lombok.Data;

import java.io.FileInputStream;

@Data
public class FileMergeBean {

    /**
     * 文件读取流
     */
    private FileInputStream input;

    /**
     * 读取的数据信息
     */
    private byte[] buffer;

    /**
     * 读取路径
     */
    private String readPath;

    /**
     * 读取的缓冲区索引
     */
    private int bufferReadIndex;

    /**
     * 文件中读取的缓冲区大小
     */
    private int fileReadIndex;

    /**
     * 是否读取完成
     */
    private boolean finish;

}
