import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mshz on 2017/11/9.
 */
public class ScriptMgr {

    private  static ScriptMgr   m_instance = new ScriptMgr();
    private final String PATH = "trunk/src/res";
    private Map<String,Long> maps = new HashMap<>();
    private Globals globals;

    private ScriptMgr()
    {
        this.globals = JsePlatform.standardGlobals();
    }

    public void loadScript()
    {
        try {
            File f = new File(PATH);
            if (f.isDirectory()) {
                File[] fs = f.listFiles();
                for (File item : fs) {
                    if (null == item)
                        continue;
                    String luaPath = "res/" + item.getName();
                    Long value = maps.get(luaPath);
                    long latModifyTime = item.lastModified();

                    // 如果文件没有加载过，或者 文件加载时间不相等
                    if (null == value || latModifyTime != value) {
                        //加载脚本文件 lua 并编译
                        globals.load(new InputStreamReader(new FileInputStream(item)),item.getName()).call();
                        maps.put(luaPath, latModifyTime);
                    }
                }
            }
        }catch (Exception ex){}
    }

    public LuaValue call(LuaString funcLuaStr)
    {
        return globals.get(funcLuaStr).call();
    }

    public LuaValue call(LuaString funcLuaStr,LuaString params)
    {
        return globals.get(funcLuaStr).call(params);
    }

    public LuaValue call(LuaString funcLuaStr,LuaString params0,LuaString params1,LuaString params2)
    {
        return globals.get(funcLuaStr).call(params0,params1,params2);
    }

    public Globals getGlobals() {return globals;}

    public LuaValue call(LuaString funcLuaStr,LuaString params0,LuaString params1)
    {
        return globals.get(funcLuaStr).call(params0,params1);
    }



    public static ScriptMgr getInstance()
    {
        return m_instance;
    }

}
