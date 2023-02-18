package fr.shall0wer.project.manager;

import fr.mrcubee.finder.plugin.PluginFinder;
import fr.shall0wer.project.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenerManager implements Listener {

    public static void registers(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), (Plugin) PluginFinder.INSTANCE.findPlugin());
        pm.registerEvents(new BlockCommands(), (Plugin) PluginFinder.INSTANCE.findPlugin());
        pm.registerEvents(new PlayerChatListener(), (Plugin) PluginFinder.INSTANCE.findPlugin());
        pm.registerEvents(new InventoryClosePrefix(), (Plugin) PluginFinder.INSTANCE.findPlugin());
        pm.registerEvents(new PlayerQuitListener(), (Plugin) PluginFinder.INSTANCE.findPlugin());
    }
}
