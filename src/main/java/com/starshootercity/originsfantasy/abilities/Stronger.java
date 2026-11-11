package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.AttributeModifierAbility;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.version.MVAttribute;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Stronger implements VisibleAbility, AttributeModifierAbility {
    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:stronger");
    }

    @Override
    public String description() {
        return "Your vampiric nature makes you stronger than a regular human, making your physical attacks deal far more damage.";
    }

    @Override
    public String title() {
        return "Stronger";
    }

    @Override
    public @NotNull Attribute getAttribute() {
        return MVAttribute.ATTACK_DAMAGE.get();
    }

    @Override
    public double getAmount(Player player) {
        return 1.8;
    }

    @Override
    public @NotNull Operation getOperation() {
        return Operation.MULTIPLY_SCALAR_1;
    }
}
