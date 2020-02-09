package dataStruct.heap.bigFileTopK;


import dataStruct.hashTable.HashCode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BigFileTopK {
    private static final String SPILT_FLAG= "";

    public KeyInfo[] topK(String file,int n){
        File curFIle = new File(file);

        // 1.将当前一个文件切分到多个文件中,按关键字的hash进行分片操作,
        PartitionBusi[] partitionBusis  = PartitionFile.INSTANCE.getPartition(curFIle.getParent());
        //这里真正堆数据文件进行分片
        this.fileToPartititon(file,partitionBusis);
        //关闭输出流文件使得可以写入磁盘
        PartitionFile.INSTANCE.closeOutPut(partitionBusis);
        //每个文件进行单独求TopN
        List<KeyInfo[]> topKList = countToTopK(partitionBusis,n);
        //多个文件整合
        KeyInfo[] topBusis  = CountToTop.INSTANCE.getTopK(topKList,n) ;

        return topBusis;

    }

    private List<KeyInfo[]> countToTopK(PartitionBusi[] partis, int n) {
        List<KeyInfo[]> list = new ArrayList<>();

        for (int i = 0; i < partis.length; i++) {
            PartitionFile.INSTANCE.openReader(partis[i]);
            // 进行数据读取
            try {
                String line = null;
                while ((line = partis[i].getBufferedReader().readLine()) != null) {
                    CountToTop.INSTANCE.putData(line);
                }
                // 完成一个文件进行一次topN的求解
                list.add(CountToTop.INSTANCE.getTopK(n));

                CountToTop.INSTANCE.dataClear();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 进行文件的关闭操作
                PartitionFile.INSTANCE.closeReader(partis[i]);
            }
        }

        return list;
    }


    /**
     * 将文件读取到分区文件中
     * @param file
     * @param parts
     */
    public void fileToPartititon(String file,PartitionBusi[] parts){
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        String line = null;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null){
                this.lineToPartition(line,parts);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            close(bufferedReader);
            close(fileReader);
        }

    }

    /**
     * 在一个数据文件中读取一条关键字数据
     * @param line
     * @param parts
     */
    private void lineToPartition(String line, PartitionBusi[] parts) {
        int index = 0;
        int findIndex;
        line = line.trim();
        while(index < line.length()){
            if((findIndex = line.indexOf(SPILT_FLAG,index)) != -1){
                String key = line.substring(index,findIndex);
                
                this.keyToPartition(key,parts);
                index = findIndex+1;
            }else{
                String key = line.substring(index);

                this.keyToPartition(key,parts);

                index += line.length();
            }
        }
    }

    /**
     * 对一个关键字生成hashcode并封装成一个PartitionFile
     * @param key
     * @param parts
     */
    private void keyToPartition(String key, PartitionBusi[] parts) {
        int partLength = parts.length;
        // hash算法使用FNV1_32_HASH计算,分拆到多个文件中
        int hashCode = HashCode.getHash(key);

        //使得hashcode可以放到每一个桶中
        int modelVal = hashCode & partLength;
        PartitionFile.INSTANCE.WriteData(parts[modelVal],key);
    }

    public static void close(Closeable stream) {
        if (null != stream) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
