package fr.shall0wer.project.database;

import fr.shall0wer.project.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

public class PlayerInteract {

    public static void setItems(Player player, String hotbar){
        PlayerInventory inv = player.getInventory();
        inv.clear();
        inv.setHelmet(null);
        inv.setChestplate(null);
        inv.setLeggings(null);
        inv.setBoots(null);
        if(hotbar.equalsIgnoreCase("lobby")){
            inv.setItem(0, new ItemBuilder(Material.COMPASS).setName("§6Menu Principal §7(Clic droit)").toItemStack());
            inv.setItem(1, new ItemBuilder(Material.GOLD_INGOT).setName("§eBoutique §7(Clic droit)").toItemStack());
            inv.setItem(7, new ItemBuilder(Material.DIODE).setName("§cParamètres §7(Clic droit)").toItemStack());
            inv.setItem(8, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§dCosmétiques §7(Clic droit)").setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWM5NmJlNzg4NmViN2RmNzU1MjVhMzYzZTVmNTQ5NjI2YzIxMzg4ZjBmZGE5ODhhNmU4YmY0ODdhNTMifX19", "").toItemStack());
            return;
        }
    }
}
