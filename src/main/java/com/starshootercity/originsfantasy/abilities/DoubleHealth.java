package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.AttributeModifierAbility;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.version.MVAttribute;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DoubleHealth implements VisibleAbility, AttributeModifierAbility {
    @Override
    public String description() {
        return "As you're larger than humans, you have more health as your body protects you from damage.";
    }

    @Override
    public String title() {
        return "Double Health";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:double_health");
    }

    @Override
    public @NotNull Attribute getAttribute() {
        return MVAttribute.MAX_HEALTH.get();
    }

    @Override
    public double getAmount(Player player) {
        return 20.0;
    }

    @Override
    public @NotNull Operation getOperation() {
        return Operation.ADD_NUMBER;
    }
}
