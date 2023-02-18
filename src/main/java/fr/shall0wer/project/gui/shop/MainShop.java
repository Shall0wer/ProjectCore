package fr.shall0wer.project.gui.shop;

import fr.shall0wer.project.utils.GuiBuilder;
import fr.shall0wer.project.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class MainShop implements GuiBuilder {
    @Override
    public String name() {
        return "§6§l» §6Boutique";
    }

    @Override
    public int getSize() {
        return 5 * 9;
    }

    @Override
    public void contents(Player player, Inventory inv) {

        inv.setItem(0, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§b§l» §bVotre profil").setLore(" ", "§3Comètes ").toItemStack());

    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) throws IOException, NoSuchMethodException {

    }
}
