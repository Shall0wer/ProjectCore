package fr.shall0wer.project.IPlayer;

import fr.shall0wer.project.database.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IPlayerInfos {

    protected Player player;
    private Connection connection = DatabaseManager.getConnection();

    public IPlayerInfos(String player) {
        this.player = Bukkit.getPlayer(player);
    }

    public Player getPlayer() {
        return player;
    }

    public boolean hasAccount(){
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT uuid FROM players WHERE pseudo = ?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createAccount() {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO players (uuid, pseudo) VALUES (?, ?)");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, player.getName());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getCometes(){
        return IEconomy.getCometes(player.getName());
    }

    public int getEtoiles(){
        return IEconomy.getEtoiles(player.getName());
    }


    public void setRank(String prefix) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE players SET grade = ? WHERE pseudo = ?");
            ps.setString(1, prefix);
            ps.setString(2, player.getName());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public String getRank() {
        try {
            String grade = "";
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT grade FROM players WHERE pseudo = ?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                grade = rs.getString("grade");
            }

            rs.close();
            return grade;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getOptions(String opt) {
        try {
            String options = "";
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT " + opt + " FROM players WHERE pseudo = ?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                options = rs.getString(opt);
            }

            rs.close();
            return options;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setOptions(String opt, String value) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE players SET " + opt + " = ? WHERE pseudo = ?");
            ps.setString(1, value);
            ps.setString(2, player.getName());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSanction(String opt) {
        try {
            int sanction = 0;
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT " + opt + " FROM players WHERE pseudo = ?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                sanction = rs.getInt(opt);
            }

            rs.close();
            return sanction;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void setSanction(String opt, int value) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE players SET " + opt + " = ? WHERE pseudo = ?");
            ps.setInt(1, value);
            ps.setString(2, player.getName());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean isMuted() {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT uuid FROM mutes WHERE pseudo = ?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                return rs.getString("uuid") != null;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setupMute(String banner, String raison, Long time){
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("INSERT INTO mutes (uuid, pseudo, banner, raison, time) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, getOptions("uuid"));
            ps.setString(2, player.getName());
            ps.setString(3, banner);
            ps.setString(4, raison);
            ps.setLong(5, time);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getMuteOpt(String opt) {
        try {
            String options = "";
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT " + opt + " FROM mutes WHERE pseudo = ?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                options = rs.getString(opt);
            }

            rs.close();
            return options;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Long getMuteTime() {
        try {
            long time = 0L;
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT time FROM mutes WHERE pseudo = ?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                time = rs.getLong("time");
            }

            rs.close();
            return time;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public void deleteMute(Boolean keepMute) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("DELETE FROM mutes WHERE pseudo = ?");
            ps.setString(1, player.getName());
            ps.executeUpdate();
            if(!keepMute){
                int mutes = getSanction("total_mutes");
                mutes--;
                setSanction("total_mutes", mutes);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isBanned() {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT uuid FROM bans WHERE pseudo = ?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                return rs.getString("uuid") != null;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param banner Le mec qui ban
     * @param raison La raison de la sanction
     * @param time La date de l'expiration de la sanction
     */
    public void setupBan(String banner, String raison, Long time){
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("INSERT INTO bans (uuid, pseudo, banner, raison, time) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, getOptions("uuid"));
            ps.setString(2, player.getName());
            ps.setString(3, banner);
            ps.setString(4, raison);
            ps.setLong(5, time);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getBanOpt(String opt) {
        try {
            String options = "";
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT " + opt + " FROM bans WHERE pseudo = ?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                options = rs.getString(opt);
            }

            rs.close();
            return options;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Long getBanTime() {
        try {
            long time = 0L;
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT time FROM bans WHERE pseudo = ?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                time = rs.getLong("time");
            }

            rs.close();
            return time;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public void deleteBan(Boolean keepBan) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("DELETE FROM bans WHERE pseudo = ?");
            ps.setString(1, player.getName());
            ps.executeUpdate();
            if(!keepBan){
                int bans = getSanction("total_bans");
                bans--;
                setSanction("total_bans", bans);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getMultiaccounts(){
        ArrayList<String> accounts = new ArrayList<>();
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT * FROM players WHERE last_ip = ?");
            ps.setString(1, getOptions("last_ip"));
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                accounts.add(rs.getString("pseudo"));
            }

            rs.close();
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public void setModModeration(Boolean b) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE players SET mod_isMod = ? WHERE pseudo = ?");
            ps.setBoolean(1, b);
            ps.setString(2, player.getName());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean isInModeration(){
        try {
            boolean statut = false;
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT mod_isMod FROM players WHERE pseudo = ?");
            ps.setString(1, player.getName());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                statut = rs.getBoolean("mod_isMod");
            }

            rs.close();
            return statut;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean isModActive(){
        return IMod.mods.contains(player.getUniqueId());
    }
}
