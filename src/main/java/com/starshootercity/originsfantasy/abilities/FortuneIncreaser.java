package com.starshootercity.originsfantasy.abilities;

import com.destroystokyo.paper.MaterialTags;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.originsfantasy.OriginsFantasy;
import com.starshootercity.util.config.ConfigManager.SettingType;
import com.starshootercity.version.MVEnchantment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.kyori.adventure.key.Key;
import org.bukkit.GameMode;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class FortuneIncreaser implements VisibleAbility, Listener {
    private final Map<Player, Collection<ItemStack>> blocks = new HashMap<>();
    private final String fortuneIncrease = "fortune_increase";

    @Override
    public String description() {
        return "Your care and mastery in the art of extracting minerals results in a much higher yield from ores than other creatures.";
    }

    @Override
    public String title() {
        return "Careful Miner";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:fortune_increaser");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockDropItem(BlockDropItemEvent event) {
        if (MaterialTags.ORES.isTagged(event.getBlockState().getType())) {
            if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null) {
                runForAbility(event.getPlayer(), player -> {
                    List<ItemStack> items = new ArrayList<>(blocks.getOrDefault(player, List.of()));
                    List<ItemStack> otherItems = new ArrayList<>();

                    for (Item item : event.getItems()) {
                        otherItems.add(item.getItemStack());
                    }

                    event.getItems().clear();

                    for (ItemStack i : otherItems) {
                        for (int itemIndex = 0; itemIndex < items.size(); itemIndex++) {
                            ItemStack item = items.get(itemIndex);
                            if (item.getAmount() != 0) {
                                if (i.getType().equals(item.getType()) && i.getItemMeta().equals(item.getItemMeta())) {
                                    int amount = item.getAmount() - i.getAmount();
                                    item.setAmount(Math.max(0, amount));
                                }

                                items.set(itemIndex, item);
                            }
                        }
                    }

                    items.addAll(otherItems);
                    items.removeIf(itemStack -> itemStack.getAmount() <= 0);

                    for (ItemStack item : items) {
                        event.getItems().add(event.getBlock().getWorld().dropItem(event.getBlock().getLocation().clone().add(0.5, 0.0, 0.5), item));
                    }
                });
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (MaterialTags.ORES.isTagged(event.getBlock().getType())) {
            if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                ItemStack i = event.getPlayer().getInventory().getItemInMainHand().clone();
                if (i.getItemMeta() != null) {
                    i.addUnsafeEnchantment(
                        MVEnchantment.FORTUNE.get(),
                        i.getEnchantmentLevel(MVEnchantment.FORTUNE.get())
                            + getConfigOption(OriginsFantasy.getInstance(), fortuneIncrease, SettingType.INTEGER)
                    );
                    blocks.put(event.getPlayer(), event.getBlock().getDrops(i, event.getPlayer()));
                }
            }
        }
    }

    @Override
    public void initialize(JavaPlugin plugin) {
        registerConfigOption(
            OriginsFantasy.getInstance(), fortuneIncrease, Collections.singletonList("Level to increase Fortune by"), SettingType.INTEGER, 2
        );
    }
}
