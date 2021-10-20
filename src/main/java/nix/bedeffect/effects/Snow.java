package nix.bedeffect.effects;

import nix.bedeffect.Main;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.xenondevs.particle.ParticleBuilder;
import xyz.xenondevs.particle.ParticleEffect;

public class Snow {

    private final Main plugin;
    public Snow(Main plugin){
        this.plugin = plugin;
    }

    public Snow display(Location location){
        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                if (count < 11){
                    count++;
                    new ParticleBuilder(ParticleEffect.SNOWBALL, location).setAmount(100).setOffset(2, 2, 2).display();
                    location.getWorld().playSound(location, Sound.STEP_SNOW, 1, 1);
                }
                else {
                    this.cancel();
                    location.getWorld().playSound(location, Sound.ORB_PICKUP, 1, 1);
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
        return this;
    }
}
