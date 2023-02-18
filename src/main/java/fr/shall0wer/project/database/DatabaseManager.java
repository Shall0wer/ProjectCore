package fr.shall0wer.project.database;

import fr.shall0wer.project.IPlayer.IPlayerInfos;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class DatabaseManager {

    /**
     * public DatabaseManager database;
     * database = new DatabaseManager("jdbc:mysql://", "localhost", "bdd", "user", "password");
     * database.connexion();
     */

    private String urlBase;
    private String host;
    private String database;
    private String userName;
    private String password;
    private static Connection connection;

    public DatabaseManager(String urlBase, String host, String database, String userName, String password){
        this.urlBase = urlBase;
        this.host = host;
        this.database = database;
        this.userName = userName;
        this.password = password;
    }

    public static Connection getConnection() {
        return connection;
    }

    public void connexion() {
        if(!isOnline()){
            try {
                connection = DriverManager.getConnection(this.urlBase + this.host + "/" + this.database, this.userName, this.password);
                System.out.println("[DatabaseManager] MySQL connected.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deconnexion() {
        if(isOnline()) {
            try {
                connection.close();
                System.out.println("[DatabaseManager] MySQL disconnected.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isOnline() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




    public static void createAccount(UUID uuid) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO players (uuid, pseudo) VALUES (?, ?)");
            ps.setString(1, uuid.toString());
            ps.setString(2, Bukkit.getPlayer(uuid).getName());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> checkIp(String pseudo){
        ArrayList<String> ips = new ArrayList<>();
        IPlayerInfos playerInfos = new IPlayerInfos(pseudo);
        try {
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement("SELECT * FROM players WHERE last_ip = ?");
            ps.setString(1, playerInfos.getOptions("last_ip"));
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                ips.add(rs.getString("pseudo"));
            }

            rs.close();
            return ips;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ips;
    }
}
