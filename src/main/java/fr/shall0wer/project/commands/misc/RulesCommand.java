package fr.shall0wer.project.commands.misc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RulesCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        sender.sendMessage("§6[Informations] §fRèglement : §bhttps://www.SERVEUR.fr/rules");
        return false;
    }
}
