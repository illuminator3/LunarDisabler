package me.illuminator3.ld;

import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.nethandler.client.obj.ServerRule;
import com.lunarclient.bukkitapi.serverrule.LunarClientAPIServerRule;
import org.bukkit.plugin.java.JavaPlugin;

public class LunarDisabler
    extends JavaPlugin
{
    public static /* final */ LunarClientAPI LCAPI;

    @Override
    public void onEnable()
    {
        LCAPI = LunarClientAPI.getInstance();

        getServer().getPluginManager().registerEvents(new LDListener(), this);

        disableShaders();
    }

    private void disableShaders()
    {
        LunarClientAPIServerRule.setRule(ServerRule.SHADERS_DISABLED, true);
    }
}