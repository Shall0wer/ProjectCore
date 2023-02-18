package fr.shall0wer.project.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import fr.shall0wer.project.database.DatabaseManager;

public class EconomyCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!sender.hasPermission("responsable.economy")){
            sender.sendMessage("§cErreur: Vous n'avez pas la permission d'utiliser cette commande.");
            return false;
        }
        if(s.equalsIgnoreCase("comètes")){
            if(args.length == 1){
                int cometes = PlayerManager.getCometes(args[0]);
                if(DatabaseManager.hasPlayerAccount(args[0])){
                    sender.sendMessage("§a§lECONOMIE §fLe joueur §b" + args[0] + " §fpossède §3" + cometes + " Comètes§f.");
                    return true;
                } else {
                    sender.sendMessage("§cErreur: Le compte du joueur '" + args[0] + "' n'existe pas où est inaccessible.");
                }
                return false;
            }
            if(args.length == 3){
                int cometes = PlayerManager.getCometes(args[0]);
                if(DatabaseManager.hasPlayerAccount(args[0])){
                    if(args[1].equalsIgnoreCase("add")){
                        int temp = cometes + Integer.parseInt(args[2]);
                        PlayerManager.addCometes(args[0], Integer.parseInt(args[2]));
                        Player target = Bukkit.getPlayer(args[0]);
                        if(target != null){
                            target.playSound(target.getLocation(), Sound.ORB_PICKUP, 10, 1);
                            target.sendMessage("§6§lBOUTIQUE §8§l❙ §fVous avez reçu §3" + Integer.parseInt(args[2]) + " comètes§f.\n§6§lBOUTIQUE §8§l❙ §aMerci pour votre achat !");
                        }
                        sender.sendMessage("§a§lECONOMIE §8§l❙ §fLe joueur §b" + args[0] + "§f possède désormais §3" + temp + " Comètes§f.");
                        return true;
                    }
                    if(args[1].equalsIgnoreCase("remove")){
                        if(cometes <= Integer.parseInt(args[2])) {
                            int temp = cometes - Integer.parseInt(args[2]);
                            PlayerManager.removeCometes(args[0], Integer.parseInt(args[2]));
                            sender.sendMessage("§a§lECONOMIE §8§l❙ §fLe joueur §b" + args[0] + "§f possède désormais §3" + temp + " Comètes§f.");
                            return true;
                        } else {
                            sender.sendMessage("§cErreur: Vous ne pouvez pas retirer plus de comètes qu'un joueur possède !");
                        }
                        return false;
                    }
                } else {
                    sender.sendMessage("§cErreur: Le compte du joueur '" + args[0] + "' n'existe pas où est inaccessible.");
                    return false;
                }
                return false;
            }
            sender.sendMessage("§r \n§a§lECONOMIE §8§l❙ §fListe des commandes disponibles:" +
                    "\n§8» §3/§fcomètes §f(§bJoueur§f) §f: Voir le nombre de comètes d'un joueur." +
                    "\n§8» §3/§fcomètes §f(§bJoueur§f) (§aadd§f/§cremove§f) (§bNOMBRE§f) : Ajoute/Retire des comètes à un joueur." +
                    "\n§r ");
            return false;
        }

        /**
         * COMMANDE CREDITS
         */

        if(s.equalsIgnoreCase("étoiles")){
            if(args.length == 1){
                int etoiles = PlayerManager.getEtoiles(args[0]);
                if(DatabaseManager.hasPlayerAccount(args[0])){
                    sender.sendMessage("§a§lECONOMIE §fLe joueur §b" + args[0] + " §fpossède §3" + etoiles + " Comètes§f.");
                    return true;
                } else {
                    sender.sendMessage("§cErreur: Le compte du joueur '" + args[0] + "' n'existe pas où est inaccessible.");
                }
                return false;
            }
            if(args.length == 3){
                int etoiles = PlayerManager.getEtoiles(args[0]);
                if(DatabaseManager.hasPlayerAccount(args[0])){
                    if(args[1].equalsIgnoreCase("add")){
                        int temp = etoiles + Integer.parseInt(args[2]);
                        PlayerManager.addEtoile(args[0], Integer.parseInt(args[2]));
                        Player target = Bukkit.getPlayer(args[0]);
                        if(target != null){
                            target.playSound(target.getLocation(), Sound.ORB_PICKUP, 10, 1);
                            target.sendMessage("§6§lBOUTIQUE §8§l❙ §fVous avez reçu §6" + Integer.parseInt(args[2]) + " étoiles§f.\n§6§lBOUTIQUE §8§l❙ §aMerci pour votre achat !");
                        }
                        sender.sendMessage("§a§lECONOMIE §8§l❙ §fLe joueur §b" + args[0] + "§f possède désormais §6" + temp + " étoiles§f.");
                        return true;
                    }
                    if(args[1].equalsIgnoreCase("remove")){
                        if(etoiles <= Integer.parseInt(args[2])) {
                            int temp = etoiles - Integer.parseInt(args[2]);
                            PlayerManager.removeEtoiles(args[0], Integer.parseInt(args[2]));
                            sender.sendMessage("§a§lECONOMIE §8§l❙ §fLe joueur §b" + args[0] + "§f possède désormais §6" + temp + " étoiles§f.");
                            return true;
                        } else {
                            sender.sendMessage("§cErreur: Vous ne pouvez pas retirer plus d'étoiles qu'un joueur possède !");
                        }
                        return false;
                    }
                } else {
                    sender.sendMessage("§cErreur: Le compte du joueur '" + args[0] + "' n'existe pas où est inaccessible.");
                    return false;
                }
            } else {
                    sender.sendMessage("§cErreur: Le compte du joueur '" + args[0] + "' n'existe pas où est inaccessible.");
            }
            return false;
        }
            sender.sendMessage("§r \n§a§lECONOMIE §8§l❙ §fListe des commandes disponibles:" +
                    "\n§8» §6/§fétoiles §f(§bJoueur§f) §f: Voir le nombre d'étoiles d'un joueur." +
                    "\n§8» §6/§fétoiles §f(§bJoueur§f) (§aadd§f/§cremove§f) (§bNOMBRE§f) : Ajoute/Retire des étoiles à un joueur." +
                    "\n§r ");
        return false;
    }
}
