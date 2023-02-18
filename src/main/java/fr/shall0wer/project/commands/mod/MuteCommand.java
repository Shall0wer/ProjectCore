package fr.shall0wer.project.commands.mod;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.shall0wer.project.IPlayer.IPlayerInfos;
import fr.shall0wer.project.PluginCore;
import fr.shall0wer.project.utils.ChatUtils;
import fr.shall0wer.project.utils.ConvertTime;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        // /mute joueur0 temps1 raison2

        Player player = (Player) sender;
        if(player.hasPermission("resp.mute")){
            if(args.length == 3){
                IPlayerInfos targetInfos = new IPlayerInfos(args[0]);
                if(!targetInfos.hasAccount()){
                    player.sendMessage("§cErreur: Le compte du joueur '" + args[0] + "' n'existe pas où est inaccessible.");
                    return false;
                }
                if(targetInfos.isMuted()){
                    player.sendMessage("§c[SANCTIONS] §fLe joueur est déjà sanctionné.");
                    return false;
                }
                if(args[2].contains("y") || args[2].contains("mo") || args[2].contains("d") || args[2].contains("h") || args[2].contains("m") || args[2].contains("s")){
                    ConvertTime ct = new ConvertTime(args[1]);
                    targetInfos.setupMute(player.getName(), args[2], ct.getTimeMillis() + new ConvertTime(System.currentTimeMillis()).getTimeMillis());
                    int totalMutes = targetInfos.getSanction("total_mutes");
                    totalMutes++;
                    targetInfos.setSanction("total_mutes", totalMutes);
                    ByteArrayDataOutput out = ByteStreams.newDataOutput();
                    out.writeUTF("Message");
                    out.writeUTF(args[0]);
                    out.writeUTF("§c§m+-------------------------------------------+§c\n" + ChatUtils.getCenteredText("Vous avez été réduis au silence pour : " + args[2]) + ChatUtils.getCenteredText("Expire le : " + new ConvertTime(targetInfos.getMuteTime() - 3600000L).getDateTimeFormatted()) + "\n§c§m+-------------------------------------------+");
                    player.sendPluginMessage(PluginCore.getINSTANCE(), "BungeeCord", out.toByteArray());
                    player.sendMessage("§c[SANCTIONS] §fSanction appliquée §7(MUTE - " + args[0] + "/" + new ConvertTime(targetInfos.getMuteTime() - 3600000L).getDateTimeFormatted() + "/" + args[2] + ")");
                    return true;
                } else {
                    player.sendMessage("§c[SANCTIONS] §fLes temps des sanctions sont : y, mo, d, h, m, s");
                    return false;
                }
            }
            player.sendMessage("§cSyntaxe: /mute (Joueur) (Temps) (Raison)");
        }

        return false;
    }
}
