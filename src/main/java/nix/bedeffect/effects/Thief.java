package nix.bedeffect.effects;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import nix.bedeffect.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Thief {

    private final Main plugin;
    public Thief(Main plugin){this.plugin = plugin;}

    public void display(Location location){

        Enderman enderman = (Enderman) location.getWorld().spawnEntity(location, EntityType.ENDERMAN);
        disableAI(enderman);

        ItemStack item = new ItemStack(Material.CHEST);
        enderman.setCarriedMaterial(item.getData());

        location.getWorld().playSound(location, Sound.ENDERMAN_IDLE, 1, 1);

        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                if (count < 4){
                    count++;
                    switch (count){
                        case 1:
                        case 3:
                            location.getWorld().playSound(location, Sound.ENDERMAN_TELEPORT, 1, 1);
                            break;
                        case 2:
                            location.getWorld().playSound(location, Sound.ENDERMAN_IDLE, 1, 1);
                            break;
                    }
                }
                else {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);


        new BukkitRunnable() {
            @Override
            public void run() {
                enderman.remove();
            }
        }.runTaskLater(plugin, 60L);

    }

    private void disableAI(Entity entity){
        net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) entity).getHandle();
        NBTTagCompound tag = nmsEntity.getNBTTag();

        if (tag == null){
            tag = new NBTTagCompound();
        }

        nmsEntity.c(tag);
        tag.setInt("Invulnerable", 1);
        tag.setInt("Silent", 1);
        tag.setInt("PortalCooldown", 120);
        tag.setInt("NoAI", 1);
        nmsEntity.f(tag);
    }

}
