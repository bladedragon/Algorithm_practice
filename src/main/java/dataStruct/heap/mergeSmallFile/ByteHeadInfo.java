package dataStruct.heap.mergeSmallFile;

import lombok.Data;

@Data
public class ByteHeadInfo {
    private byte value;

    private int index;

    public ByteHeadInfo(byte value,int index){
        this.value = value;
        this.index = index;
    }


}
