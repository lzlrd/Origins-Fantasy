package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.version.MVAttribute;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

public class Leeching implements VisibleAbility, Listener {
    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:leeching");
    }

    @Override
    public String description() {
        return "Upon killing a mob or player, you sap a portion of its health, healing you.";
    }

    @Override
    public String title() {
        return "Leeching";
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        runForAbility(event.getEntity().getKiller(), player -> {
            AttributeInstance mH = player.getAttribute(MVAttribute.MAX_HEALTH.get());
            if (mH != null) {
                double maxHealth = mH.getValue();
                AttributeInstance mobMH = event.getEntity().getAttribute(MVAttribute.MAX_HEALTH.get());
                if (mobMH != null) {
                    double mobMaxHealth = mobMH.getValue();
                    player.setHealth(Math.min(maxHealth, player.getHealth() + mobMaxHealth / 5.0));
                }
            }
        });
    }
}
