package nix.bedeffect.data;

import nix.bedeffect.Main;
import org.bukkit.entity.Player;

import java.io.File;
import java.sql.*;

public class SQLite {

    private final Main plugin;

    public SQLite(Main plugin){
        this.plugin = plugin;
        connect();
        createTable();
    }

    private Connection connect(){
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + plugin.getDataFolder() + File.separator + "data" + File.separator + "data.db";
            connection = DriverManager.getConnection(url);
        }
        catch (SQLException | ClassNotFoundException e){
            plugin.getLogger().info("Unable to create database. ");
            e.printStackTrace();
        }
        return connection;
    }

    private void createTable(){
        String url = "jdbc:sqlite:" + plugin.getDataFolder() + File.separator + "data" + File.separator + "data.db";

        String sql = "CREATE TABLE IF NOT EXISTS bedEffects (\n"
                + "     id integer PRIMARY KEY,\n"
                + "     name text NOT NULL,\n"
                + "     effect text NOT NULL\n"
                + "     );";

        String sql2 = "CREATE UNIQUE INDEX idx_name ON bedEffects (name)";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            stmt.execute(sql2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        plugin.getLogger().info("Created BedEffect Table.");
    }

    public void insert(String player, String effect){

        String sql = "INSERT OR IGNORE INTO bedEffects(id,name,effect) VALUES(?,?,?)";
        Connection conn = this.connect();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(2, player);
            pstmt.setString(3, effect);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getEffect(Player player){
        String sql = "SELECT effect FROM bedEffects WHERE name=?";
        Connection conn = this.connect();
        String effect = "null";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, player.getName());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                effect = rs.getString("effect");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return effect;
    }

    public void update(Player player, String effect){
        String sql = "UPDATE bedEffects SET effect = ? WHERE name=?";
        Connection conn = this.connect();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, effect);
            pstmt.setString(2, player.getName());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
