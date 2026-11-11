package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.AttributeModifierAbility;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.version.MVAttribute;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NaturalArmor implements VisibleAbility, AttributeModifierAbility {
    @Override
    public String description() {
        return "Your tough and strong body gives you some natural defense against attacks.";
    }

    @Override
    public String title() {
        return "Natural Armor";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:natural_armor");
    }

    @Override
    public @NotNull Attribute getAttribute() {
        return MVAttribute.ARMOR.get();
    }

    @Override
    public double getAmount(Player player) {
        return 6.0;
    }

    @Override
    public @NotNull Operation getOperation() {
        return Operation.ADD_NUMBER;
    }
}
