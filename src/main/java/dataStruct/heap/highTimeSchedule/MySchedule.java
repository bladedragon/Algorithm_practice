package dataStruct.heap.highTimeSchedule;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 高性能的定时任务调度
 * 使用最小堆
 */
public class MySchedule implements  Runnable{
    public static final MySchedule INSTANCE = new MySchedule();

    private PriorityBlockingQueue<ScheduleBean> queue = new PriorityBlockingQueue<ScheduleBean>(
            8,
            (o1,o2)-> {
                if(o1.getRunTime() > o2.getRunTime()){
                    return 1;
                }else if(o1.getRunTime() < o2.getRunTime()){
                    return -1;
                }
                return 0;
            });

    /**
     * 本质是堆中添加元素
     * @param runtime
     * @param run
     */
    public void delayRun(long runtime,Runnable run){
        queue.offer(new ScheduleBean(runtime,run));
    }



    @Override
    public void run() {
        while(true){
            ScheduleBean schedule = null;
            try {
                //阻塞队列可以入参时间表示如果队列为空的线程等待时间
                schedule = queue.poll(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(queue);
            if(schedule == null){
                continue;
            }
            long currTime = System.currentTimeMillis();
            //距离下一个任务的启动还剩的时间
            long runTime = schedule.getRunTime()-currTime;

            if(runTime > 0){
                try {
                    Thread.sleep(runTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //运行定时任务
            RunSchedule.INSTANCE.runTask(schedule.getRunTime(),schedule.getRunnable());

        }
    }
}
