package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.AttributeModifierAbility;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.version.MVAttribute;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Stiff implements VisibleAbility, AttributeModifierAbility {
    @Override
    public @NotNull Attribute getAttribute() {
        return MVAttribute.MOVEMENT_SPEED.get();
    }

    @Override
    public double getAmount(Player player) {
        return -0.35;
    }

    @Override
    public @NotNull Operation getOperation() {
        return Operation.MULTIPLY_SCALAR_1;
    }

    @Override
    public String description() {
        return "Your stone body makes it harder to move, causing you to walk slower.";
    }

    @Override
    public String title() {
        return "Stiff";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:stiff");
    }
}
