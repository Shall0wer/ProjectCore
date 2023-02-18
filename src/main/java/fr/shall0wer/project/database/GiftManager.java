package fr.shall0wer.project.database;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GiftManager {

    FileConfiguration config;
    String path = "/home/minecraft/DataBase/Gifts/";

    public Boolean existGift(String uuid) {
        File file = new File(path + uuid + ".yml");
        return file.exists();
    }

    /**
     * GET INFOS DU GIFT
     */
    public String getDescription(String uuid){
        File file = new File(path + uuid + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        return config.getString("description");
    }
    public Boolean getAlwaysEnable(String uuid){
        File file = new File(path + uuid + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        return config.getBoolean("always_enable");
    }
    public Integer getUtilisations(String uuid){
        File file = new File(path + uuid + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        return config.getInt("utilisations");
    }
    public void setUtilisations(String uuid) throws IOException {
        File file = new File(path + uuid + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        int u = getUtilisations(uuid);
        u--;
        config.set("utilisations", u);
        config.save(file);
    }
    public Boolean getSpecificPlayer(String uuid){
        File file = new File(path + uuid + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        return config.getBoolean("specific_player");
    }
    public String getPlayerWhichIsSpecific(String uuid){
        File file = new File(path + uuid + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        return config.getString("player");
    }
    public List getGiftCommands(String uuid){
        File file = new File(path + uuid + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        return config.getList("commands");
    }
    public String getRewardsMessage(String uuid){
        File file = new File(path + uuid + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        return config.getString("rewards");
    }
    public List getGiftUsers(String uuid){
        File file = new File(path + uuid + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);
        return config.getList("users");
    }

    public void addPlayerToGift(String uuid, Player player) throws IOException {
        File file = new File(path + uuid + ".yml");
        List<String> players = getGiftUsers(uuid);
        players.add(player.getName());
        config.set(uuid + "users", players);
        config.save(file);
    }

    public void useGift(String uuid, Player player) throws IOException {
        /**
         * COMMANDS EXECUTE
         */
        for(String commands : (ArrayList<String>) getGiftCommands(uuid)){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("{PLAYER}", player.getName()));
        }

        player.sendMessage("§r \n§e§lGIFTS §8§l❙ §fCode utilisé avec succès !" +
                "\n§r " +
                "\n§8Contenu:\n" +
                getRewardsMessage(uuid).replace( "[", "").replace( "]", "").replace(",", "") +
                "\n§r " +
                "\n§f§oTous les objets viennent d'être transférés sur votre compte." +
                "\n§r ");
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 10);
        addPlayerToGift(uuid, player);
        if(!getAlwaysEnable(uuid)){
            if(getUtilisations(uuid) > 1){
                setUtilisations(uuid);
                return;
            } else {
                File file = new File(path + uuid + ".yml");
                file.delete();
            }
        }
        return;
    }
}
