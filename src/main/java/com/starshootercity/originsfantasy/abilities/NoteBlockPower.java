package com.starshootercity.originsfantasy.abilities;

import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.version.MVPotionEffectType;
import net.kyori.adventure.key.Key;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.NotePlayEvent;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

public class NoteBlockPower implements VisibleAbility, Listener {
    @Override
    public String description() {
        return "You gain strength and speed when a nearby Note Block is played.";
    }

    @Override
    public String title() {
        return "Musically Attuned";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:note_block_power");
    }

    @EventHandler
    public void onNotePlay(NotePlayEvent event) {
        for (Entity entity : event.getBlock().getLocation().getNearbyEntities(32.0, 32.0, 32.0)) {
            if (entity instanceof Player p) {
                runForAbility(p, player -> {
                    player.addPotionEffect(new PotionEffect(MVPotionEffectType.SPEED.get(), 600, 1));
                    player.addPotionEffect(new PotionEffect(MVPotionEffectType.STRENGTH.get(), 600, 1));
                });
            }
        }
    }
}
