package fr.shall0wer.project.commands;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class LagCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }

        Player player = (Player) sender;
        EntityPlayer nmsPlayer = ((CraftPlayer) player).getHandle();
        player.sendMessage("§e[Latence] §fVotre latence est de §b" + nmsPlayer.ping + "ms§f.");
        return false;
    }
}
