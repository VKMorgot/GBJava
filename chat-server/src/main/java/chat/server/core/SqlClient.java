package chat.server.core;

import java.sql.*;

public class SqlClient {

    private static Connection connection;
    private static Statement statement;
    private static final String FILE_NAME = "chat-server/src/main/resources/clients-db.sqlite";

    synchronized static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + FILE_NAME);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized static String getNick(String login, String password) {
        String query =  String.format(
                "select nickname from users where login='%s' and password ='%s'",
                login, password);
        try (ResultSet set = statement.executeQuery(query)) {
            if (set.next())
                return set.getString("nickname");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
