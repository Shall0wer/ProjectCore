package fr.shall0wer.project.gui.lobby;

import fr.shall0wer.project.PluginCore;
import fr.shall0wer.project.database.GiftManager;
import fr.shall0wer.project.gui.shop.MainShop;
import fr.shall0wer.project.utils.GuiBuilder;
import fr.shall0wer.project.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class MainGui extends GiftManager implements GuiBuilder {
    @Override
    public String name() {
        return "§6» Menu Principal";
    }

    @Override
    public int getSize() {
        return 6 * 9;
    }

    @Override
    public void contents(Player player, Inventory inv) {
        inv.setItem(0, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName(" ").toItemStack());
        inv.setItem(1, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName(" ").toItemStack());
        inv.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName(" ").toItemStack());
        inv.setItem(7, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName(" ").toItemStack());
        inv.setItem(8, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName(" ").toItemStack());
        inv.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName(" ").toItemStack());
        inv.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName(" ").toItemStack());
        inv.setItem(45, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName(" ").toItemStack());
        inv.setItem(46, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName(" ").toItemStack());
        inv.setItem(52, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName(" ").toItemStack());
        inv.setItem(53, new ItemBuilder(Material.ARROW).setName("§cFermer").toItemStack());
        inv.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 0).setName(" ").toItemStack());
        if(!getGiftUsers("e3bc4bb4-3f4b-11ec-9bbc-0242ac130002").contains(player.getName())){
            GiftManager gm = new GiftManager();
            inv.setItem(2, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§eCadeau !").setLore("§f§oOh un cadeau !", " ", "§8Contient:", "  §7▪ §fGrade §eAnge §f(à vie)", "  §7▪ §e2500 Comètes", "  §7▪ §6500 Étoiles", " ", "§c§lEDITION LIMITEE ! §c(Plus que " + gm.getUtilisations("e3bc4bb4-3f4b-11ec-9bbc-0242ac130002") + " §ccadeaux restants)", " ", "§e§l» §fClique pour récupérer !").setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmNlZjlhYTE0ZTg4NDc3M2VhYzEzNGE0ZWU4OTcyMDYzZjQ2NmRlNjc4MzYzY2Y3YjFhMjFhODViNyJ9fX0=", "").toItemStack());

        }

        inv.setItem(18, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§eLobby").setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2Y3Y2RlZWZjNmQzN2ZlY2FiNjc2YzU4NGJmNjIwODMyYWFhYzg1Mzc1ZTlmY2JmZjI3MzcyNDkyZDY5ZiJ9fX0=", "").toItemStack());
        inv.setItem(27, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner(player.getName()).setName("§3Profil").setLore("  §b▪ §fClique pour accèder à ton profil !").toItemStack());
        inv.setItem(47, new ItemBuilder(Material.GOLD_INGOT).setName("§eBoutique").toItemStack());
        inv.setItem(48, new ItemBuilder(Material.ENDER_CHEST).setName("§bMysterys Boxs").toItemStack());
        inv.setItem(50, new ItemBuilder(Material.DIODE).setName("§cParamètres").setLore("  §c▪ §fClique pour modifier tes paramètres !").toItemStack());
        inv.setItem(51, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setName("§dCosmétiques").setSkullTexture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWM5NmJlNzg4NmViN2RmNzU1MjVhMzYzZTVmNTQ5NjI2YzIxMzg4ZjBmZGE5ODhhNmU4YmY0ODdhNTMifX19", "").toItemStack());
        inv.setItem(4, new ItemBuilder(Material.PRISMARINE_CRYSTALS).setName("§dEvents").setLore("§7§oIl n'y aucun event de prévu", "§7§opour le moment...").toItemStack());
        inv.setItem(26, new ItemBuilder(Material.DIAMOND).setName("§eEspace VIP").setLore("§fIci, tu peux configurer les options §eVIP §f!", "  §e▪ §fAccessible à partir du rang §eAnge §f» " + (player.hasPermission("ange.vip") ? "§a✔" : "§c✘")).toItemStack());
        inv.setItem(35, new ItemBuilder(Material.BOOK).setName("§cRèglement").setLore("§fClique ici pour obtenir toutes les règles du serveur.", "  §c▪ §7§oNous considérons que tout le monde connait le règlement.").toItemStack());

        inv.setItem(21, new ItemBuilder(Material.IRON_SWORD).hideAttributes().setName("§bSkyWars").toItemStack());
        inv.setItem(22, new ItemBuilder(Material.BED).setName("§eBedWars").toItemStack());
        inv.setItem(23, new ItemBuilder(Material.SMOOTH_BRICK, 1, (byte) 0).setName("§aLabyrinthe").toItemStack());
        inv.setItem(30, new ItemBuilder(Material.BARRIER).setName("§c§oBientôt...").toItemStack());
        inv.setItem(31, new ItemBuilder(Material.BARRIER).setName("§c§oBientôt...").toItemStack());
        inv.setItem(32, new ItemBuilder(Material.BARRIER).setName("§c§oBientôt...").toItemStack());

    }

    @Override
    public void onClick(Player player, Inventory inv, ItemStack current, int slot) throws IOException, NoSuchMethodException {

        if(inv.getName().equals("§6» Menu Principal")){
            switch (slot){
                case 2:
                    if(!getGiftUsers("e3bc4bb4-3f4b-11ec-9bbc-0242ac130002").contains(player.getName())){
                        Bukkit.dispatchCommand(player, "gift e3bc4bb4-3f4b-11ec-9bbc-0242ac130002");
                    }
                    break;
                case 18:
                    player.teleport(new Location(Bukkit.getWorld("Lobby"), 0.5, 50, 0.5, 180, 0));
                    break;
                case 26:
                    if(player.hasPermission("ange.vip")){
                        PluginCore.getGuiManager().open(player, VIPGui.class);
                        break;
                    }
                    player.sendMessage("§e§lVIP §8§l❙ §cVous devez posséder le grade §eAnge §cpour accéder à l'espace VIP.");
                    player.playSound(player.getLocation(), Sound.VILLAGER_NO, 10, 1);
                    break;
                case 30:
                case 31:
                case 32:
                    player.playSound(player.getLocation(), Sound.ANVIL_USE, 10, 1);
                    break;
                case 47:
                    PluginCore.getGuiManager().open(player, MainShop.class);
                    break;
                case 53:
                    player.closeInventory();
                default: break;
            }
        }
    }
}
