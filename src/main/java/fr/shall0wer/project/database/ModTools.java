package fr.shall0wer.project.database;

import fr.shall0wer.project.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class ModTools {

    public ArrayList<UUID> moderateurs = new ArrayList<UUID>();

    public Boolean isInModeration(Player player){
        return moderateurs.contains(player.getUniqueId());
    }

    public void giveModTools(Player player){
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);

        player.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, (byte) 10).setName("§6Vanish §8§l❙ §aActif").setLore("§7§oClique droit pour désactiver l'invisibilité.").toItemStack());
    }

}
