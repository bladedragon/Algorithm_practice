package dataStruct.heap.highTimeSchedule;

import java.time.LocalDateTime;

public class RunSchedule {

    public static final RunSchedule INSTANCE = new RunSchedule();

    public final long getCurrentTime(){
        return System.currentTimeMillis();
    }

    /**
     *
     * @param runTime 预期到运行的时间点
     * @param task 运行的任务
     */
    public void runTask(long runTime,Runnable task){
        long currTime;

        //如果没有到时间就继续sleep吧
        while(true){
            currTime = this.getCurrentTime();
            long needTime = runTime - currTime;

            //有点像重复确认
            if(needTime > 0){
                try {
                    Thread.sleep(needTime);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }

            LocalDateTime localDateTime = LocalDateTime.now();
            System.out.println("执行时间"+ localDateTime);

            task.run();
        }

    }


}
