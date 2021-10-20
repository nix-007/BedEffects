package nix.bedeffect.data;

import nix.bedeffect.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Loader {

    private final Main plugin;
    private YamlConfiguration config;
    private YamlConfiguration effectConfig;

    public Loader(Main plugin) throws IOException {
        this.plugin = plugin;
        create();
        load();
    }

    private void create() throws IOException {
        if (!new File(plugin.getDataFolder(), "config.yml").exists()){plugin.saveResource("config.yml", false);}
        if (!new File(plugin.getDataFolder(), "effects.yml").exists()){plugin.saveResource("effects.yml", false);}
        if (!new File(plugin.getDataFolder() + File.separator + "data").exists()){
            Files.createDirectory(Paths.get(plugin.getDataFolder() + File.separator + "data"));
        }
    }

    private void load(){
        File file = new File(plugin.getDataFolder(), "config.yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        File file2 = new File(plugin.getDataFolder(), "effects.yml");
        this.effectConfig = YamlConfiguration.loadConfiguration(file2);
    }

    public YamlConfiguration getConfig(){
        return this.config;
    }

    public YamlConfiguration getEffectConfig(){
        return this.effectConfig;
    }

    public void reload(){
        load();
    }

}
