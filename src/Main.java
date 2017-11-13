import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by mshz on 2017/11/6.
 */
public class Main {

    public static void main(String[] args) throws Exception
    {
        // luaj to java 简单实用
        //testBase()

        // 一个简单版本的架构
        testJG();
    }

    public static TestObject obj ;
    /**
     * 一个简单版本的热更新架构
     */
    public static void testJG()
    {

        obj = new TestObject(10,20);
        // 测试用于实时热更新框架
        TimerScheduleMgr.getInstance().addScheduled(new Runnable() {
            @Override
            public void run() {
                try {
                    ScriptMgr.getInstance().loadScript();

                    java2Lua();
                    //lua2Java();
                }catch (Exception ex)
                {}
            }
        },2 ,10, TimeUnit.SECONDS);
    }

    /**
     * java 调用lua
     */
    public static void java2Lua()
    {
        ScriptMgr mgr = ScriptMgr.getInstance();
        // java调用 不带参数lua
        mgr.call(LuaValue.valueOf("hello"));

        // java 调用 带参数lua
        mgr.call(LuaValue.valueOf("hi"),LuaValue.valueOf(new Date().toString()));

        // java 调用 带参java对象
        Varargs res =  mgr.getGlobals().get(LuaValue.valueOf("testObject")).invoke(new LuaValue[]{CoerceJavaToLua.coerce(obj)});
        //obj = (TestObject)res.arg1().checkuserdata(TestObject.class);
        System.out.println("java hold a 1 " + obj.getA());
        obj = (TestObject)res.arg1().checkuserdata(TestObject.class);
        System.out.println("java hold a 2 " + obj.getA());
    }

    /**
     * lua 调用java
     */
    public static void lua2Java()
    {
        ScriptMgr mgr = ScriptMgr.getInstance();
        mgr.call(LuaValue.valueOf("testLua2Java"));
    }


    /**
     * 基本调用
     */
    public static void testBase()
    {
        // eg: 1 调用字符串
        String luaStr = "print 'hello,world!'";
        Globals globals = JsePlatform.standardGlobals();
        LuaValue chunk = globals.load(luaStr);
        chunk.call();

        /// eg:  2
        String luaPath = "res/login.lua";   //lua脚本文件所在路径
        globals = JsePlatform.standardGlobals();
        //加载脚本文件login.lua，并编译
        globals.loadfile(luaPath).call();
        //获取无参函数hello
        LuaValue func = globals.get(LuaValue.valueOf("hello"));
        //执行hello方法
        func.call();
        //获取带参函数test
        LuaValue func1 = globals.get(LuaValue.valueOf("test"));
        //执行test方法,传入String类型的参数参数
        String data = func1.call(LuaValue.valueOf("I'am from Java!")).toString();
        //打印lua函数回传的数据
        System.out.println("data return from lua is:"+data);

        // eg: 3
        globals = JsePlatform.standardGlobals();
        globals.loadfile("res/test.lua").call();
    }
}
