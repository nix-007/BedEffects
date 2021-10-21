package nix.bedeffect;

import nix.bedeffect.commands.bedEffect;
import nix.bedeffect.data.Loader;
import nix.bedeffect.data.SQLite;
import nix.bedeffect.event.bedBrokeEvent;
import nix.bedeffect.event.basicListeners;
import nix.bedeffect.utils.PapiHook;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Main extends JavaPlugin {

    private Loader loader;
    private SQLite sqLite;

    @Override
    public void onEnable() {
        try {
            this.loader = new Loader(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getServer().getLogger().info("Configurations loaded!");

        this.sqLite = new SQLite(this);

        getServer().getPluginManager().registerEvents(new bedBrokeEvent(this), this);
        getServer().getPluginManager().registerEvents(new basicListeners(this), this);

        getCommand("bedeffect").setExecutor(new bedEffect(this));

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PapiHook(this).register();
            this.getLogger().info("Hooked in PAPI.");
        }

    }
    @Override
    public void onDisable() {
    }

    public Loader getLoader() {
        return loader;
    }
    public SQLite getSqLite(){return sqLite;}

}
