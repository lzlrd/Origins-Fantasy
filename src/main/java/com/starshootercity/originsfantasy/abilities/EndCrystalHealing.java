package com.starshootercity.originsfantasy.abilities;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.util.config.ConfigManager.SettingType;
import com.starshootercity.version.MVAttribute;
import java.util.Collections;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class EndCrystalHealing implements VisibleAbility, Listener {
    private int crystalRange;

    @Override
    public String description() {
        return "You can regenerate health from nearby End Crystals.";
    }

    @Override
    public String title() {
        return "Crystal Healer";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:end_crystal_healing");
    }

    @EventHandler
    public void onServerTickEnd(ServerTickEndEvent event) {
        if (event.getTickNumber() % 5 == 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!p.isDead()) {
                    runForAbility(
                        p,
                        player -> {
                            for (Entity entity : player.getWorld()
                                .getNearbyEntitiesByType(
                                    EnderCrystal.class, player.getLocation(), crystalRange + 64, crystalRange + 64, crystalRange + 64
                                )) {
                                if (entity instanceof EnderCrystal crystal) {
                                    if (entity.getLocation().distance(player.getLocation()) > crystalRange) {
                                        if (crystal.getBeamTarget() != null && crystal.getBeamTarget().distance(player.getLocation()) < 12.0) {
                                            crystal.setBeamTarget(null);
                                        }
                                    } else {
                                        crystal.setBeamTarget(player.getLocation().clone().subtract(0.0, 1.0, 0.0));
                                        AttributeInstance maxHealthAttr = player.getAttribute(MVAttribute.MAX_HEALTH.get());
                                        if (maxHealthAttr == null) {
                                            return;
                                        }

                                        double maxHealth = maxHealthAttr.getValue();
                                        player.setHealth(Math.min(maxHealth, player.getHealth() + 1.0));
                                    }
                                }
                            }
                        }
                    );
                }
            }
        }
    }

    @Override
    public void initialize(JavaPlugin plugin) {
        crystalRange = registerConfigOption(
            plugin, "crystal_range", Collections.singletonList("Range the end crystal effect should be"), SettingType.INTEGER, 48
        );
    }
}
