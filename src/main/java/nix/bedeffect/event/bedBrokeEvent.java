package nix.bedeffect.event;

import nix.bedeffect.Main;
import nix.bedeffect.effects.EManager;
import nix.bedeffect.effects.Effects;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class bedBrokeEvent implements Listener {

    private final Main plugin;
    private final EManager manager;

    public bedBrokeEvent(Main plugin){
        this.plugin = plugin;
        this.manager = new EManager(plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBedBreak(BlockBreakEvent e){

        if (!e.isCancelled() && e.getBlock().getType() == Material.BED_BLOCK){
            String activeEffect = plugin.getSqLite().getEffect(e.getPlayer());
            switch (activeEffect){
                case "lightning":
                    if (e.getPlayer().hasPermission("bedeffect.lightning")){
                        manager.showEffect(Effects.lightning, e.getBlock().getLocation());
                    }
                    break;
                case "explosion":
                    if (e.getPlayer().hasPermission("bedeffect.explosion")){
                        manager.showEffect(Effects.explosion, e.getBlock().getLocation());
                    }
                    break;
                case "squid":
                    if (e.getPlayer().hasPermission("bedeffect.squid")){
                        manager.showEffect(Effects.squid, e.getBlock().getLocation());
                    }
                    break;
                case "firework":
                    if (e.getPlayer().hasPermission("bedeffect.firework")){
                        manager.showEffect(Effects.firework, e.getBlock().getLocation());
                    }
                    break;
                case "lavapop":
                    if (e.getPlayer().hasPermission("bedeffect.lavapop")){
                        manager.showEffect(Effects.lavapop, e.getBlock().getLocation());
                    }
                    break;
                case "snow":
                    if (e.getPlayer().hasPermission("bedeffect.snow")){
                        manager.showEffect(Effects.snow, e.getBlock().getLocation());
                    }
                    break;
                case "bedbug":
                    if (e.getPlayer().hasPermission("bedeffect.bedbug")){
                        manager.showEffect(Effects.bedbug, e.getBlock().getLocation());
                    }
                    break;
                case "tornado":
                    if (e.getPlayer().hasPermission("bedeffect.tornado")){
                        manager.showEffect(Effects.tornado, e.getBlock().getLocation());
                    }
                    break;
                case "thief":
                    if (e.getPlayer().hasPermission("bedeffect.thief")){
                        manager.showEffect(Effects.thief, e.getBlock().getLocation());
                    }
                    break;
                case "pigexplosion":
                    if (e.getPlayer().hasPermission("bedeffect.pigexplosion")){
                        manager.showEffect(Effects.pigexplosion, e.getBlock().getLocation());
                    }
                    break;
                case "eggpop":
                    if (e.getPlayer().hasPermission("bedeffect.eggpop")){
                        manager.showEffect(Effects.eggpop, e.getBlock().getLocation());
                    }
                    break;

            }
        }

    }


}
