package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.VisibleAbility;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.damage.DamageType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.jetbrains.annotations.NotNull;

public class FireResistant implements VisibleAbility, Listener {
    @Override
    public String description() {
        return "Your stone skin makes you immune to fire.";
    }

    @Override
    public String title() {
        return "Heat Resistant";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:fire_resistant");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        runForAbility(event.getEntity(), player -> {
            // Since 26.2 a magma block reports the cause CONTACT, which it shares with cactus and campfires.
            if (List.of(DamageCause.FIRE_TICK, DamageCause.FIRE, DamageCause.LAVA).contains(event.getCause())
                || event.getDamageSource().getDamageType() == DamageType.HOT_FLOOR) {
                event.setCancelled(true);
            }

            player.setFireTicks(0);
        });
    }
}
