package fr.shall0wer.project.IPlayer;

import fr.shall0wer.project.database.DatabaseManager;
import fr.shall0wer.project.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class IMod {

    public static ArrayList<UUID> mods = new ArrayList<>();

    public static void giveModTools(Player player){
        player.getInventory().clear();
        player.getInventory().setItem(0, new ItemBuilder(Material.INK_SACK, 1, (byte) 10).setName("§fVanish §9§l» §aActivé").toItemStack());
        player.getInventory().setItem(2, new ItemBuilder(Material.WOOD_SWORD).setName("§fKnockback §9§l» §bNiveau I").setInfinityDurability().addEnchant(Enchantment.KNOCKBACK, 1).toItemStack());
        player.getInventory().setItem(3, new ItemBuilder(Material.WOOD_SWORD).setName("§fKnockback §9§l» §bNiveau II").setInfinityDurability().addEnchant(Enchantment.KNOCKBACK, 2).toItemStack());
        player.getInventory().setItem(5, new ItemBuilder(Material.DIAMOND_HOE).setName("§6CPS Tester").toItemStack());
        player.getInventory().setItem(6, new ItemBuilder(Material.PACKED_ICE).setName("§bFreeze").toItemStack());
        player.getInventory().setItem(8, new ItemBuilder(Material.BOOK).setName("§cSanctionner").toItemStack());
    }

    public static ArrayList<String> getMultiaccounts(String pseudo){
        ArrayList<String> accounts = new ArrayList<>();
        IPlayerInfos playerInfos = new IPlayerInfos(pseudo);
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT * FROM players WHERE last_ip = ?");
            ps.setString(1, playerInfos.getOptions("last_ip"));
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                accounts.add(rs.getString("pseudo"));
            }

            rs.close();
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public static void setModModeration(String pseudo, Boolean b) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE players SET mod_isMod = ? WHERE pseudo = ?");
            ps.setBoolean(1, b);
            ps.setString(2, pseudo);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Boolean isInModeration(String pseudo){
        try {
            boolean statut = false;
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT mod_isMod FROM players WHERE pseudo = ?");
            ps.setString(1, pseudo);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                statut = rs.getBoolean("mod_isMod");
            }

            rs.close();
            return statut;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Boolean isModActive(Player player){
        return mods.contains(player.getUniqueId());
    }

}
