package fr.shall0wer.project.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.shall0wer.project.PluginCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        if(player.hasPermission("resp.broadcast")){
            if(args.length >= 1){
                StringBuilder sb = new StringBuilder();
                for(String bc : args){
                    sb.append(" " + bc);
                }
                ByteArrayDataOutput out = ByteStreams.newDataOutput();
                out.writeUTF("Message");
                out.writeUTF("ALL");
                out.writeUTF("§4[§lANNONCE§4] " + PlayerManager.getRank(player.getName()).replace("&", "§") + player.getName() + " §f" + PlayerManager.getOptions(player.getName(), "opt_chat_icon") + sb);
                player.sendPluginMessage(PluginCore.getINSTANCE(), "BungeeCord", out.toByteArray());
                return false;
            }
            player.sendMessage("§cSyntaxe: /broadcast (Message)");
            return false;
        }
        player.sendMessage("§cErreur: Vous n'avez pas la permission d'utiliser cette commande.");
        return false;
    }
}
