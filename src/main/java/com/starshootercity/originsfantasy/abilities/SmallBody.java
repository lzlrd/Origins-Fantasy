package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.AttributeModifierAbility;
import com.starshootercity.abilities.types.VisibleAbility;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SmallBody implements VisibleAbility, AttributeModifierAbility {
    private final Attribute scale;

    public SmallBody(Attribute scale) {
        this.scale = scale;
    }

    @Override
    public String description() {
        return "You're only one block tall, half as tall as a regular human.";
    }

    @Override
    public String title() {
        return "Small Body";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:small_body");
    }

    @Override
    public @NotNull Attribute getAttribute() {
        return scale;
    }

    @Override
    public double getAmount(Player player) {
        return -0.5;
    }

    @Override
    public @NotNull Operation getOperation() {
        return Operation.MULTIPLY_SCALAR_1;
    }
}
