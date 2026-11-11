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

public class HeavyBlow implements VisibleAbility, MultiAbility {
    @Override
    public String description() {
        return "Your attacks are stronger than humans, but you have a longer attack cooldown.";
    }

    @Override
    public String title() {
        return "Heavy Blow";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:heavy_blow");
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(HeavyBlow.IncreasedDamage.increasedDamage, HeavyBlow.IncreasedCooldown.increasedCooldown);
    }

    public static class IncreasedCooldown implements AttributeModifierAbility {
        public static HeavyBlow.IncreasedCooldown increasedCooldown = new HeavyBlow.IncreasedCooldown();

        @Override
        public @NotNull Attribute getAttribute() {
            return MVAttribute.ATTACK_SPEED.get();
        }

        @Override
        public double getAmount(Player player) {
            return -0.4;
        }

        @Override
        public @NotNull Operation getOperation() {
            return Operation.MULTIPLY_SCALAR_1;
        }

        @Override
        public @NotNull Key getKey() {
            return Key.key("fantasyorigins:increased_cooldown");
        }
    }

    public static class IncreasedDamage implements AttributeModifierAbility {
        public static HeavyBlow.IncreasedDamage increasedDamage = new HeavyBlow.IncreasedDamage();

        @Override
        public @NotNull Attribute getAttribute() {
            return MVAttribute.ATTACK_DAMAGE.get();
        }

        @Override
        public double getAmount(Player player) {
            return 1.2;
        }

        @Override
        public @NotNull Operation getOperation() {
            return Operation.MULTIPLY_SCALAR_1;
        }

        @Override
        public @NotNull Key getKey() {
            return Key.key("fantasyorigins:increased_damage");
        }
    }
}
