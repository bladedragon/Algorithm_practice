package dataStruct.heap.mergeSmallFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutProcess {
    /** 文件处理操作 */
    public static final FileOutProcess INSTANCE = new FileOutProcess();

    /** 最大缓冲区大小 */
    private static final int MAX_BUFFER = 10;

    public OutFileBean openFile(String path) {

        OutFileBean outFile = new OutFileBean(MAX_BUFFER);

        outFile.setOutPath(path);
        try {
            outFile.setOutput(new FileOutputStream(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return outFile;
    }

    public void fileWrite(OutFileBean beanBuff) {
        FileOutputStream outputStream = beanBuff.getOutput();

        try {
            outputStream.write(beanBuff.getBuffer(), 0, beanBuff.getOutIndex());
            // 将数据刷入磁盘
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        beanBuff.setOutIndex(0);
    }

    public void close(OutFileBean busiBuff) {
        if (null != busiBuff) {
            try {
                busiBuff.getOutput().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
