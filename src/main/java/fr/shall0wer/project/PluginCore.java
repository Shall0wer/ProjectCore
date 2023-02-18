package fr.shall0wer.project;

import fr.mrcubee.packet.bukkit.PacketListener;
import fr.shall0wer.project.commands.*;
import fr.shall0wer.project.commands.misc.DiscordCommand;
import fr.shall0wer.project.commands.misc.HelpCommand;
import fr.shall0wer.project.commands.misc.RulesCommand;
import fr.shall0wer.project.commands.mod.*;
import fr.shall0wer.project.database.DatabaseManager;
import fr.shall0wer.project.gui.lobby.MainGui;
import fr.shall0wer.project.gui.lobby.VIPGui;
import fr.shall0wer.project.gui.punish.CheatMenu;
import fr.shall0wer.project.gui.punish.MainPunish;
import fr.shall0wer.project.gui.punish.MessagesMenu;
import fr.shall0wer.project.gui.punish.OtherMenu;
import fr.shall0wer.project.manager.ListenerManager;
import fr.shall0wer.project.packets.PacketSendReceive;
import fr.shall0wer.project.runnables.ModRunnable;
import fr.shall0wer.project.utils.GuiBuilder;
import fr.shall0wer.project.utils.GuiManager;
import fr.shall0wer.project.utils.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class PluginCore extends JavaPlugin {

    private PacketSendReceive packetEdit;
    private static PluginCore INSTANCE;
    public DatabaseManager database;

    /**
     * MENUS
     */
    private static GuiManager guiManager;
    private static Map<Class <? extends GuiBuilder>, GuiBuilder> registeredMenus;
    public static GuiManager getGuiManager() {
        return guiManager;
    }
    public static Map<Class<? extends GuiBuilder>, GuiBuilder> getRegisteredMenus() {
        return registeredMenus;
    }
    public static Map<Player, String> ssCorresp = new HashMap();

    public BukkitTask taskID;
    public int task;
    public Random r = new Random();


    @Override
    public void onEnable() {
        GradeCommand.setRanksPrefixs();
        INSTANCE = this;
        saveDefaultConfig();
        database = new DatabaseManager("jdbc:mysql://", "localhost", "minecraft", "root", "");
        database.connexion();

        getCommand("comètes").setExecutor(new EconomyCommands());
        getCommand("étoiles").setExecutor(new EconomyCommands());
        getCommand("admingift").setExecutor(new GiftCommand());
        getCommand("gift").setExecutor(new GiftCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("lag").setExecutor(new LagCommand());
        getCommand("grade").setExecutor(new GradeCommand());
        getCommand("discord").setExecutor(new DiscordCommand());
        getCommand("whois").setExecutor(new WhoIsCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getCommand("unmute").setExecutor(new UnmuteCommand());
        getCommand("ban").setExecutor(new BanCommand());
        getCommand("unban").setExecutor(new UnbanCommand());
        getCommand("statut").setExecutor(new StatutCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("punish").setExecutor(new PunishCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("ro").setExecutor(new RoCommand());
        getCommand("boutique").setExecutor(new RoCommand());
        getCommand("mod").setExecutor(new ModCommand());

        ListenerManager.registers();
        loadGui();

        packetEdit = new PacketSendReceive();
        PacketListener.registerPacketHandler(packetEdit);
        for (Player player : Bukkit.getOnlinePlayers()){
            PacketListener.addPlayer(player);
        }

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        new ModRunnable().runTaskTimer(this, 20 * 3, 20 * 3);
    }

    public static PluginCore getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void loadGui(){
        guiManager = new GuiManager();
        registeredMenus = new HashMap<>();
        Bukkit.getPluginManager().registerEvents(guiManager, this);
        guiManager.addMenu(new MainGui());
        guiManager.addMenu(new VIPGui());
        guiManager.addMenu(new MainPunish());
        guiManager.addMenu(new MessagesMenu());
        guiManager.addMenu(new CheatMenu());
        guiManager.addMenu(new OtherMenu());
    }

    public void animation(Location loc) {
        taskID = Bukkit.getScheduler().runTaskTimer(this, new Runnable() {

            double t = Math.PI/4;
            @Override
            public void run() {
                t = t + 0.1*Math.PI;
                for (double theta = 0; theta <= 2*Math.PI; theta = theta + Math.PI/32) {
                    double x = t*Math.cos(theta);
                    double y = 2*Math.exp(-0.1*t) * Math.sin(t) + 0;
                    double z = t*Math.sin(theta);
                    loc.add(x,y,z);
                    ParticleEffect.FIREWORKS_SPARK.display(0F, 0F, 0F, 0, 1, loc, 10);
                    loc.subtract(x,y,z);

                    theta = theta + Math.PI/64;

                    x = t*Math.cos(theta);
                    y = 2*Math.exp(-0.1*t) * Math.sin(t) + 0;
                    z = t*Math.sin(theta);
                    loc.add(x,y,z);
                    ParticleEffect.SPELL_WITCH.display(0F, 0F, 0F, 0, 1, loc, 10);
                    loc.subtract(x,y,z);
                }
                if (t > 20) {
                    taskID.cancel();
                }
            }
        }, 0L, 1L);
    }
}
