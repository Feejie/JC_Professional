package Lesson_2;

import java.sql.*;

public class MainStaff {

    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) {

        try {
            connect();

            // Create
//            stmt.executeUpdate("INSERT INTO personal (name, salary)\n" +
//                    "values ('Bob5', 500)");

            // Update
//            stmt.executeUpdate("UPDATE personal SET name = 'NotBobNow' WHERE id = 6");

            // Delete
//            stmt.executeUpdate("DELETE FROM personal WHERE id = 2");

            // Select
            ResultSet rs = stmt.executeQuery("SELECT * from personal WHERE id = 6");

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("name"));
            }



//            ResultSet rs = stmt.executeQuery("SELECT * from personal where id = 2");
//
//            ResultSetMetaData rsmd = rs.getMetaData();
//
//            for (int i = 1; i < rsmd.getColumnCount(); i++) {
//                System.out.println(rsmd.getColumnName(i) + " " + rsmd.getColumnType(i));
//            }
//
//            while (rs.next()) {
//                System.out.println(rs.getInt(1) + " " + rs.getString("name"));
//            }

//            stmt.executeUpdate("DELETE FROM personal WHERE id = 5");

//            stmt.executeUpdate("CREATE TABLE term " +
//                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
//                    "name TEXT," +
//                    "term TEXT)");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void connect() throws ClassNotFoundException, SQLException {

        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:staffDB.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
