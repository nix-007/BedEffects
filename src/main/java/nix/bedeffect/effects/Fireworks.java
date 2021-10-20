package nix.bedeffect.effects;

import nix.bedeffect.Main;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Fireworks {

    private final Main plugin;
    public Fireworks(Main plugin){this.plugin = plugin;}
    
    public Fireworks display(Location location){

        Firework firework = location.getWorld().spawn(location, Firework.class);
        FireworkMeta meta = firework.getFireworkMeta();
        meta.addEffect(FireworkEffect.builder().withColor(Color.RED).withColor(Color.LIME).with(FireworkEffect.Type.BALL_LARGE).withFlicker().build());
        firework.setFireworkMeta(meta);

        new BukkitRunnable() {
            @Override
            public void run() {
                firework.detonate();
            }
        }.runTaskLater(plugin, 2L);

        return this;
    }
    
    
}
