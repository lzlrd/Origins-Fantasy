package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.AttributeModifierAbility;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.version.MVAttribute;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Elegy implements VisibleAbility, AttributeModifierAbility {
    @Override
    public String description() {
        return "You become stronger when at less than 3 hearts.";
    }

    @Override
    public String title() {
        return "Elegy";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:elegy");
    }

    @Override
    public @NotNull Attribute getAttribute() {
        return MVAttribute.ATTACK_DAMAGE.get();
    }

    @Override
    public @NotNull Operation getOperation() {
        return Operation.MULTIPLY_SCALAR_1;
    }

    @Override
    public double getAmount(Player player) {
        return player.getHealth() <= 6.0 ? 2.0 : 0.0;
    }
}
