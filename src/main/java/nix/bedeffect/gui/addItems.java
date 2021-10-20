package nix.bedeffect.gui;

import abhigya.menu.StringUtils;
import abhigya.menu.itemstack.ItemMetaBuilder;
import abhigya.menu.material.MaterialUtils;
import abhigya.menu.menu.ItemMenu;
import abhigya.menu.menu.action.ItemClickAction;
import abhigya.menu.menu.item.action.ActionItem;
import abhigya.menu.menu.item.action.ItemAction;
import abhigya.menu.menu.item.action.ItemActionPriority;
import nix.bedeffect.Main;
import nix.bedeffect.utils.Utils;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class addItems {

    private final Main plugin;
    private final ItemMenu menu;
    private final YamlConfiguration config;

    public addItems(Main plugin, ItemMenu menu, YamlConfiguration config){
        this.plugin = plugin;
        this.menu = menu;
        this.config = config;
    }

    public void add(String effect, Player player){

        if (config.getBoolean("effects." + effect + ".enable")){
            ItemStack item;
            int slot = config.getInt("effects." + effect + ".slot");
            String perm = "bedeffects." + effect;
            String name = Utils.color(config.getString("effects." + effect + ".name"));

            if (!player.hasPermission(perm)){item = getNot();}
            else {
                item = getYes(effect);
                if (plugin.getSqLite().getEffect(player).equalsIgnoreCase(effect)){
                    item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
                    item.getItemMeta().addItemFlags(ItemFlag.HIDE_ENCHANTS);
                }
            }
            ActionItem item1 = new ActionItem(item){
                @Override
                public ItemStack getDisplayIcon(){
                    if (this.getIcon().getType() == Material.AIR){
                        return this.icon;
                    }
                    else {
                        ItemMetaBuilder builder = new ItemMetaBuilder(MaterialUtils.getRightMaterial(this.getIcon()));
                        builder.withDisplayName(StringUtils.translateAlternateColorCodes(this.getName())).withLore(this.getLore());
                        this.getIcon().getEnchantments().forEach((e, i) -> builder.withEnchantment(e, i, true));
                        builder.withItemFlags(ItemFlag.HIDE_ENCHANTS);
                        return builder.applyTo(this.getIcon().clone());
                    }
                }
            };
            item1.setName(name);
            if (player.hasPermission(perm)){
                item1.setLore(getColoredLore(config.getStringList("effects." + effect + ".lore")));}
            else {item1.setLore(getColoredLore(config.getStringList("not-available-lore")));}

            if (player.hasPermission(perm)) {
                item1.addAction(new ItemAction() {
                    @Override
                    public ItemActionPriority getPriority() {return ItemActionPriority.HIGH;}
                    @Override
                    public void onClick(ItemClickAction action) {
                        plugin.getSqLite().update(action.getPlayer(), effect);
                        menu.close(player);
                        String msg = Utils.color(plugin.getLoader().getConfig().getString("equipped-message"));
                        msg = msg.replace("{effect}", effect);
                       action.getPlayer().sendMessage(msg);
                    }
                });
            }
            menu.setItem(slot, item1);
        }

    }

    private ItemStack getNot(){
        Material not = Material.getMaterial(config.getString("not-available-item.material"));
        return new ItemStack(not, 1, (short) config.getInt("not-available-item.data"));
    }
    private ItemStack getYes(String effect){
        return new ItemStack(Material.getMaterial(config.getString("effects."+ effect +".material")), config.getInt("effects."+ effect +".amount"), (short) config.getInt("effects."+ effect +".data"));
    }
    private List<String> getColoredLore(List<String> list){
        List<String> newList = new ArrayList<>();
        for (String s : list){
            newList.add(Utils.color(s));
        }
        return newList;
    }


}
