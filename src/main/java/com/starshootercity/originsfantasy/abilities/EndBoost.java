package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.Ability;
import com.starshootercity.abilities.types.AttributeModifierAbility;
import com.starshootercity.abilities.types.MultiAbility;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.version.MVAttribute;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EndBoost implements VisibleAbility, MultiAbility {
    @Override
    public String description() {
        return "Your natural habitat is the end, so you have more health and are stronger when you are there.";
    }

    @Override
    public String title() {
        return "End Inhabitant";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:end_boost");
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(EndBoost.EndStrength.endStrength, EndBoost.EndHealth.endHealth);
    }

    public static class EndHealth implements AttributeModifierAbility {
        public static EndBoost.EndHealth endHealth = new EndBoost.EndHealth();

        @Override
        public @NotNull Key getKey() {
            return Key.key("fantasyorigins:end_health");
        }

        @Override
        public @NotNull Attribute getAttribute() {
            return MVAttribute.MAX_HEALTH.get();
        }

        @Override
        public double getAmount(Player player) {
            return player.getWorld().getEnvironment() == Environment.THE_END ? 20.0 : 0.0;
        }

        @Override
        public @NotNull Operation getOperation() {
            return Operation.ADD_NUMBER;
        }
    }

    public static class EndStrength implements AttributeModifierAbility {
        public static EndBoost.EndStrength endStrength = new EndBoost.EndStrength();

        @Override
        public @NotNull Attribute getAttribute() {
            return MVAttribute.ATTACK_DAMAGE.get();
        }

        @Override
        public double getAmount(Player player) {
            return player.getWorld().getEnvironment() == Environment.THE_END ? 1.6 : 0.0;
        }

        @Override
        public @NotNull Operation getOperation() {
            return Operation.MULTIPLY_SCALAR_1;
        }

        @Override
        public @NotNull Key getKey() {
            return Key.key("fantasyorigins:end_strength");
        }
    }
}
