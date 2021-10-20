package nix.bedeffect.effects;

import nix.bedeffect.Main;
import nix.bedeffect.utils.Utils;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Squid;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.xenondevs.particle.ParticleEffect;

public class SquidBurst {

    private final Main plugin;

    public SquidBurst(Main plugin){this.plugin = plugin;}

    public SquidBurst display(Location location){

        Squid squid = (Squid) location.getWorld().spawnEntity(location, EntityType.SQUID);
        Utils.disableAI(squid);

        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                if (count < 31){
                    count++;
                    location.getWorld().playSound(location, Sound.PISTON_EXTEND, (float) 0.1, 1);
                    squid.teleport(location.add(0, 0.5, 0));
                    Location location1 = squid.getLocation().subtract(0, 5, 0);
                    ParticleEffect.FLAME.display(location1);
                }
                else {
                    squid.remove();
                    this.cancel();
                    explosion(location.subtract(0, 3, 0));
                    location.getWorld().playSound(location, Sound.ENDERMAN_DEATH, 1, 1);
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);

        return this;

    }

    private void explosion(Location location){
        Firework firework = location.getWorld().spawn(location, Firework.class);
        FireworkMeta meta = firework.getFireworkMeta();
        meta.addEffect(FireworkEffect.builder().withColor(Color.BLACK).with(FireworkEffect.Type.BALL_LARGE).withFlicker().build());
        firework.setFireworkMeta(meta);

        new BukkitRunnable() {
            @Override
            public void run() {
                firework.detonate();
            }
        }.runTaskLater(plugin, 2L);
    }





















}
