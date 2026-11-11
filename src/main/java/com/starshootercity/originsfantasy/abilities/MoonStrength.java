package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.AttributeModifierAbility;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.version.MVAttribute;
import io.papermc.paper.world.MoonPhase;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MoonStrength implements VisibleAbility, AttributeModifierAbility {
    @Override
    public String description() {
        return "You're a worshipper of the moon, so on nights with a full moon you're stronger than normal.";
    }

    @Override
    public String title() {
        return "Moon's Blessing";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:moon_strength");
    }

    @Override
    public @NotNull Attribute getAttribute() {
        return MVAttribute.ATTACK_DAMAGE.get();
    }

    @Override
    public double getAmount(Player player) {
        return !player.getWorld().isDayTime() && player.getWorld().getMoonPhase() == MoonPhase.FULL_MOON ? 2.4 : 0.0;
    }

    @Override
    public @NotNull Operation getOperation() {
        return Operation.MULTIPLY_SCALAR_1;
    }
}
