package nix.bedeffect.commands;

import nix.bedeffect.Main;
import nix.bedeffect.effects.Effects;
import nix.bedeffect.gui.maingui;
import nix.bedeffect.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

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
                        if (sender.hasPermission("bedeffects.admin")){
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
                    else {plugin.getLogger().info("BedEffect vPRE-0.1.1 Author: NIX");}
                    break;

                case "seteffect":
                    sender.sendMessage(Utils.color("&fUsage: &a/bedeffect seteffect <player> <effect>"));
                    break;
                case "clear":
                    sender.sendMessage(Utils.color("&aUsage: &a/bedeffect clear <player>"));
                    break;

            }
        }
        else if (args.length == 2){

            if (args[0].equalsIgnoreCase("clear")  && !(sender instanceof Player)){
                Player p = plugin.getServer().getPlayer(args[1]);
                if (p != null){
                    plugin.getSqLite().update(p, "null");
                    sender.sendMessage(Utils.color("&aUpdated!"));
                }
                else {
                    sender.sendMessage(Utils.color("Player does not exist!"));
                }
            }
        }
        else if (args.length == 3){
            if (Objects.equals(args[0], "seteffect") && !(sender instanceof Player)){
                Player p = plugin.getServer().getPlayer(args[1]);
                String effect = args[2];
                boolean isEffect = false;
                for (Effects effects : Effects.values()){
                    if (effects.toString().equalsIgnoreCase(effect)){
                        isEffect = true;
                    }
                }
                if (isEffect && p!= null){
                    plugin.getSqLite().update(p, effect);
                    sender.sendMessage(Utils.color("&aUpdated!"));
                }
                else {
                    StringBuilder str = new StringBuilder();
                    str.append(Utils.color("&fAvailable effects: "));
                    for (Effects effects : Effects.values()){
                        str.append(Utils.color("&a" + effects.toString() + ", "));
                    }
                    sender.sendMessage(str.toString());
                }
                if (p == null){
                    sender.sendMessage(Utils.color("Player does not exist!"));
                }
            }
        }

        return true;
    }
}
