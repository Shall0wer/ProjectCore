package fr.shall0wer.project.listeners;

import fr.shall0wer.project.IPlayer.IMod;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        if(IMod.mods.contains(event.getPlayer().getUniqueId())){
            IMod.mods.remove(event.getPlayer().getUniqueId());
        }
    }
}
