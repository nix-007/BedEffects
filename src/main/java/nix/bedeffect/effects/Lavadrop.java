package nix.bedeffect.effects;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;

public class Lavadrop {
    public Lavadrop display(Location location){

        location.getWorld().playSound(location, Sound.ENDERDRAGON_HIT, 1, 1);
        location.getWorld().playSound(location, Sound.FIRE, 1, 1);
        for (int i =0; i<31; i++){
            location.getWorld().playEffect(location, Effect.LAVA_POP, 1);
        }
        location.add(0, 2, 0);
        for (int i =0; i<31; i++){
            location.getWorld().playEffect(location, Effect.LAVA_POP, 1);
        }

        return this;
    }











}
