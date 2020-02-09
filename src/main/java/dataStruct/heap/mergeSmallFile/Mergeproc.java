package dataStruct.heap.mergeSmallFile;

import java.io.File;
import java.util.PriorityQueue;

/**
 * 进行有序文件的合并操作
 */
public class Mergeproc {

    public static final Mergeproc INSTANCE = new Mergeproc();

    /**
     * 获取实例信息
     * @param path
     * @return
     */
    public FileMergeBean[] getMergeBean(String path){
        FileMergeBean[] result = null;

        File file = new File(path);

        if(file.exists()){
            File[] fileChilds = file.listFiles();
            result = new FileMergeBean[fileChilds.length];

            for(int i =0; i<result.length;i++){
                result[i] = FileMergeProc.INSTANCE.openFile(fileChilds[i]);
            }
        }
        return result;
    }

    /**
     * 读取文件i信息，合并成大文件
     * @param mergeBeans
     * @param outPath
     */
    public void reader(FileMergeBean[] mergeBeans, String outPath){
        //每个节点读取一个缓冲区数据
        for(int i =0;i<mergeBeans.length;i++){
            FileMergeProc.INSTANCE.readFile(mergeBeans[i]);
        }

        PriorityQueue<ByteHeadInfo> smallHeap = new PriorityQueue<ByteHeadInfo>(
                mergeBeans.length,
                (o1,o2)->{
                    if(o1.getValue() > o2.getValue()){
                        return 1;
                    }else if(o1.getValue() < o2.getValue()){
                        return -1;
                    }
                    return 0;
                });

        //遍历所有文件，都取一个放到小顶堆
        for(int i =0;i<mergeBeans.length;i++){
            //如果缓冲区的Index小于文件读取索引，如果大于说明一个缓冲区的数据都已经读取完毕
            if(mergeBeans[i].getBufferReadIndex() <= mergeBeans[i].getFileReadIndex()){
                smallHeap.offer(
                        //一个ByteHead对象存储一个byte
                        new ByteHeadInfo(mergeBeans[i].getBuffer()[mergeBeans[i].getBufferReadIndex()],i));
                //索引向前推进一步
            mergeBeans[i].setBufferReadIndex(mergeBeans[i].getBufferReadIndex()+1);
            }
        }

        //输出文件
        OutFileBean outFile = FileOutProcess.INSTANCE.openFile(outPath);
        ByteHeadInfo currByte;

        //如果当前文件未结束，继续遍历
        while(!checkFinish(mergeBeans) || !smallHeap.isEmpty()){
            //取出堆中最小值
            currByte = smallHeap.poll();
            //缓冲区未满，先写入缓冲区
            if(outFile.getOutIndex() < outFile.getMaxIndex()){
                outFile.getBuffer()[outFile.getOutIndex()] = currByte.getValue();
                outFile.setOutIndex(outFile.getOutIndex()+1);
            }
            //缓冲区满，写入文件
            if (outFile.getOutIndex() == outFile.getMaxIndex()) {
                FileOutProcess.INSTANCE.fileWrite(outFile);
            }

            // 将当前文件的下一个数据加入到当前小顶堆中
            this.readFileNextByte(mergeBeans[currByte.getIndex()], currByte.getIndex(), smallHeap);
        }
        FileOutProcess.INSTANCE.fileWrite(outFile);

        // 做最后的文件关闭操作
        FileMergeProc.INSTANCE.close(mergeBeans);
        // 关闭输出文件
        FileOutProcess.INSTANCE.close(outFile);
    }

    private boolean checkFinish(FileMergeBean[] mergeBeans) {
        for (int i = 0; i < mergeBeans.length; i++) {
            if (!mergeBeans [i].isFinish()) {
                return false;
            }
        }

        return true;
    }

    /**
     * 读取下一个字节信息
     *
     * @param margeFile 合并文件信息
     * @param smallHeap 小顶堆信息
     */
    private void readFileNextByte(
            FileMergeBean margeFile, int index, PriorityQueue<ByteHeadInfo> smallHeap) {
        // 如果当前中的缓冲区还未读取完，从先从缓冲区中读取
        if (margeFile.getBufferReadIndex() < margeFile.getFileReadIndex()) {
            smallHeap.offer(
                    new ByteHeadInfo(margeFile.getBuffer()[margeFile.getBufferReadIndex()], index));
            // 将索引向前推进一步
            margeFile.setBufferReadIndex(margeFile.getBufferReadIndex() + 1);
        }
        // 如果已经读取完成,则写入磁盘文件中
        if (margeFile.getBufferReadIndex() == margeFile.getFileReadIndex()) {
            FileMergeProc.INSTANCE.readFile(margeFile);
        }
    }

}
