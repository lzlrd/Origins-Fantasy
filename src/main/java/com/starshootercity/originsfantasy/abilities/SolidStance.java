package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.Ability;
import com.starshootercity.abilities.types.AttributeModifierAbility;
import com.starshootercity.abilities.types.MultiAbility;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.version.MVAttribute;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SolidStance implements VisibleAbility, MultiAbility, AttributeModifierAbility {
    @Override
    public String description() {
        return "You are immune to knockback.";
    }

    @Override
    public String title() {
        return "Solid Stance";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:solid_stance");
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of();
    }

    @Override
    public @NotNull Attribute getAttribute() {
        return MVAttribute.KNOCKBACK_RESISTANCE.get();
    }

    @Override
    public double getAmount(Player player) {
        return 1000.0;
    }

    @Override
    public @NotNull Operation getOperation() {
        return Operation.ADD_NUMBER;
    }
}
