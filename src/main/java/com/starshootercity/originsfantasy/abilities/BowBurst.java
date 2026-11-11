package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.OriginsReborn;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.originsfantasy.OriginsFantasy;
import com.starshootercity.util.config.ConfigManager.SettingType;
import java.util.Collections;
import net.kyori.adventure.key.Key;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class BowBurst implements VisibleAbility, Listener {
    private final String arrowCount = "arrow_count";
    private final String cooldownTime = "cooldown_time";

    @Override
    public String description() {
        return "By casting a spell on any regular arrow, you can instantly shoot %s arrows at once using only one, but this disables your bow for %s seconds.";
    }

    @Override
    public String modifyDescription(String description) {
        return description.formatted(
            getConfigOption(OriginsFantasy.getInstance(), arrowCount, SettingType.INTEGER),
            getConfigOption(OriginsFantasy.getInstance(), cooldownTime, SettingType.INTEGER)
        );
    }

    @Override
    public void initialize(JavaPlugin plugin) {
        registerConfigOption(OriginsFantasy.getInstance(), arrowCount, Collections.singletonList("The number of arrows to fire"), SettingType.INTEGER, 3);
        registerConfigOption(
            OriginsFantasy.getInstance(), cooldownTime, Collections.singletonList("The time in seconds to disable the bow for"), SettingType.INTEGER, 7
        );
    }

    @Override
    public String title() {
        return "Bow Burst";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:bow_burst");
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction().isLeftClick()) {
            if (event.getItem() != null && event.getItem().getType() == Material.BOW) {
                if (event.getPlayer().getCooldown(Material.BOW) <= 0) {
                    runForAbility(event.getPlayer(), player -> {
                        if (player.getInventory().contains(Material.ARROW)) {
                            ItemStack it = null;

                            for (ItemStack item : player.getInventory()) {
                                if (item != null && item.getType() == Material.ARROW) {
                                    item.setAmount(item.getAmount() - 1);
                                    it = item;
                                    break;
                                }
                            }

                            player.setCooldown(Material.BOW, getConfigOption(OriginsFantasy.getInstance(), cooldownTime, SettingType.INTEGER) * 20);
                            int count = getConfigOption(OriginsFantasy.getInstance(), arrowCount, SettingType.INTEGER);
                            int divergence = 0;
                            Arrow arrow = null;

                            for (int i = 0; i < count; i++) {
                                if (arrow != null) {
                                    arrow.setPickupStatus(PickupStatus.CREATIVE_ONLY);
                                }

                                arrow = (Arrow)player.launchProjectile(Arrow.class);
                                OriginsReborn.getMVE().setArrow(arrow, it);
                                OriginsReborn.getMVE().launchArrow(arrow, player, 0.0F, 3.0F, divergence);
                                divergence = 15;
                            }
                        }
                    });
                }
            }
        }
    }
}
