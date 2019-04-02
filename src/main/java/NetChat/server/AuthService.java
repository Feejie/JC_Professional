package NetChat.server;

import java.sql.*;

public class AuthService {

    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:A:/Mine/Java/JC_Professional/userDB.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) throws SQLException {
        String sql = String.format("SELECT nickname FROM main where " +
                "login = '%s' and password = '%s'", login, pass);
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public static int getIdByLoginAndPass(String login, String pass) throws SQLException {
        String sql = String.format("SELECT id FROM main where " +
                "login = '%s' and password = '%s'", login, pass);
        ResultSet rs = stmt.executeQuery(sql);

        int id = 0;
        if (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }

    public static void addToHistory(String nickname, String message) throws SQLException {
        String sql = String.format("INSERT INTO chatHistory (nickname, message) \n" +
                "values ('%s', '%s')", nickname, message);
        stmt.executeQuery(sql);
    }

    public static String showHistory() throws SQLException {
        String str = null;
        ResultSet rs = stmt.executeQuery("SELECT * from chatHistory");

        while (rs.next()) {
            str += "\n " + rs.getString(1) + ": " + rs.getString(2);
        }
        return str;
    }

    public static void clearHistory() throws SQLException {
        stmt.executeQuery("DELETE from chatHistory");
    }


    public static boolean checkBlackList(ClientHandler client, String target) throws SQLException {
        String sql = String.format("SELECT nickname FROM blacklist where " +
                "id = '%s' and nickname = '%s'", client.getId(), target);
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            return true;
        }
        return false;
    }

    public static void addToBlackList(ClientHandler client, String target) throws SQLException {
        String sql = String.format("INSERT INTO blacklist (id, nickname) \n" +
                "VALUES ('%s', '%s')", client.getId(), target);
        stmt.executeQuery(sql);
    }

    public static void deleteFromBlackList(ClientHandler client, String target) throws SQLException {
        String sql = String.format("DELETE FROM blacklist WHERE id = '%s' and nickname = '%s'", client.getId(), target);
        stmt.executeQuery(sql);
    }


    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
