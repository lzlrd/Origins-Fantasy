package com.starshootercity.originsfantasy.abilities;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import com.starshootercity.OriginsReborn;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.originsfantasy.OriginsFantasy;
import com.starshootercity.util.config.ConfigManager.SettingType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class WaterSensitive implements VisibleAbility, Listener {
    private final String damageAmount = "damage_amount";
    private final Map<Player, Integer> lastWaterDamagedMap = new HashMap<>();

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:water_sensitive");
    }

    @Override
    public String description() {
        return "You are hurt by water as its current drains your power.";
    }

    @Override
    public String title() {
        return "Water Sensitive";
    }

    @EventHandler
    public void onServerTickEnd(ServerTickEndEvent event) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (Bukkit.getCurrentTick() - lastWaterDamagedMap.getOrDefault(p, Bukkit.getCurrentTick() - 20) >= 20) {
                runForAbility(
                    p,
                    player -> {
                        if (player.isInWaterOrRainOrBubbleColumn() || OriginsReborn.getMVE().wasTouchingWater(player)) {
                            OriginsReborn.getMVE()
                                .dealFreezeDamage(player, getConfigOption(OriginsFantasy.getInstance(), damageAmount, SettingType.INTEGER));
                            lastWaterDamagedMap.put(player, Bukkit.getCurrentTick());
                        }
                    }
                );
            }
        }
    }

    @Override
    public void initialize(JavaPlugin plugin) {
        registerConfigOption(
            OriginsFantasy.getInstance(), damageAmount, Collections.singletonList("Amount to damage the player by"), SettingType.INTEGER, 1
        );
    }
}
