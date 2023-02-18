package fr.shall0wer.project.runnables;

import fr.shall0wer.project.IPlayer.IPlayerInfos;
import fr.shall0wer.project.utils.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ModRunnable extends BukkitRunnable {
    @Override
    public void run() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            IPlayerInfos playerInfos = new IPlayerInfos(players.getName());
            if(playerInfos.isModActive()){
                Title.sendActionBar(players, "§9• Mode modération •");
            }
        }

    }
}
