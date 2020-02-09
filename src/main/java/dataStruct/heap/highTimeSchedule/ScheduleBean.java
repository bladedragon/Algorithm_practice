package dataStruct.heap.highTimeSchedule;


import lombok.Data;

@Data
public class ScheduleBean {

    /**
     * 定时运行的时间点
     */
    private long runTime;

    /**
     * 定时任务的时间点
     */
    private Runnable runnable;

    public ScheduleBean(long runTime,Runnable run){
        this.runTime = runTime;
        this.runnable = run;
    }


}
