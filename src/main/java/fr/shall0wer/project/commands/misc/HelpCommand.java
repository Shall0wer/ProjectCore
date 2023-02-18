package fr.shall0wer.project.commands.misc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("helper") && player.hasPermission("helper.help")){
                player.sendMessage("§r \n§6§lAIDE §8§l❙ §fListe des commandes pour le grade §3§lHELPER§f:" +
                        "\n§3/§fro §3§l» §fPermet de voir les signalements liés au chat." +
                        "\n§3/§fpunish (§bJoueur§f) §3§l» §fPermet de sanctionner un joueur." +
                        "\n§3/§fstatut (§bJoueur§f) §3§l» §fPermet de voir si un joueur est sanctionné." +
                        "\n§r ");
                return true;
            }
            if(args[0].equalsIgnoreCase("modo") && player.hasPermission("mod.help")){
                player.sendMessage("§r \n§6§lAIDE §8§l❙ §fListe des commandes pour le grade §9§lMODO§f:" +
                        "\n§9/§fmod §9§l» §fActive/Désactive le mode modération" +
                        "\n§9/§fpunish (§bJoueur§f) §9§l» §fPermet de sanctionner un joueur." +
                        "\n§9/§fwhois (§bJoueur§f) §9§l» §fPermet de voir les informations d'un joueur." +
                        "\n§9/§freports §9§l» §fPermet de voir tous les signalements." +
                        "\n§r ");
                return true;
            }
        }
        player.sendMessage("§r \n§6§lAIDE §8§l❙ §fListe des commandes disponibles:" +
                "\n§6/§frules §6§l» §fAffiche les règles du serveur." +
                "\n§6/§ffriends §6§l» §fAffiche les commandes pour le système d'amis." +
                "\n§6/§fignore §6§l» §fPermet d'ignorer les messages d'un joueur." +
                "\n§6/§freport §6§l» §fPermet de signaler un joueur." +
                "\n§6/§fmsg §6§l» §fPermet d'envoyer un message privé à un joueur." +
                "\n§6/§fr §6§l» §fPermet de répondre rapidement à un message reçu." +
                "\n§6/§flag §6§l» §fPermet d'obtenir des informations de lag sur le serveur." +
                "\n§r ");
        return false;
    }
}
