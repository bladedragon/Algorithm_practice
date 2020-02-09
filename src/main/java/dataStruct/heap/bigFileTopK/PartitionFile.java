package dataStruct.heap.bigFileTopK;

import java.io.*;

public class PartitionFile {

    //静态实例
    public static final  PartitionFile INSTANCE = new PartitionFile();

    private static final int MAX_PARTTITION_SIZE = 4;

    private static final String PARTTITION_DIR_NAME = "partition";

    private static final String SUFFIXE_NAME = ".buffer";

    public PartitionBusi[] getPartition(String basePath){
        PartitionBusi[] result = new PartitionBusi[MAX_PARTTITION_SIZE];

        File baseFile = new File(basePath+File.separator+PARTTITION_DIR_NAME);

        //如果之前有同名文件(夹)存在
        if(baseFile.exists()){
            File[] rsp = baseFile.listFiles();
            for (File fins : rsp) {
                fins.delete();
            }
        }
        baseFile.mkdir();

        for(int i =0;i<MAX_PARTTITION_SIZE;i++){
            result[i] = new PartitionBusi();
            //文件的索引，hash后的值和length取模后要和index相匹配
            result[i].setIndex(i);
            String path = baseFile.getPath()+File.separator+i+SUFFIXE_NAME;
            //path存放切分文件的路径
            result[i].setPath(path);

            try {
                result[i].setFileWriter(new FileWriter(path));
                result[i].setBufferedWriter(new BufferedWriter(result[i].getFileWriter()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void WriteData(PartitionBusi busi,String data){
        try {
            //写入data
            busi.getBufferedWriter().write(data);
            //写入新的一行
            busi.getBufferedWriter().newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 进行文件读取操作
     * @param file
     */
    public void openReader(PartitionBusi file) {
        try {
            file.setFileReader(new FileReader(file.getPath()));
            file.setBufferedReader(new BufferedReader(file.getFileReader()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 关闭写入流
     * @param partitionBusis
     */
    public void closeOutPut(PartitionBusi[] partitionBusis){
        for (int i = 0; i < partitionBusis.length; i++) {

            close(partitionBusis[i].getBufferedWriter());
            close(partitionBusis[i].getFileReader());
        }
    }

    /**
     * 进行文件关闭操作
     * @param file
     */
    public void closeReader(PartitionBusi file) {
        close(file.getFileReader());
        close(file.getBufferedReader());
    }

    /**
     * 关闭通用函数
     * @param stream
     */
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
