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

public class OceansGrace implements VisibleAbility, MultiAbility {
    @Override
    public String description() {
        return "You are a part of the water, so you have extra health and deal extra damage when in water or rain.";
    }

    @Override
    public String title() {
        return "Ocean's Grace";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:oceans_grace");
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(OceansGrace.WaterStrength.waterStrength, OceansGrace.WaterHealth.waterHealth);
    }

    public static class WaterHealth implements AttributeModifierAbility {
        public static OceansGrace.WaterHealth waterHealth = new OceansGrace.WaterHealth();

        @Override
        public @NotNull Attribute getAttribute() {
            return MVAttribute.MAX_HEALTH.get();
        }

        @Override
        public double getAmount(Player player) {
            return player.isInWaterOrRainOrBubbleColumn() ? 4.0 : 0.0;
        }

        @Override
        public @NotNull Operation getOperation() {
            return Operation.ADD_NUMBER;
        }

        @Override
        public @NotNull Key getKey() {
            return Key.key("fantasyorigins:water_health");
        }
    }

    public static class WaterStrength implements AttributeModifierAbility {
        public static OceansGrace.WaterStrength waterStrength = new OceansGrace.WaterStrength();

        @Override
        public @NotNull Key getKey() {
            return Key.key("fantasyorigins:water_strength");
        }

        @Override
        public @NotNull Attribute getAttribute() {
            return MVAttribute.ATTACK_DAMAGE.get();
        }

        @Override
        public double getAmount(Player player) {
            return player.isInWaterOrRainOrBubbleColumn() ? 1.4 : 0.0;
        }

        @Override
        public @NotNull Operation getOperation() {
            return Operation.MULTIPLY_SCALAR_1;
        }
    }
}
