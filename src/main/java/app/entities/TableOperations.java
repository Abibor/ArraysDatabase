package app.entities;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class TableOperations {

    //доступ к БД
    private static String url = "jdbc:postgresql://localhost:5432/database_arrays";
    private static String user = "postgres";
    private static String password = "453659";
    public static int columns;

    public static void countColumn() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, password);

        PreparedStatement ps = con.prepareStatement("select * from list_arrays");
        //PreparedStatement ps = con.prepareStatement("select * from table_name");

        ResultSet rs = ps.executeQuery();

        ResultSetMetaData rsmd = rs.getMetaData();

        columns = rsmd.getColumnCount();
    }

    public static void columnName() throws SQLException {
        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement("select * from list_arrays");
        ResultSet rs = ps.executeQuery();
        ResultSetMetaData meta = rs.getMetaData();

        int columnCount = meta.getColumnCount();

        ArrayList<String> nameColumn = new ArrayList<>();
        for (int i = 1; i < columnCount; i++) {
            String columnName = meta.getColumnName(i);
            nameColumn.add(columnName);
        }
        System.out.println(nameColumn);

        String query = "";

        for(int i = 1; i < nameColumn.size(); i++){
            query = query + nameColumn.get(i) + ", ";
        }

        StringBuilder sb = new StringBuilder(query);
        sb.deleteCharAt(sb.length()-2);
        query = sb.toString();
        String e = query.trim();

        System.out.println(e);
    }

    public static void alterTable() throws SQLException {
        if (Calculation.nowColumns != columns) {
            int create = Calculation.nowColumns - columns + 1;
            System.out.println("created columns = " + create);
            Integer[] clmns = new Integer[create];

            for (int i = 0; i < clmns.length; i++) {
                columns++;
                clmns[i] = columns;
                String sql = "ALTER TABLE list_arrays ADD column column_" + clmns[i] + " varchar(10)";
                //String sql="ALTER TABLE table_name ADD column column_"+clmns[i]+" varchar(10)";
                Connection con = DriverManager.getConnection(url, user, password);
                PreparedStatement ps = con.prepareStatement(sql);
                ps.execute();
            }
        } else {
            System.out.println("Колличество столбцов хватает для количества элементов массива");
        }
    }

    public static void insertRow(String query) throws SQLException {
        Connection con = DriverManager.getConnection(url, user, password);
        PreparedStatement ps = con.prepareStatement(query);
        ps.executeUpdate();
        System.out.println("Query done! Check it");

    }
}
