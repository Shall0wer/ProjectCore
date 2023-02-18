package fr.shall0wer.project.listeners;

import fr.shall0wer.project.IPlayer.IPlayerInfos;
import fr.shall0wer.project.PluginCore;
import fr.shall0wer.project.utils.ChatUtils;
import fr.shall0wer.project.utils.ConvertTime;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class PlayerChatListener implements Listener {

    private List<UUID> cooldown = new ArrayList<>();

    @EventHandler(priority = EventPriority.HIGHEST) @Deprecated
    public void onChatPrefix(PlayerChatEvent event){
        if(!PluginCore.getINSTANCE().getConfig().getBoolean("Chat")){
            return;
        }

        Player player = event.getPlayer();
        IPlayerInfos playerInfos = new IPlayerInfos(player.getName());
        event.setCancelled(true);

        if(playerInfos.isMuted()){
            if(cooldown.contains(player.getUniqueId())){
                event.setCancelled(true);
                return;
            }
            cooldown.add(event.getPlayer().getUniqueId());
            Bukkit.getScheduler().runTaskLater(PluginCore.getINSTANCE(), () -> cooldown.remove(player.getUniqueId()), 20 * 3);
            if(playerInfos.getMuteTime() > new ConvertTime(System.currentTimeMillis()).getTimeMillis()){ // si fin > l'heure actuelle
                event.setCancelled(true);
                player.sendMessage("§c§m+-------------------------------------------+§c\n" + ChatUtils.getCenteredText("Vous avez été réduis au silence pour : " + playerInfos.getMuteOpt("raison")) + ChatUtils.getCenteredText("Expire le : " + new ConvertTime(playerInfos.getMuteTime() - 3600000L).getDateTimeFormatted()) + "\n§c§m+-------------------------------------------+");
                return;
            } else {
                if(cooldown.contains(event.getPlayer().getUniqueId())){
                    cooldown.remove(event.getPlayer().getUniqueId());
                }
                playerInfos.deleteMute(true);
            }
        }

        if(cooldown.contains(player.getUniqueId())){
            player.sendMessage("§cVeuillez patienter avant de renvoyer un message.");
            return;
        }

        StringBuilder str = new StringBuilder();
        if(playerInfos.isModActive()){
            str.append("§b").append(event.getMessage());
        }
        for(Player players : Bukkit.getOnlinePlayers()){
            if(playerInfos.isModActive()){
                players.sendMessage(playerInfos.getRank().replace("&", "§").replace("Aucun", "") + player.getName() + "§f " + playerInfos.getOptions("opt_chat_icon") + " " + str);
                return;
            }
            players.sendMessage(playerInfos.getRank().replace("&", "§").replace("Aucun", "") + player.getName() + "§f " + playerInfos.getOptions("opt_chat_icon") + " " + (player.hasPermission("gardien.chat") ? "§f" : "§7") + event.getMessage());
        }
        if(!player.hasPermission("staff.bypass")){
            cooldown.add(event.getPlayer().getUniqueId());
            Bukkit.getScheduler().runTaskLater(PluginCore.getINSTANCE(), () -> cooldown.remove(player.getUniqueId()), 20 * 2);
        }
    }
}
