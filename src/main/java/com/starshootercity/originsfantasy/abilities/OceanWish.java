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

public class OceanWish implements VisibleAbility, MultiAbility {
    @Override
    public String description() {
        return "Your natural habitat is the ocean, so you're much weaker when you're not in the water.";
    }

    @Override
    public String title() {
        return "Ocean Wish";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:ocean_wish");
    }

    @Override
    public List<Ability> getAbilities() {
        return List.of(OceanWish.LandSlowness.landSlowness, OceanWish.LandHealth.landHealth, OceanWish.LandWeakness.landWeakness);
    }

    public static class LandHealth implements AttributeModifierAbility {
        public static OceanWish.LandHealth landHealth = new OceanWish.LandHealth();

        @Override
        public @NotNull Key getKey() {
            return Key.key("fantasyorigins:land_health");
        }

        @Override
        public @NotNull Attribute getAttribute() {
            return MVAttribute.MAX_HEALTH.get();
        }

        @Override
        public double getAmount(Player player) {
            return player.isInWaterOrRainOrBubbleColumn() ? 0.0 : -12.0;
        }

        @Override
        public @NotNull Operation getOperation() {
            return Operation.ADD_NUMBER;
        }
    }

    public static class LandSlowness implements AttributeModifierAbility {
        public static OceanWish.LandSlowness landSlowness = new OceanWish.LandSlowness();

        @Override
        public @NotNull Key getKey() {
            return Key.key("fantasyorigins:land_slowness");
        }

        @Override
        public @NotNull Attribute getAttribute() {
            return MVAttribute.MOVEMENT_SPEED.get();
        }

        @Override
        public double getAmount(Player player) {
            return player.isInWaterOrRainOrBubbleColumn() ? 0.0 : -0.4;
        }

        @Override
        public @NotNull Operation getOperation() {
            return Operation.MULTIPLY_SCALAR_1;
        }
    }

    public static class LandWeakness implements AttributeModifierAbility {
        public static OceanWish.LandWeakness landWeakness = new OceanWish.LandWeakness();

        @Override
        public @NotNull Attribute getAttribute() {
            return MVAttribute.ATTACK_DAMAGE.get();
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
            return Key.key("fantasyorigins:land_weakness");
        }
    }
}
