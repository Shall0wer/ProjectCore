package fr.shall0wer.project.commands;

import fr.shall0wer.project.database.GiftManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class GiftCommand extends GiftManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        if(s.equalsIgnoreCase("admingift")){ //admingift exist GIFT | info GIFT
            if(player.hasPermission("admin.gift")){
                player.sendMessage("§cErreur: Commande en cours de création...");
                return true;
            } else {
                player.sendMessage("§6§lSERVEUR §8§l❙ §fCommande introuvable.");
                return false;
            }
        }

        if(s.equalsIgnoreCase("gift")){
            if(args.length == 1){
                if(existGift(args[0])){
                    if(getGiftUsers(args[0]).contains(player.getName())){
                        player.sendMessage("§e§lGIFTS §8§l❙ §fVous avez §cdéjà §futilisé ce cadeau.");
                        return false;
                    }
                    if(getSpecificPlayer(args[0])){
                        if(getPlayerWhichIsSpecific(args[0]).equals(player.getName())){
                            try {
                                useGift(args[0], player);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return true;
                        } else {
                            player.sendMessage("§e§lGIFTS §8§l❙ §fCe code §cn'existe pas §fou a §cdéjà été utilisé§f.");
                            return false;
                        }
                    } else {
                        try {
                            useGift(args[0], player);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                } else {
                    player.sendMessage("§e§lGIFTS §8§l❙ §fCe code §cn'existe pas §fou a §cdéjà été utilisé§f.");
                    return false;
                }
            }
            player.sendMessage("§e§lGIFTS §8§l❙ §fSyntaxe: §e/§fgift (§bCode§f)");
            return false;
        }

        return false;
    }
}
