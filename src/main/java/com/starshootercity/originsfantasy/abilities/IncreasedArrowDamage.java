package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.OriginSwapper.BooleanPDT;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.originsfantasy.OriginsFantasy;
import com.starshootercity.util.config.ConfigManager.SettingType;
import java.util.Collections;
import net.kyori.adventure.key.Key;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class IncreasedArrowDamage implements VisibleAbility, Listener {
    private final NamespacedKey key = new NamespacedKey(OriginsFantasy.getInstance(), "increased-arrow-damage-key");
    private final String arrowDamageIncrease = "arrow_damage_increase";

    @Override
    public String description() {
        return "All arrows you shoot deal increased damage.";
    }

    @Override
    public String title() {
        return "Piercing Shot";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:increased_arrow_damage");
    }

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        runForAbility(event.getEntity(), player -> event.getProjectile().getPersistentDataContainer().set(key, BooleanPDT.BOOLEAN, true));
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getPersistentDataContainer().has(key)) {
            double v = getConfigOption(OriginsFantasy.getInstance(), arrowDamageIncrease, SettingType.DOUBLE);
            event.setDamage(event.getDamage() + v);
        }
    }

    @Override
    public void initialize(JavaPlugin plugin) {
        registerConfigOption(
            OriginsFantasy.getInstance(), arrowDamageIncrease, Collections.singletonList("Amount to add to arrow damage"), SettingType.DOUBLE, 3.0
        );
    }
}
