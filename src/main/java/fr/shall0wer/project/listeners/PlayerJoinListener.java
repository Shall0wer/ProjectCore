package fr.shall0wer.project.listeners;

import fr.shall0wer.project.IPlayer.IMod;
import fr.shall0wer.project.IPlayer.IPlayerInfos;
import fr.shall0wer.project.database.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        IPlayerInfos playerInfos = new IPlayerInfos(player.getName());
        if(playerInfos.isInModeration() && !IMod.mods.contains(player.getUniqueId())){
            IMod.mods.add(player.getUniqueId());
        }

        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE players SET last_ip = ? WHERE pseudo = ?");
            ps.setString(1, player.getAddress().getAddress().toString());
            ps.setString(2, player.getName());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(playerInfos.isModActive()){
            player.setGameMode(GameMode.SPECTATOR);
            player.getInventory().clear();
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.hidePlayer(player);
            }
        }
    }
}
