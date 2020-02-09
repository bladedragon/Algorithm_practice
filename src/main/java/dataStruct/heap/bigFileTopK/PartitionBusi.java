package dataStruct.heap.bigFileTopK;

import lombok.Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 切分数据的bean
 *
 */
@Data
public class PartitionBusi {

    private int index;
    private String path;

    /*
    文件流对象
     */
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    /*
    缓冲流对象
     */
    private FileWriter fileWriter;
    private FileReader fileReader;




}
