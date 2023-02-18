package fr.shall0wer.project.database;

import fr.shall0wer.project.utils.ConvertTime;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ChatReportManager {

    public static int getAllReports() {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT * FROM chat_reports");
            ResultSet rs = ps.executeQuery();
            int number = 0;

            while(rs.next()) {
                number = number++;
            }

            rs.close();
            return number;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Boolean hasPlayerReport(String pseudo){
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT * FROM chat_reports WHERE who_folder = ?");
            ps.setString(1, pseudo);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                return true;
            }

            rs.close();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getPlayerReportID(String pseudo){
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT who_folder FROM chat_reports WHERE who_folder = ?");
            ps.setString(1, pseudo);
            ResultSet rs = ps.executeQuery();
            int player = 0;

            while(rs.next()) {
                player = rs.getInt("id");
            }

            rs.close();
            return player;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void createMessage(String pseudo, String message, String serveurName){
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("INSERT INTO chat_messages (uuid, player, message, serveur, time) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, pseudo);
            ps.setString(3, message);
            ps.setString(4, serveurName);
            ps.setLong(5, new ConvertTime(System.currentTimeMillis()).getTimeMillis());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createReport(String uuid){
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("INSERT INTO chat_reports (uuid, pseudo, message, time, serveur) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, uuid);
            ps.setString(3, uuid);
            ps.setString(4, uuid);
            ps.setLong(5, new ConvertTime(System.currentTimeMillis()).getTimeMillis());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
