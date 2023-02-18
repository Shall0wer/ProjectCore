package fr.shall0wer.project.listeners;

import fr.shall0wer.project.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.UUID;

public class BlockCommands implements Listener {

    private ArrayList<UUID> cooldown = new ArrayList<>();

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event){
        if(cooldown.contains(event.getPlayer().getUniqueId())){
            event.getPlayer().sendMessage("§cVeuillez patienter entre l'exécution des commandes...");
            event.setCancelled(true);
            return;
        }

        if(!event.getPlayer().hasPermission("modo.*")){
            cooldown.add(event.getPlayer().getUniqueId());
            Bukkit.getScheduler().runTaskLater(PluginCore.getINSTANCE(), () -> cooldown.remove(event.getPlayer().getUniqueId()), 15);
        }
        if(event.getMessage().contains(":")){
            if(event.getPlayer().hasPermission("*")){
                return;
            }
            event.setCancelled(true);
            event.getPlayer().sendMessage("§6§lSERVEUR §8§l❙ §fCommande introuvable");
        }
    }
}
