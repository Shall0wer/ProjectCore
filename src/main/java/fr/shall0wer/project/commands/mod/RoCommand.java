package fr.shall0wer.project.commands.mod;

import fr.shall0wer.project.IPlayer.IPlayerInfos;
import fr.shall0wer.project.database.ChatReportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        IPlayerInfos playerInfos = new IPlayerInfos(player.getName());
        if(player.hasPermission("helper.ro")){
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("on")){
                    String def = "";
                    if(args[0].equalsIgnoreCase("on")) {def = "true";}
                    if(args[0].equalsIgnoreCase("off")) {def = "false";}
                    playerInfos.setOptions("mod_ro", def);
                    player.sendMessage("§6[Signalements] §fRéception des dossiers " + (args[0].equalsIgnoreCase("on") ? "§aactivée§f." : "§cdésactivée§f."));
                    return true;
                }
            }
            int i = ChatReportManager.getAllReports();
            player.sendMessage("§6[Signalements] §fInformations :" +
                    "\n  §6• §fDossiers en attente : " + (i > 10 ? "§c" + i : "§b" + i) +
                    "\n  §6• §fVotre dossier : " + (ChatReportManager.hasPlayerReport(player.getName()) ? ChatReportManager.getPlayerReportID(player.getName()) : "§7Aucun") +
                    "\n  §6• §fRéception des dossiers : " + (playerInfos.getOptions("mod_ro").equalsIgnoreCase("true") ? "§aOui" : "§cNon"));
            return true;
        }
        return false;
    }
}
