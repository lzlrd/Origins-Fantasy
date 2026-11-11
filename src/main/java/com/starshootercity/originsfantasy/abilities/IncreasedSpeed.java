package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.VisibleAbility;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

public class IncreasedSpeed implements VisibleAbility {
    @Override
    public String description() {
        return "After years of racing, your horse is much faster than any normal horse.";
    }

    @Override
    public String title() {
        return "Dashmaster";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:increased_speed");
    }
}
