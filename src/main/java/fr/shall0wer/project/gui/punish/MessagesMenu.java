package fr.shall0wer.project.gui.punish;

import fr.shall0wer.project.PluginCore;
import fr.shall0wer.project.commands.mod.PunishCommand;
import fr.shall0wer.project.utils.GuiBuilder;
import fr.shall0wer.project.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MessagesMenu implements GuiBuilder {
    @Override
    public String name() {
        return "§cSanctions §7(Messages)";
    }

    @Override
    public int getSize() {
        return 6 * 9;
    }

    @Override
    public void contents(Player player, Inventory inv) {

        inv.setItem(0, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner(PunishCommand.punishHeads.get(player.getUniqueId())).setName(PlayerManager.getRank(PunishCommand.punishHeads.get(player.getUniqueId())).replace("&", "§") + PunishCommand.punishHeads.get(player.getUniqueId())).toItemStack());
        inv.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f").toItemStack());
        inv.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f").toItemStack());
        inv.setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f").toItemStack());
        inv.setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f").toItemStack());
        inv.setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f").toItemStack());
        inv.setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f").toItemStack());
        inv.setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE).setName("§f").toItemStack());

        inv.setItem(18, new ItemBuilder(Material.PAPER).setName("§6Catégorie: §fMessages").setLore("§r ", "§fVous êtes déjà dans cette catégorie !").toItemStack());
        inv.setItem(27, new ItemBuilder(Material.GOLDEN_APPLE).setName("§6Catégorie: §fTriche").setLore("§r ", (player.hasPermission("modo.punish") ? "§6§l» §fClique pour afficher la catégorie !" : "§c§l» §cVous n'avez pas la permission d'afficher cette catégorie !")).toItemStack());
        inv.setItem(36, new ItemBuilder(Material.ANVIL).setName("§6Catégorie: §fAutre").setLore("§r ", (player.hasPermission("modo.punish") ? "§6§l» §fClique pour afficher la catégorie !" : "§c§l» §cVous n'avez pas la permission d'afficher cette catégorie !")).toItemStack());
        inv.setItem(45, new ItemBuilder(Material.COMMAND).setName("§6WhoIs").toItemStack());
        inv.setItem(53, new ItemBuilder(Material.ARROW).setName("§cRetour").setLore("§7Vers: Sanctions Menu Principal").toItemStack());

    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) {
        if(inv.getName().equals("§cSanctions §7(Messages)")){
            switch (slot){
                case 18:
                    if(current.getItemMeta().getDisplayName().endsWith("Messages")){
                        break;
                    }
                case 27:
                    if(current.getItemMeta().getDisplayName().endsWith("Triche")){
                        PluginCore.getGuiManager().open(player, CheatMenu.class);
                        break;
                    }
                case 36:
                    if(current.getItemMeta().getDisplayName().endsWith("Autre")){
                        PluginCore.getGuiManager().open(player, OtherMenu.class);
                        break;
                    }
                case 45:
                    player.closeInventory();
                    Bukkit.dispatchCommand(player, "whois " + PunishCommand.punishHeads.get(player.getUniqueId()));
                    break;
                case 53:
                    if(current.getItemMeta().getLore().get(0).endsWith("Sanctions Menu Principal")){
                        PluginCore.getGuiManager().open(player, MainPunish.class);
                        break;
                    }
            }
        }
    }
}
