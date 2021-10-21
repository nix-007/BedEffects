package nix.bedeffect.utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import nix.bedeffect.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PapiHook extends PlaceholderExpansion {

    private final Main plugin;

    public PapiHook(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "bedeffect";
    }

    @Override
    public @NotNull String getAuthor() {
        return "NIX";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if(params.equalsIgnoreCase("active")){
            //%bedeffect_active%
            return plugin.getSqLite().getEffect((Player) player);
        }

        return null;
    }
}
