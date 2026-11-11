package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.VisibleAbility;
import net.kyori.adventure.key.Key;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class MagicResistance implements VisibleAbility, Listener {
    @Override
    public String description() {
        return "You have an immunity to poison and harming potion effects.";
    }

    @Override
    public String title() {
        return "Iron Stomach";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:magic_resistance");
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (!(event instanceof EntityDamageByEntityEvent ev && !(ev.getDamager() instanceof Projectile))) {
            runForAbility(event.getEntity(), player -> {
                if (event.getCause().equals(DamageCause.MAGIC)) {
                    event.setCancelled(true);
                }
            });
        }
    }

    @EventHandler
    public void onEntityPotionEffect(EntityPotionEffectEvent event) {
        if (event.getNewEffect() != null) {
            runForAbility(event.getEntity(), player -> {
                if (event.getNewEffect().getType().equals(PotionEffectType.POISON)) {
                    event.setCancelled(true);
                }
            });
        }
    }
}
