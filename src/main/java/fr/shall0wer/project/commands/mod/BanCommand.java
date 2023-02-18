package fr.shall0wer.project.commands.mod;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.shall0wer.project.IPlayer.IPlayerInfos;
import fr.shall0wer.project.PluginCore;
import fr.shall0wer.project.utils.ConvertTime;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        if(player.hasPermission("resp.ban")){
            if(args.length == 3){
                IPlayerInfos targetInfos = new IPlayerInfos(args[0]);
                if(!targetInfos.hasAccount()){
                    player.sendMessage("§cErreur: Le compte du joueur '" + args[0] + "' n'existe pas où est inaccessible.");
                    return false;
                }
                if(targetInfos.isBanned()){
                    player.sendMessage("§c[SANCTIONS] §fLe joueur est déjà sanctionné.");
                    return false;
                }
                if(args[1].contains("y") || args[1].contains("mo") || args[1].contains("d") || args[1].contains("h") || args[1].contains("m") || args[1].contains("s")){
                    ConvertTime ct = new ConvertTime(args[1]);
                    targetInfos.setupBan(player.getName(), args[2], ct.getTimeMillis() + new ConvertTime(System.currentTimeMillis()).getTimeMillis());
                    int totalBans = targetInfos.getSanction("total_bans");
                    totalBans++;
                    targetInfos.setSanction("total_bans", totalBans);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("KickPlayer");
                    out.writeUTF(args[0]);
                    out.writeUTF("§c§m+----------------------------------------------+§c\n §c\n§cVous êtes banni de SERVEUR !\n §r\n §cRaison : " + targetInfos.getBanOpt("raison") + "\nExpire le : " + new ConvertTime(targetInfos.getBanTime() - 3600000L).getDateTimeFormatted() + "\n §c" + "\n§c§m+----------------------------------------------+");
                    player.sendPluginMessage(PluginCore.getINSTANCE(), "BungeeCord", out.toByteArray());
                    player.sendMessage("§c[SANCTIONS] §fSanction appliquée §7(BAN -" + args[0] + "/" + new ConvertTime(targetInfos.getBanTime() - 3600000L).getDateTimeFormatted() + "/" + args[2] + ")");
                    return true;
                } else {
                    player.sendMessage("§c[SANCTIONS] §fLes temps des sanctions sont : y, mo, d, h, m, s");
                    return false;
                }
            }
            player.sendMessage("§cSyntaxe: /ban (Joueur) (Temps) (Raison)");
        }

        return false;
    }
}
