package fr.shall0wer.project.commands.mod;

import fr.shall0wer.project.IPlayer.IMod;
import fr.shall0wer.project.IPlayer.IPlayerInfos;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        IPlayerInfos playerInfos = new IPlayerInfos(player.getName());
        if(player.hasPermission("modo.command")){
            if(args.length == 0){
                if(playerInfos.isInModeration()){
                    playerInfos.setModModeration(false);
                    player.sendMessage("§9[Modération] §fLe mode modération ne sera §cplus actif §fau prochain changement de serveur.");
                    return true;
                }
                playerInfos.setModModeration(true);
                player.sendMessage("§9[Modération] §fLe mode modération §asera actif §fau prochain changement de serveur.");
                return true;
            }
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("help")){
                    player.sendMessage("§9[Modération] §fListe des commandes :" +
                            "\n  §9• §f/mod §9help §7(Vous êtes déjà ici :p)" +
                            "\n  §9• §f/mod §9§l» §fActive/Désactive le mode modération" +
                            "\n  §9• §f/mod §9tools §l» §fDonne les items du mod modération");
                    return false;
                }
                if(args[0].equalsIgnoreCase("tools")){
                    if(playerInfos.isModActive()){
                        IMod.giveModTools(player);
                        player.setGameMode(GameMode.ADVENTURE);
                        player.setAllowFlight(true);
                        player.setFlying(true);
                        return true;
                    }
                    player.sendMessage("§9[Modération] §fLe mode modération doit être activé pour faire cela.");
                }
                return false;
            }

        }
        player.sendMessage("§cErreur: Vous n'avez pas la permission d'utiliser cette commande.");
        return false;
    }
}
