package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.VisibleAbility;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;

public class SuperJump implements VisibleAbility {
    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:super_jump");
    }

    @Override
    public String description() {
        return "Your horse has trained its whole life, so it jumps much higher than a regular horse.";
    }

    @Override
    public String title() {
        return "Powerful Jump";
    }
}
