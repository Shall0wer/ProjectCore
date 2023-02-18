package fr.shall0wer.project.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscordLinkManager {

    public static boolean isDiscordAccountLinked(String player) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT discord_id FROM players WHERE pseudo = ?");
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                return rs.getString("discord_id") != null;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getDiscordID(String pseudo) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT discord_id FROM players WHERE pseudo = ?");
            ps.setString(1, pseudo);
            ResultSet rs = ps.executeQuery();
            String finalCometes = "";

            while(rs.next()) {
                finalCometes = rs.getString("discord_id");
            }

            rs.close();
            return finalCometes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean hasDiscordKey(String player) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT discord_key FROM players WHERE pseudo = ?");
            ps.setString(1, player);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                return rs.getString("discord_key") != null;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getDiscordKey(String pseudo) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT discord_key FROM players WHERE pseudo = ?");
            ps.setString(1, pseudo);
            ResultSet rs = ps.executeQuery();
            String finalCometes = "";

            while(rs.next()) {
                finalCometes = rs.getString("discord_key");
            }

            rs.close();
            return finalCometes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void setDiscordKey(String pseudo, String key) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE players SET discord_key = ? WHERE pseudo = ?");
            ps.setString(1, key);
            ps.setString(2, pseudo);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void resetDiscordLink(String pseudo){
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE players SET discord_key = ? WHERE pseudo = ?");
            ps.setString(1, null);
            ps.setString(2, pseudo);
            ps.executeUpdate();
            ps.close();
            PreparedStatement psb = DatabaseManager.getConnection().prepareStatement("UPDATE players SET discord_id = ? WHERE pseudo = ?");
            psb.setString(1, null);
            psb.setString(2, pseudo);
            psb.executeUpdate();
            psb.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
