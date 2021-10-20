package nix.bedeffect.event;

import nix.bedeffect.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class basicListeners implements Listener {

    private final Main plugin;

    public basicListeners(Main plugin){this.plugin = plugin;}

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e){
        String username = e.getPlayer().getName();
        plugin.getSqLite().insert(username, "null");
    }

    @EventHandler
    public void onEndermiteDamage(EntityDamageByEntityEvent e){
        if (e.getDamager().getType() == EntityType.ENDERMITE || e.getDamager().getType() == EntityType.ENDERMAN){
            e.setCancelled(true);
        }
    }

}

