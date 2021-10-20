package nix.bedeffect.effects;

import nix.bedeffect.Main;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Bedbug {

    private final Main plugin;

    public Bedbug(Main plugin){this.plugin = plugin;}

    public void display(Location location){

        Location location1 = location.add(0, 1, 0);
        location.getWorld().playSound(location, Sound.SPIDER_DEATH, 1, 1);
        for(int i=0; i<5; i++){run(location1);}

    }

    private void run(Location location){

        Endermite endermite = (Endermite) location.getWorld().spawnEntity(location, EntityType.ENDERMITE);
        endermite.setVelocity(new Vector().setX(getRandom()).setZ(getRandom()).setY(0.1));

        new BukkitRunnable() {
            @Override
            public void run() {
                endermite.remove();
            }
        }.runTaskLater(plugin, 60L);

    }

    private float getRandom(){
        return (float) (Math.random() - Math.random())/100;
    }



}
