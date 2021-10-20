package nix.bedeffect.effects;

import nix.bedeffect.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import xyz.xenondevs.particle.ParticleEffect;

public class EggPop {

    private final Main plugin;
    public EggPop(Main plugin){
        this.plugin = plugin;
    }

    public void display(Location location){

        new BukkitRunnable() {int count = 0;@Override public void run() {
            if (count < 7){
                count++;
                location.getWorld().playSound(location, Sound.NOTE_SNARE_DRUM, 1, 1);
            }
            else {this.cancel();}}}.runTaskTimer(plugin, 0L, 1L);
        for (int i=0; i<7; i++){
            run(location);
        }
    }

    private void run(Location location){

        ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        stand.setItemInHand(new ItemStack(Material.EGG));
        stand.setVisible(false);

        new BukkitRunnable() {int count = 0;@Override public void run() {
                if (count < 15){
                    count++;
                    stand.setRightArmPose(new EulerAngle(count*10, count*10, count*10));
                }
                else {this.cancel();}}}.runTaskTimer(plugin, 0L, 1L);

        stand.setVelocity(new Vector().setX(getRandom()).setZ(getRandom()).setY(0.7));
        stand.setRightArmPose(new EulerAngle(90, 50, 150));

        new BukkitRunnable() {
            @Override
            public void run() {
                stand.remove();
                stand.getLocation().getWorld().playSound(stand.getLocation(), Sound.CHICKEN_EGG_POP, 1, 1);
                ParticleEffect.CLOUD.display(stand.getLocation().clone().add(0, 2, 0));
                spawnChickens(stand.getLocation().clone().add(0, 2, 0));
            }
        }.runTaskLater(plugin, 20L);
    }

    private float getRandom(){
        return (float) (Math.random() - Math.random())/2;
    }

    private void spawnChickens(Location location){

        Chicken chicken = (Chicken) location.getWorld().spawnEntity(location, EntityType.CHICKEN);
        chicken.setAdult();
        chicken.setMaxHealth(150);
        chicken.setHealth(100);

        new BukkitRunnable() {
            @Override
            public void run() {
                chicken.remove();
                ParticleEffect.CLOUD.display(chicken.getLocation().clone().add(0, 0.5, 0));
            }
        }.runTaskLater(plugin, 40L);

    }

//    private ItemStack skull(){
//
//        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
//        String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzZmNWY1ZWY5MjJlMTI1Mzg4NTYyYjkzNmJhMmVkMzVkNjY1Yzc1ZGE4OWExYzlmNTdjNTVlNDU5MGFlZjBhMiJ9fX0="; // retrieved from the website you mentioned
//        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
//
//        GameProfile profile = new GameProfile(UUID.randomUUID(), "name"); // this will show "name's Head"
//        profile.getProperties().put("textures", new Property("textures", texture));
//
//        Field profileField;
//
//        try
//        {
//            profileField = skullMeta.getClass().getDeclaredField("profile");
//        }
//        catch (NoSuchFieldException | SecurityException | NullPointerException e)
//        {
//            e.printStackTrace();
//            return skull;
//        }
//
//        profileField.setAccessible(true);
//
//        try
//        {
//            profileField.set(skullMeta, profile);
//        }
//        catch (IllegalArgumentException | IllegalAccessException e)
//        {
//            e.printStackTrace();
//        }
//
//        skull.setItemMeta(skullMeta);
//        return skull;
//    }







}
