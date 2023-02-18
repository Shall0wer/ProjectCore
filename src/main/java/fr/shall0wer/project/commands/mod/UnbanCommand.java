package fr.shall0wer.project.commands.mod;

import fr.shall0wer.project.IPlayer.IPlayerInfos;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnbanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!sender.hasPermission("resp.ban")){
            sender.sendMessage("§cErreur: Vous n'avez pas la permission d'utiliser cette commande.");
            return false;
        }

        IPlayerInfos playerInfos = new IPlayerInfos(args[0]);
        if(args.length == 1){
            if(!playerInfos.hasAccount()){
                sender.sendMessage("§cErreur: Le compte du joueur '" + args[0] + "' n'existe pas où est inaccessible.");
                return false;
            }
            if(playerInfos.isBanned()){
                playerInfos.deleteBan(false);
                sender.sendMessage("§c[SANCTIONS] §fLe joueur §b" + args[0] + " §fn'est plus banni.");
                return true;
            } else {
                sender.sendMessage("§c[SANCTIONS] §fCe joueur n'est pas sanctionné.");
                return false;
            }
        }
        sender.sendMessage("§cSyntaxe: /unban (Joueur)");
        return false;
    }
}
