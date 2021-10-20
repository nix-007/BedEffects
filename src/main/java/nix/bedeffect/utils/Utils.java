package nix.bedeffect.utils;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;

public class Utils {

    public static String color(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void disableAI(Entity entity){
        net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) entity).getHandle();
        NBTTagCompound tag = nmsEntity.getNBTTag();

        if (tag == null){
            tag = new NBTTagCompound();
        }

        nmsEntity.c(tag);
        tag.setInt("NoAI", 1);
        tag.setInt("Silent", 1);
        tag.setInt("Invulnerable", 1);
        nmsEntity.f(tag);
    }
}
