package fst.tp1;

import java.sql.*;

public class JdbcDao {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/javafx_tp1?useSSL=false";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "";
    private static final String INSERT_QUERY = "INSERT INTO produit (nom, prix) VALUES (?, ?)";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM produit";
    private static final String AGE_QUERY = "SELECT prix FROM produit";


    public void insertRecord(String fullName, double prix) {

        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, fullName);
            preparedStatement.setDouble(2, prix);

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int calcRecord() {
        int count = -1;
        try {
            Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);


            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(COUNT_QUERY);
            while (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            stm.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int sumAge() {
        int count = -1;

        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(AGE_QUERY)) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        }
        return count;
    }

}