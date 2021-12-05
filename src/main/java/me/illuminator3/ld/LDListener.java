package me.illuminator3.ld;

import com.lunarclient.bukkitapi.nethandler.client.LCPacketGhost;
import com.lunarclient.bukkitapi.nethandler.client.LCPacketModSettings;
import com.lunarclient.bukkitapi.nethandler.client.LCPacketStaffModState;
import com.lunarclient.bukkitapi.nethandler.client.obj.ModSettings;
import com.lunarclient.bukkitapi.serverrule.LunarClientAPIServerRule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LDListener
    implements Listener
{
    private static final String[] MOD_IDS = {
         "one_seven_visuals",
         "fps",
         "cps",
         "skyblockAddons",
         "toggleSneak",
         "hypixel_mod",
         "armorstatus",
         "keystrokes",
         "coords",
         "crosshair",
         "potioneffects",
         "directionhud",
         "waypoints",
         "scoreboard",
         "potion_counter",
         "ping",
         "motionBlur",
         "chat",
         "scrollable_tooltips",
         "uhc_overlay",
         "particleMultiplierMod",
         "cooldowns",
         "worldedit_cui",
         "clock",
         "stopwatch",
         "memory",
         "combo",
         "range",
         "time_changer",
         "serverAddressMod",
         "saturation",
         "itemPhysic",
         "itemTrackerChild",
         "shinyPots",
         "block_outline",
         "screenshot",
         "fov_mod",
         "textHotKey",
         "netgraph",
         "mumble-link",
         "bossbar",
         "freelook",
         "replaymod",
         "nickHider"
    }, STAFF_MOD_IDS = {
        "xray",
        "bunny_hop",
        "name_tags",
        "no_clip"
    };
    private static final ModSettings.ModSetting DISABLED_MOD_SETTING = new ModSettings.ModSetting(false, Map.of());
    private static final ModSettings MOD_SETTINGS;

    static {
        ModSettings settings = new ModSettings();

        Arrays.stream(MOD_IDS).forEach(id -> settings.addModSetting(id, DISABLED_MOD_SETTING));

        MOD_SETTINGS = settings;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();

        LunarClientAPIServerRule.sendServerRule(player);
        LunarDisabler.LCAPI.sendPacket(player, new LCPacketModSettings(MOD_SETTINGS));

        Arrays.stream(STAFF_MOD_IDS).forEach(id -> LunarDisabler.LCAPI.sendPacket(player, new LCPacketStaffModState(id, true)));
    }
}