package nix.bedeffect.commands;

import nix.bedeffect.Main;
import nix.bedeffect.effects.EggPop;
import nix.bedeffect.gui.maingui;
import nix.bedeffect.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class bedEffect implements CommandExecutor {

    private final Main plugin;

    public bedEffect(Main plugin){this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0){
            if (sender instanceof Player){maingui gui = new maingui(plugin).open((Player) sender);}
            else {sender.sendMessage("This command is only runnable through a player.");}
        }
        else if (args.length == 1){
            switch (args[0]){
                case "reload":
                    if (sender instanceof Player){
                        if (sender.hasPermission("bedeffect.reload")){
                            plugin.getLoader().reload();
                            sender.sendMessage(Utils.color(plugin.getLoader().getConfig().getString("reload-message")));
                        }
                        else {sender.sendMessage(Utils.color(plugin.getLoader().getConfig().getString("no-permission-message")));}
                    }
                    else {plugin.getLoader().reload();}
                    break;
                case "info":
                    if (sender instanceof Player){
                        sender.sendMessage(Utils.color("&fBedeffect: &avPRE-0.1.1 &fAuthor: &aNIX"));
                    }
                    else {plugin.getLogger().info("BedEffect v0.1.1 Author: NIX");}
                    break;

//                case "fire":
//                    if (sender instanceof Player){
//                        new EggPop(plugin).display(((Player) sender).getLocation());
//                        break;
//                    }
            }
        }

        return true;
    }
}
