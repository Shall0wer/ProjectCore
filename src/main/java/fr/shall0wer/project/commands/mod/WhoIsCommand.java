package fr.shall0wer.project.commands.mod;

import fr.shall0wer.project.IPlayer.IPlayerInfos;
import fr.shall0wer.project.database.DiscordLinkManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhoIsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!sender.hasPermission("modo.whois")){
            sender.sendMessage("§cErreur: Vous n'avez pas la permission d'utiliser cete commande.");
            return false;
        }

        Player player = (Player) sender;
        IPlayerInfos playerInfos = new IPlayerInfos(args[0]);
        if(args.length == 1){
            if(!playerInfos.hasAccount()){
                sender.sendMessage("§cErreur: Le compte du joueur '" + args[0] + "' n'existe pas où est inaccessible.");
                return false;
            }
            StringBuilder str = new StringBuilder();
            boolean effect = false;
            if(playerInfos.getMultiaccounts().size() > 1){
                for (String multiaccount : playerInfos.getMultiaccounts()) {
                    str.append("§e").append(multiaccount).append(", ");
                }
                effect = true;
            }
            if(effect){
                str.setLength(str.length() - 2);
            }
            player.sendMessage("§c[WHOIS] §fInformations sur le joueur §b" + args[0] + "§f :" +
                    "\n  §6• §bUUID §f» " + playerInfos.getOptions("uuid") +
                    "\n  §6• §bGrade §f» " + playerInfos.getRank().replace("&", "§") +
                    "\n  §6• §bDernière IP §f» " + (sender.hasPermission("*") ? playerInfos.getOptions("last_ip") + " §7(" + str + "§7)" : "§cVous n'avez pas la permission de voir l'IP.") +
                    "\n  §6• §3Comètes §f» " + playerInfos.getCometes() +
                    "\n  §6• §6Etoiles §f» " + playerInfos.getEtoiles() +
                    "\n  §6• §9Compte discord §f» " + (DiscordLinkManager.isDiscordAccountLinked(args[0]) ? DiscordLinkManager.getDiscordID(args[0]) : "§cPas lié") +
                    "\n  §6• §cMutes §f» " + playerInfos.getOptions("total_mutes") +
                    "\n  §6• §cBans §f» " + playerInfos.getOptions("total_bans"));
            return true;
        }
        StringBuilder sb = new StringBuilder();
        for (Player players : Bukkit.getOnlinePlayers()) {
            IPlayerInfos playersInfos = new IPlayerInfos(players.getName());
            sb.append("\n  §f• ").append(playerInfos.getRank().replace("&", "§")).append(players.getName()).append(" §f").append(playersInfos.isModActive() ? "§7(§9MOD§7)" : "");
        }
        sender.sendMessage("§c[WhoIs] §fListe des joueurs : " + sb);
        return false;
    }
}