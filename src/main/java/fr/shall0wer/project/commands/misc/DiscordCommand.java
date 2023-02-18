package fr.shall0wer.project.commands.misc;

import fr.shall0wer.project.database.DiscordLinkManager;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DiscordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        if(args.length == 0 || args.length > 2){
            player.sendMessage("§9[DISCORD] §fVoici notre discord : §bhttps://discord.gg/7wtd5JRD§f.");
            return true;
        }

        if(args.length == 2 && args[0].equalsIgnoreCase("link")){
            if(DiscordLinkManager.isDiscordAccountLinked(player.getName())){
                player.sendMessage("§9[DISCORD] §fIdentifiant du compte discord lié : " + DiscordLinkManager.getDiscordID(player.getName()) + "\n§7§oFaites '/discord unlink' pour délié le compte discord de votre compte minecraft.");
            } else {
                if(DiscordLinkManager.hasDiscordKey(player.getName())){
                    player.sendMessage("§9[DISCORD] §fPour liez votre compte, faites '§b/discord link " + DiscordLinkManager.getDiscordKey(player.getName()) + "§f' dans un salon discord !");
                    return false;
                }
                UUID key = UUID.randomUUID();
                DiscordLinkManager.setDiscordKey(player.getName(), key.toString());
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
                player.sendMessage("§9[DISCORD] §fPour liez votre compte, faites '§b/discord link " + key + "§f' dans un salon discord !");
            }
            return true;
        }

        if(args[0].equalsIgnoreCase("link")){
            if(DiscordLinkManager.isDiscordAccountLinked(player.getName())){
                player.sendMessage("§9[DISCORD] §fIdentifiant du compte discord lié : " + DiscordLinkManager.getDiscordID(player.getName()) + "\n§7§oFaites '/discord unlink' pour délié le compte discord de votre compte minecraft.");
                return true;
            }
            player.sendMessage("§9[DISCORD] §fPour lier votre compte discord faites : §b/discord link ID_DE_COMPTE_DISCORD\n§7§oPour obtenir l'identifiant de votre compte discord, exécutez la commande '/getid' dans un salon discord !");
            return false;
        }

        if(args[0].equalsIgnoreCase("unlink")){
            if(!DiscordLinkManager.isDiscordAccountLinked(player.getName())){
                player.sendMessage("§9[DISCORD] §fPour liez votre compte, faites '§b/discord link§f'.");
                return false;
            }
            DiscordLinkManager.resetDiscordLink(player.getName());
            player.sendMessage("§9[DISCORD] §fVotre compte discord n'est plus lié à votre compte discord. Pour en lier un nouveau, faites '§b/discord link§f'.");
            return true;
        }

        player.sendMessage("§9[DISCORD] §fVous n'avez pas de compte lié ! Faites §9/discord link §fpour lier votre compte discord.");
        return false;
    }
}
