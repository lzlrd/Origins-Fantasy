package com.starshootercity.originsfantasy.abilities;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import com.starshootercity.OriginsReborn;
import com.starshootercity.OriginSwapper.BooleanPDT;
import com.starshootercity.abilities.types.VisibleAbility;
import com.starshootercity.events.PlayerSwapOriginEvent;
import com.starshootercity.originsfantasy.OriginsFantasy;
import com.starshootercity.util.AbilityRegister;
import com.starshootercity.version.MVAttribute;
import com.starshootercity.version.MVEntityDismountEvent;
import com.starshootercity.version.MVEntityMountEvent;
import net.kyori.adventure.key.Key;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse.Style;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.Event.Result;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class PermanentHorse implements VisibleAbility, Listener {
    private final NamespacedKey key = new NamespacedKey(OriginsFantasy.getInstance(), "mount-key");
    private final NamespacedKey teleportingKey = new NamespacedKey(OriginsFantasy.getInstance(), "teleporting");

    @Override
    public String description() {
        return "You are half horse, half human.";
    }

    @Override
    public String title() {
        return "Half Horse";
    }

    @Override
    public @NotNull Key getKey() {
        return Key.key("fantasyorigins:permanent_horse");
    }

    @EventHandler
    public void onEntityDismount(MVEntityDismountEvent event) {
        if (!event.getEntity().isDead()) {
            runForAbility(event.getEntity(), player -> {
                event.setCancelled(true);
                Entity vehicle = event.getDismounted().getVehicle();
                if (vehicle != null) {
                    vehicle.removePassenger(event.getDismounted());
                }
            });
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (!event.getPlayer().getPersistentDataContainer().has(teleportingKey)) {
            runForAbility(event.getPlayer(), player -> {
                Entity e = player.getVehicle();
                if (e != null) {
                    e.removePassenger(player);
                    player.getPersistentDataContainer().set(teleportingKey, BooleanPDT.BOOLEAN, true);
                    event.setCancelled(true);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(OriginsFantasy.getInstance(), () -> {
                        player.teleport(event.getTo(), event.getCause());
                        e.teleport(event.getTo(), event.getCause());
                        e.addPassenger(player);
                        player.getPersistentDataContainer().remove(teleportingKey);
                    });
                }
            });
        }
    }

    @EventHandler
    public void onEntityMount(MVEntityMountEvent event) {
        runForAbility(
            event.getEntity(),
            player -> {
                if (!((String)event.getMount().getPersistentDataContainer().getOrDefault(key, PersistentDataType.STRING, ""))
                    .equals(event.getEntity().getUniqueId().toString())) {
                    event.setCancelled(true);
                    if (!(event.getMount() instanceof LivingEntity)) {
                        Entity vehicle = player.getVehicle();
                        if (vehicle != null) {
                            event.getMount().addPassenger(vehicle);
                        }
                    }
                }
            }
        );
    }

    @EventHandler
    public void onServerTickEnd(ServerTickEndEvent event) {
        if (event.getTickNumber() % 20 == 0) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!p.isDead()) {
                    runForAbility(p, player -> {
                        if (!player.getPersistentDataContainer().has(teleportingKey)) {
                            if (player.getVehicle() == null) {
                                Horse horse = (Horse)player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
                                AttributeInstance jump = horse.getAttribute(MVAttribute.JUMP_STRENGTH.get());
                                AttributeInstance speed = horse.getAttribute(MVAttribute.MOVEMENT_SPEED.get());
                                if (AbilityRegister.hasAbility(player, Key.key("fantasyorigins:super_jump")) && jump != null) {
                                    jump.setBaseValue(1.0);
                                }

                                if (AbilityRegister.hasAbility(player, Key.key("fantasyorigins:increased_speed")) && speed != null) {
                                    speed.setBaseValue(0.4);
                                }

                                horse.getPersistentDataContainer().set(key, PersistentDataType.STRING, player.getUniqueId().toString());
                                horse.setTamed(true);
                                horse.setStyle(Style.NONE);
                                ItemStack saddle = new ItemStack(Material.SADDLE);
                                ItemMeta meta = saddle.getItemMeta();
                                meta.getPersistentDataContainer().set(key, BooleanPDT.BOOLEAN, true);
                                saddle.setItemMeta(meta);
                                horse.getInventory().setSaddle(saddle);
                                horse.addPassenger(player);
                            }
                        }
                    });
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getCurrentItem() != null) {
            if (event.getCurrentItem().getItemMeta() != null) {
                if (event.getCurrentItem().getItemMeta().getPersistentDataContainer().has(key)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        runForAbility(event.getEntity(), player -> {
            Entity vehicle = player.getVehicle();
            if (vehicle != null && vehicle.getPersistentDataContainer().has(key)) {
                vehicle.remove();
            }
        });
    }

    @EventHandler
    public void onPlayerSwapOrigin(PlayerSwapOriginEvent event) {
        Entity vehicle = event.getPlayer().getVehicle();
        if (vehicle != null && vehicle.getPersistentDataContainer().has(key)) {
            vehicle.remove();
        }
    }

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        runForAbility(event.getPlayer(), player -> event.setUseBed(Result.DENY));
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity().getPersistentDataContainer().has(key)) {
            for (Entity entity : event.getEntity().getPassengers()) {
                if (entity instanceof LivingEntity livingEntity) {
                    OriginsReborn.getMVE().transferDamageEvent(livingEntity, event);
                }
            }

            event.setCancelled(true);
        }
    }
}
