import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by mshz on 2017/11/9.
 */
public class TimerScheduleMgr {
    private  static TimerScheduleMgr  m_instance = new TimerScheduleMgr();
    private ScheduledExecutorService service;


    private TimerScheduleMgr()
    {
        service = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     *
     * @param runable
     * @param initialDelay 首次执行时间
     * @param period  执行间隔
     * @param unit  时间单位
     */
    public void addScheduled(Runnable runable,long initialDelay,long period,TimeUnit unit)
    {
        service.scheduleAtFixedRate(runable,initialDelay,period, unit);
    }


    public static TimerScheduleMgr getInstance(){return m_instance;}

}
