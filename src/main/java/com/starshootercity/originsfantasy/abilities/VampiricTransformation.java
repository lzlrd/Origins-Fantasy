package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.OriginSwapper;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.events.PlayerSwapOriginEvent.SwapReason;
import com.starshootercity.originsfantasy.OriginsFantasy;
import com.starshootercity.util.config.ConfigManager.SettingType;
import java.util.Collections;
import java.util.Random;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class VampiricTransformation implements VisibleAbility, Listener {
    private final Random random = new Random();
    private final String transformChance = "transformation_chance";

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:vampiric_transformation");
    }

    @Override
    public String description() {
        return "Players you kill come back as Zombified.";
    }

    @Override
    public String title() {
        return "Infection";
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (event.getEntity().getKiller() == null) {
                return;
            }

            runForAbility(
                event.getEntity().getKiller(),
                pl -> {
                    if (random.nextDouble()
                        <= (getConfigOption(OriginsFantasy.getInstance(), transformChance, SettingType.FLOAT)).floatValue()) {
                        OriginSwapper.setOrigin(player, OriginSwapper.getOrigin(pl, "origin"), SwapReason.DIED, false, "origin");
                        player.sendMessage(Component.text("You have been zombified!").color(NamedTextColor.RED));
                    }
                }
            );
        }
    }

    @Override
    public void initialize(JavaPlugin plugin) {
        registerConfigOption(
            OriginsFantasy.getInstance(),
            transformChance,
            Collections.singletonList("Chance (between 0 and 1) that a player killed by a Zombified player becomes one"),
            SettingType.FLOAT,
            1.0F
        );
    }
}
