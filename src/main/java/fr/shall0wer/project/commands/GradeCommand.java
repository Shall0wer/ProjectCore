package fr.shall0wer.project.commands;

import com.nametagedit.plugin.NametagEdit;
import fr.shall0wer.project.database.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.HashMap;

public class GradeCommand implements CommandExecutor {

    public static HashMap<String, String> gradeCorresp = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        if(player.hasPermission("*")){
            if(args.length == 3){
                Player target = Bukkit.getPlayer(args[0]);
                if(DatabaseManager.hasPlayerAccount(args[0]) && target != null){
                    if(gradeCorresp.containsKey(args[1].toLowerCase())){
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "bungeee perms user " + target.getName() + " add group " + args[1]);
                        NametagEdit.getApi().setPrefix(target, gradeCorresp.get(args[1]));
                        PlayerManager.setRank(args[0], gradeCorresp.get(args[1]));
                        player.sendMessage("§6§lGRADE §8§l❙ §fLe joueur §b" + target.getName() + " §fest maintenant " + gradeCorresp.get(args[1]).replace("&", "§") + "§f!");
                        if(args[2].equalsIgnoreCase("true")){
                            target.sendMessage("§r \n§6§lGRADE §8§l❙ §aFélicitations ! §fVous avez reçu le grade " + gradeCorresp.get(args[1]).replace("&", "§") + "§f!\n§r ");
                            target.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 1);
                        }
                        return true;
                    } else {
                        player.sendMessage("§6§lGRADE §8§l❙ §fListe des grades : §c§lADMIN§f, §c§lRESP§f, §b§lMANAGER§f, §9§lMODO§f, §3§lHELPER§f, §5§lDEV§f, §2§lBUILDER§f, §cYou§fTube§f, §dCustom§f, §6Céleste§f, §eAnge§f, §fGardien§f, §7default");
                    }
                    player.sendMessage("§6§lGRADE §8§l❙ §fSyntaxe: §6/§fgrade (§bJoueur§f) (§6Grade§f) (§eNotification§f)");
                } else {
                    player.sendMessage("§cErreur: Le compte du joueur '" + args[0] + "§c' est introuvable ou est déconnecté.");
                }
                return false;
            }
            player.sendMessage("§6§lGRADE §8§l❙ §fSyntaxe: §6/§fgrade (§bJoueur§f) (§6Grade§f) (§eNotification§f)");
            return true;
        }
        player.sendMessage("§6§lSERVEUR §8§l❙ §fCommande introuvable.");
        return false;
    }

    public static void setRanksPrefixs(){
        GradeCommand.gradeCorresp.put("admin", "&c&lADMIN &c");
        GradeCommand.gradeCorresp.put("resp", "&c&lRESP &c");
        GradeCommand.gradeCorresp.put("manager", "&b&lMANAGER &b");
        GradeCommand.gradeCorresp.put("modo", "&9&lMODO &9");
        GradeCommand.gradeCorresp.put("helper", "&3&lHELPER &3");
        GradeCommand.gradeCorresp.put("dev", "&5&lDEV &5");
        GradeCommand.gradeCorresp.put("builder", "&2&lBUILDER &2");
        GradeCommand.gradeCorresp.put("youtube", "&cYou&fTube &f");
        GradeCommand.gradeCorresp.put("custom", "&dCustom &d");
        GradeCommand.gradeCorresp.put("céleste", "&6Céleste &6");
        GradeCommand.gradeCorresp.put("ange", "&eAnge &e");
        GradeCommand.gradeCorresp.put("gardien", "&fGardien &f");
        GradeCommand.gradeCorresp.put("default", "&7");
    }
}
