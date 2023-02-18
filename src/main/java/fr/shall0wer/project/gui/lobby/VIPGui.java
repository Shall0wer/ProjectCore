package fr.shall0wer.project.gui.lobby;

import fr.shall0wer.project.PluginCore;
import fr.shall0wer.project.utils.GuiBuilder;
import fr.shall0wer.project.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class VIPGui implements GuiBuilder {
    @Override
    public String name() {
        return "§e» Espace VIP";
    }

    @Override
    public int getSize() {
        return 9;
    }

    @Override
    public void contents(Player player, Inventory inv) {
        inv.setItem(8, new ItemBuilder(Material.ARROW).setName("§cRetour").setLore(" §7Vers: Menu Principal").toItemStack());

        if(player.hasPermission("céleste.chatoption")){
            if(PlayerManager.getOptions(player.getName(), "opt_chat_icon").equals(":")){
                inv.setItem(2, new ItemBuilder(Material.BOOK).setName("§eSymbole de chat").setLore("§7Permet de modifier le symbole juste après", "§7votre pseudo dans le chat.", " ", "§a▪ §7'§f:§7' §a(Actif)", "§7▪ '§f»§7'", " ", "§e§l» §fClique pour modifier !").toItemStack());
            }
            if(PlayerManager.getOptions(player.getName(), "opt_chat_icon").equals("»")){
                inv.setItem(2, new ItemBuilder(Material.BOOK).setName("§eSymbole de chat").setLore("§7Permet de modifier le symbole juste après", "§7votre pseudo dans le chat.", " ", "§7▪ '§f:§7'", "§a▪ §7'§f»§7' §a(Actif)", " ", "§e§l» §fClique pour modifier !").toItemStack());
            } else {
                inv.setItem(2, new ItemBuilder(Material.BOOK).setName("§eSymbole de chat").setLore("§7Permet de modifier le symbole juste après", "§7votre pseudo dans le chat.", " ", "§7▪ '§f:§7'", "§7▪ §7'§f»§7'", "§6▪ §7'§6" + PlayerManager.getOptions(player.getName(), "opt_chat_icon") + "§7' §a(Actif) §7- §6Spécial §f(Changé sur demande)", " ", "§e§l» §fVous ne pouvez pas modifier votre symbole !").toItemStack());
            }
        } else {
            inv.setItem(2, new ItemBuilder(Material.BOOK).setName("§eSymbole de chat").setLore("§7Permet de modifier le symbole juste après", "§7votre pseudo dans le chat.", " ", "§a▪ §7'§f:§7' §a(Actif)", "§7▪ '§f»§7'", " ", "§e§l» §cIl vous faut le grade §6Céleste §c!").toItemStack());
        }
    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) throws IOException, NoSuchMethodException {

        switch (slot){
            case 8:
                PluginCore.getGuiManager().open(player, MainGui.class);
                break;
            case 2:
                if(current.getItemMeta().getLore().get(3).endsWith("(Actif)")){
                    inv.setItem(2, new ItemBuilder(Material.BOOK).setName("§eSymbole de chat").setLore("§7Permet de modifier le symbole juste après", "§7votre pseudo dans le chat.", " ", "§7▪ '§f:§7'", "§a▪ §7'§f»§7' §a(Actif)", " ", "§e§l» §fClique pour modifier !").toItemStack());
                    PlayerManager.setOptions(player.getName(), "opt_chat-icon", "»");
                    player.sendMessage("§c§lPARAMETRES §8§l❙ §fVotre nouveau symbole de chat est: §b» §f!");
                    break;
                }
                if(current.getItemMeta().getLore().get(4).endsWith("(Actif)")){
                    inv.setItem(2, new ItemBuilder(Material.BOOK).setName("§eSymbole de chat").setLore("§7Permet de modifier le symbole juste après", "§7votre pseudo dans le chat.", " ", "§a▪ §7'§f:§7' §a(Actif)", "§7▪ '§f»§7'", " ", "§e§l» §fClique pour modifier !").toItemStack());
                    PlayerManager.setOptions(player.getName(), "opt_chat-icon", ":");
                    player.sendMessage("§c§lPARAMETRES §8§l❙ §fVotre nouveau symbole de chat est: §b: §f!");
                    break;
                }
                break;
            default: break;
        }

    }
}
