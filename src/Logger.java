/**
 * Created by mshz on 2017/11/6.
 */
public class Logger {
    public static String TAG = "Logger";
    public void TestLogger(String str)
    {
        System.out.println("-----------------------------TestLogger------------------" + str);
    }

    public static void info(String content){
        System.out.println("-----------------------------info------------------" + content);
    }
}
