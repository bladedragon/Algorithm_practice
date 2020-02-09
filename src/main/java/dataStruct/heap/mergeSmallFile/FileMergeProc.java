package dataStruct.heap.mergeSmallFile;

import java.io.*;

/**
 * 文件合并操作的处理
 */
public class FileMergeProc {

    /** 实例信息 */
    public static final FileMergeProc INSTANCE = new FileMergeProc();

    /** 缓冲区的大小 */
    private static final int MAX_BUFF_SIZE = 10;

    public FileMergeBean openFile(File path){
        FileMergeBean mergeBean = new FileMergeBean();

        if(path.exists()){
            try {
                mergeBean.setInput(new FileInputStream(path));
                mergeBean.setBuffer(new byte[MAX_BUFF_SIZE]);
                mergeBean.setFileReadIndex(0);
                mergeBean.setBufferReadIndex(0);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return mergeBean;
    }

    /**
     * 读取文件中的比特信息
     * @param bean
     */
    public void readFile(FileMergeBean bean){
        if(!bean.isFinish()){
            try {
                //读取一个缓冲区长度
                int readLength = bean.getInput().read(bean.getBuffer());
                //设置文件已经读取的比特长度
                bean.setFileReadIndex(readLength);
                bean.setBufferReadIndex(0);
                if(readLength == -1){
                    bean.setFinish(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(FileMergeBean[] bean) {
        for (int i = 0; i < bean.length; i++) {
            if (null != bean[i]) {
                try {
                    bean[i].getInput().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

