package nix.bedeffect.effects;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;

public class Explosion {

    public Explosion display(Location location){
        for (int i =0; i<51; i++){
            location.getWorld().playEffect(location, Effect.EXPLOSION, 1);
        }
        location.getWorld().playEffect(location, Effect.EXPLOSION_LARGE, 1);
        location.getWorld().playSound(location, Sound.EXPLODE, (float) 0.1, 1);
        return this;
    }
}
