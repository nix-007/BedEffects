package nix.bedeffect.effects;

import nix.bedeffect.Main;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleEffect;

public class Tornado {
    private final Main plugin;
    public Tornado(Main plugin){this.plugin = plugin;}

    public Tornado display(Location location){

        run(location);
        location.getWorld().playSound(location, Sound.GHAST_FIREBALL, 1, 1);
        location.getWorld().playSound(location, Sound.FIRE, 1, 1);

        new BukkitRunnable() {
            @Override
            public void run() {
                Tornado tornado = new Tornado(plugin);
                tornado.run(location);
                location.getWorld().playSound(location, Sound.GHAST_FIREBALL, 1, 1);
                location.getWorld().playSound(location, Sound.FIRE, 1, 1);
            }
        }.runTaskLater(plugin, 20L);

        new BukkitRunnable() {
            @Override
            public void run() {
                Tornado tornado = new Tornado(plugin);
                tornado.run(location);
                location.getWorld().playSound(location, Sound.GHAST_FIREBALL, 1, 1);
                location.getWorld().playSound(location, Sound.FIRE, 1, 1);
            }
        }.runTaskLater(plugin, 40L);

        new BukkitRunnable() {
            @Override
            public void run() {
                Tornado tornado = new Tornado(plugin);
                tornado.run(location);
                location.getWorld().playSound(location, Sound.GHAST_FIREBALL, 1, 1);
                location.getWorld().playSound(location, Sound.FIRE, 1, 1);
                location.getWorld().playSound(location, Sound.FIZZ, 1, 1);
            }
        }.runTaskLater(plugin, 60L);




        return this;
    }

    private void run(Location location){

        float y = (float) location.getY();
        float radius = 0.09f;
        for (double t=0; t < 50; t+=0.05){
            float x = radius * (float) Math.sin(t);
            float z = radius * (float) Math.cos(t);
            Location location1 = new Location(location.getWorld(), location.getX()+x, y, location.getZ()+z);
            ParticleEffect.FIREWORKS_SPARK.display(location1);
            y += 0.005f;
            radius += 0.002f;
        }

        float y2 = (float) location.getY();
        float radius2 = 0.09f;
        for (double t=0; t <50; t+=0.05){
            float z = radius2 * (float) Math.sin(t*2);
            float x = radius2 * (float) Math.cos(t*2);
            Location location1 = new Location(location.getWorld(), location.getX()+x, y2, location.getZ()+z);
            ParticleEffect.FIREWORKS_SPARK.display(location1);
            y2 += 0.005f;
            radius2 += 0.002f;
        }

        smoke(location);

    }

    private void smoke(Location location){

        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                if (count < 31){
                    count++;
                    ParticleEffect.SMOKE_LARGE.display(location.clone().add(new Vector().setX(getRandom()).setZ(getRandom())));
                    ParticleEffect.SMOKE_LARGE.display(location.clone().subtract(new Vector().setX(getRandom()).setZ(getRandom())));
                }
                else {
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);

    }

    private float getRandom(){
        return (float) (Math.random() - Math.random());
    }

}
