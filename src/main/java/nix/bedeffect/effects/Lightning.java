package nix.bedeffect.effects;

import org.bukkit.Location;

public class Lightning {
    public Lightning display(Location location){
        location.getWorld().strikeLightningEffect(location);
        return this;
    }
}
