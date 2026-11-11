package com.starshootercity.originsfantasy.abilities;

import com.destroystokyo.paper.MaterialTags;
import com.starshootercity.abilities.types.TriggerableAbility;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.abilities.types.TriggerableAbility.Condition;
import com.starshootercity.abilities.types.TriggerableAbility.Trigger;
import com.starshootercity.abilities.types.TriggerableAbility.TriggerType;
import com.starshootercity.originsfantasy.OriginsFantasy;
import com.starshootercity.util.config.ConfigManager.SettingType;
import java.util.Collections;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class DragonFireball implements VisibleAbility, Listener, TriggerableAbility {
    private final String cooldownTime = "cooldown_time";
    private final String fireballVelocity = "fireball_velocity";

    @Override
    public String description() {
        return "You can right click whilst holding a sword to launch a dragon's fireball, with a cooldown of %s seconds.";
    }

    @Override
    public String modifyDescription(String description) {
        return description.formatted(getConfigOption(cooldownTime, SettingType.INTEGER));
    }

    @Override
    public String title() {
        return "Dragon's Fireball";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:dragon_fireball");
    }

    @Override
    public void initialize(JavaPlugin plugin) {
        registerConfigOption(
            OriginsFantasy.getInstance(), cooldownTime, Collections.singletonList("The cooldown in seconds between each fireball"), SettingType.INTEGER, 30
        );
        registerConfigOption(
            OriginsFantasy.getInstance(), fireballVelocity, Collections.singletonList("The velocity the fireball should be given"), SettingType.FLOAT, 1.2F
        );
    }

    @Override
    public @NotNull Trigger getTrigger() {
        return Trigger.builder(TriggerType.RIGHT_CLICK, this)
            .addConditions(Condition.HOLDING_SWORD)
            .build(
                event -> {
                    if (event.block() == null || !event.block().getType().isInteractable()) {
                        Player player = event.player();
                        int cooldown;
                        if (MaterialTags.SWORDS.isTagged(player.getInventory().getItemInMainHand().getType())) {
                            cooldown = player.getCooldown(player.getInventory().getItemInMainHand().getType());
                        } else {
                            cooldown = player.getCooldown(player.getInventory().getItemInOffHand().getType());
                        }

                        if (cooldown <= 0) {
                            for (Material material : MaterialTags.SWORDS.getValues()) {
                                player.setCooldown(material, getConfigOption(OriginsFantasy.getInstance(), cooldownTime, SettingType.INTEGER) * 20);
                            }

                            org.bukkit.entity.DragonFireball fireball = (org.bukkit.entity.DragonFireball)player.launchProjectile(
                                org.bukkit.entity.DragonFireball.class
                            );
                            Bukkit.getScheduler()
                                .scheduleSyncDelayedTask(
                                    OriginsFantasy.getInstance(),
                                    () -> fireball.setVelocity(
                                        player.getLocation()
                                            .getDirection()
                                            .multiply(getConfigOption(OriginsFantasy.getInstance(), fireballVelocity, SettingType.FLOAT))
                                    )
                                );
                        }
                    }
                }
            );
    }
}
