package fr.shall0wer.project.IPlayer;

import fr.shall0wer.project.database.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IEconomy {

    /**
     * SYSTÈME DE COINS
     */
    public static void addCometes(String pseudo, int i) {
        try {
            int cometes = getCometes(pseudo);
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE players SET cometes = ? WHERE pseudo = ?");
            ps.setInt(1, i + cometes);
            ps.setString(2, pseudo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeCometes(String pseudo, int i) {
        try {
            int cometes = getCometes(pseudo);
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE players SET cometes = ? WHERE pseudo = ?");
            ps.setInt(1, cometes - i);
            ps.setString(2, pseudo);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCometes(String pseudo) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT cometes FROM players WHERE pseudo = ?");
            ps.setString(1, pseudo);
            ResultSet rs = ps.executeQuery();
            int finalCometes = 0;

            while(rs.next()) {
                finalCometes = rs.getInt("cometes");
            }

            rs.close();
            return finalCometes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * SYSTÈME D'ÉTOILES
     */
    public static void addEtoile(String pseudo, int i) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE players SET etoiles = etoiles + ? WHERE pseudo = ?");
            ps.setInt(1, i);
            ps.setString(2, pseudo);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeEtoiles(String pseudo, int i) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("UPDATE players SET etoiles = etoiles - ? WHERE pseudo = ?");
            ps.setInt(1, i);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getEtoiles(String pseudo) {
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT etoiles FROM players WHERE pseudo = ?");
            ps.setString(1, pseudo);
            ResultSet rs = ps.executeQuery();
            int finalEtoiles = 0;

            while(rs.next()) {
                finalEtoiles = rs.getInt("etoiles");
            }

            ps.close();
            return finalEtoiles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
