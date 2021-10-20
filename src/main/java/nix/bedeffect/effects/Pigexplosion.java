package nix.bedeffect.effects;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import nix.bedeffect.Main;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleEffect;

public class Pigexplosion {

    private final Main plugin;
    public Pigexplosion(Main plugin){this.plugin = plugin;}

    public void display(Location location){

        location.getWorld().playSound(location, Sound.FIREWORK_LAUNCH, 1, 1);
        for (int i=0; i<5; i++){
            run(location);
        }

    }

    private void run(Location location){

        Pig pig = (Pig) location.getWorld().spawnEntity(location, EntityType.PIG);
        pig.setBaby();
        pig.setVelocity(new Vector().setX(getRandom()).setZ(getRandom()).setY(0.5));
        disableAI(pig);

        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                if (count < 15){
                    count++;
                    ParticleEffect.FLAME.display(pig.getLocation());
                }
                else {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);

        new BukkitRunnable() {
            @Override
            public void run() {
                pig.remove();
                ParticleEffect.CLOUD.display(pig.getLocation().add(0, 0.5, 0));
            }
        }.runTaskLater(plugin, 60L);

    }

    private float getRandom(){
        return (float) (Math.random() - Math.random())/5;
    }

    private void disableAI(Entity entity){
        net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) entity).getHandle();
        NBTTagCompound tag = nmsEntity.getNBTTag();

        if (tag == null){
            tag = new NBTTagCompound();
        }

        nmsEntity.c(tag);
        tag.setInt("Invulnerable", 1);
        nmsEntity.f(tag);
    }























}
