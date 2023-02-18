package fr.shall0wer.project.commands.mod;

import fr.shall0wer.project.IPlayer.IPlayerInfos;
import fr.shall0wer.project.PluginCore;
import fr.shall0wer.project.gui.punish.MainPunish;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PunishCommand implements CommandExecutor {

    public static HashMap<UUID, String> punishHeads = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        IPlayerInfos playerInfos = new IPlayerInfos(player.getName());
        if(args.length == 1){
            String pseudo = args[0];
            if(playerInfos.hasAccount()){
                punishHeads.put(player.getUniqueId(), pseudo);
                PluginCore.getGuiManager().open(player, MainPunish.class);
                return true;
            }
            player.sendMessage("§cErreur: Le compte du joueur '" + pseudo + "' n'existe pas où est inaccessible.");
            return false;
        }
        player.sendMessage("§cSyntaxe: /" + s + " (Joueur)");
        return false;
    }
}
