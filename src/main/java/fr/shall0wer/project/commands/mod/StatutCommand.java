package fr.shall0wer.project.commands.mod;

import fr.shall0wer.project.IPlayer.IPlayerInfos;
import fr.shall0wer.project.utils.ConvertTime;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatutCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        IPlayerInfos playerInfos = new IPlayerInfos(player.getName());
        if(player.hasPermission("helper.statut")){
            if(args.length == 1){
                if(playerInfos.hasAccount()){
                    if(playerInfos.isMuted() || playerInfos.isBanned()){
                        player.sendMessage("§c[SANCTIONS] §fSanctions actives de §b" + args[0] + "§f :" +
                                (playerInfos.isMuted() ? "\n  §6• §fMute : " + (playerInfos.getMuteTime() > new ConvertTime(System.currentTimeMillis()).getTimeMillis() ? "§c" + new ConvertTime(playerInfos.getMuteTime() - 3600000L).getDateTimeFormatted() + " §7(" + playerInfos.getMuteOpt("banner") + ")" : "§7Expiré (" + playerInfos.getMuteOpt("raison") + ")") : "") +
                                (playerInfos.isBanned() ? "\n  §6• §fBan : " + (playerInfos.getBanTime() > new ConvertTime(System.currentTimeMillis()).getTimeMillis() ? "§c" + new ConvertTime(playerInfos.getBanTime() - 3600000L).getDateTimeFormatted() + " §7(" + playerInfos.getMuteOpt("banner") + ")" : "§7Expiré (" + playerInfos.getMuteOpt("raison") + ")") : ""));
                        return true;
                    }
                    player.sendMessage("§c[SANCTIONS] §fLe joueur §b" + args[0] + "§f n'a aucune sanction active.");
                    return false;
                }
                player.sendMessage("§cErreur: Le compte du joueur '" + args[0] + "' n'existe pas où est inaccessible.");
                return false;
            }
            player.sendMessage("§cSyntaxe: /statut (Joueur)");
            return false;
        }
        player.sendMessage("§cErreur: Vous n'avez pas la permission d'exécuter cette commande.");
        return false;
    }
}
