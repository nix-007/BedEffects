package nix.bedeffect.effects;

import nix.bedeffect.Main;
import org.bukkit.Location;

public class EManager {

    private final Main plugin;
    public EManager(Main plugin){this.plugin = plugin;}

    public void showEffect(Effects effect, Location location){
        switch (effect){
            case lightning:
                new Lightning().display(location);
                break;
            case explosion:
                new Explosion().display(location);
                break;
            case squid:
                new SquidBurst(plugin).display(location);
                break;
            case firework:
                new Fireworks(plugin).display(location);
                break;
            case lavapop:
                new Lavadrop().display(location);
                break;
            case snow:
                new Snow(plugin).display(location);
                break;
            case bedbug:
                new Bedbug(plugin).display(location);
                break;
            case tornado:
                new Tornado(plugin).display(location);
                break;
            case thief:
                new Thief(plugin).display(location);
                break;
            case pigexplosion:
                new Pigexplosion(plugin).display(location);
                break;
            case eggpop:
                new EggPop(plugin).display(location);
                break;
        }
    }
}
