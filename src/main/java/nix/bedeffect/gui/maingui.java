package nix.bedeffect.gui;

import abhigya.menu.material.XMaterial;
import abhigya.menu.menu.ItemMenu;
import abhigya.menu.menu.action.ItemClickAction;
import abhigya.menu.menu.item.action.ActionItem;
import abhigya.menu.menu.item.action.ItemAction;
import abhigya.menu.menu.item.action.ItemActionPriority;
import abhigya.menu.menu.size.ItemMenuSize;
import nix.bedeffect.Main;
import nix.bedeffect.utils.Utils;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class maingui {

    private final Main main;
    private ItemMenu menu;
    private final YamlConfiguration effectconfig;
    private final addItems add;


    public maingui(Main main){
        this.main = main;
        this.effectconfig = main.getLoader().getEffectConfig();
        loadGui();
        this.add = new addItems(main, this.menu, this.effectconfig);
    }

    private void loadGui(){
        this.menu = new ItemMenu(effectconfig.getString("title"), ItemMenuSize.fitOf(effectconfig.getInt("size")), null);
        ItemStack filler = new ItemStack(Material.getMaterial(effectconfig.getString("filler")), 1, (short) effectconfig.getInt("data"));
        this.menu.fillToAll(new ActionItem(" ", filler));
        this.menu.registerListener(main);
    }

    public maingui open(Player player){
        addItems(player);
        this.menu.open(player);
       return this;
    }

    private void addItems(Player player){
        add.add("lightning", player);
        add.add("explosion", player);
        add.add("squid", player);
        add.add("firework", player);
        add.add("lavapop", player);
        add.add("snow", player);
        add.add("bedbug", player);
        add.add("tornado", player);
        add.add("thief", player);
        add.add("pigexplosion", player);
        add.add("eggpop", player);
        unequip();
    }

    private void unequip(){
        ActionItem item = new ActionItem(Utils.color("&c&lUnequip"), XMaterial.BARRIER.parseItem(), Utils.color("&7Unequip your current Bed Destroy Effect."));
        item.addAction(new ItemAction() {
            @Override
            public ItemActionPriority getPriority() {return ItemActionPriority.HIGH;}
            @Override
            public void onClick(ItemClickAction action) {
                main.getSqLite().update(action.getPlayer(), "null");
                menu.close(action.getPlayer());
                String msg = Utils.color(main.getLoader().getConfig().getString("unequipped-message"));
                action.getPlayer().sendMessage(msg);
            }
        });
        this.menu.setItem(49, item);
    }











}
