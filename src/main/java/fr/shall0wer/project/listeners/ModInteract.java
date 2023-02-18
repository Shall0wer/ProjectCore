package fr.shall0wer.project.listeners;

import fr.shall0wer.project.database.ModTools;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ModInteract implements Listener {

    ModTools mt = new ModTools();

    @EventHandler
    public void onDamageMod(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if(mt.moderateurs.contains(player.getUniqueId())){
                player.sendMessage("§c§lMOD §8§l❙ §f" + event.getDamager().getName() + " : " + event.getDamage() + " §7(" + player.getLocation().distance(event.getDamager().getLocation()) + "m)");
            }
        }
    }
}
