package app.entities;

import javax.swing.*;
import java.sql.*;

public class TableOperations {

    //доступ к БД
    private static String url = "jdbc:postgresql://localhost:5432/database_arrays";
    private static String user = "postgres";
    private static String password = "453659";
    public static int columns;

    public static void countColumn() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, password);

        PreparedStatement ps = con.prepareStatement("select * from list_arrays");

        ResultSet rs = ps.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();

        columns = rsmd.getColumnCount();
    }

    public static void alterTable() throws SQLException {
        if (Calculation.nowColumns != columns){
            int create = Calculation.nowColumns - columns;
            System.out.println("create = " + create);
            String [] clmns = new String[create];
            for (int i = 0; i < clmns.length; i++) {
                String sql="ALTER TABLE list_arrays ADD column datatype"+i+" varchar(10)";
                Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = con.prepareStatement(sql);
                ps.execute();
            }
        }
    }
}
