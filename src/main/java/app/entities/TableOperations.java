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
    public static  String query;

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

        System.out.println(nameColumn + " | Имена столбцов в таблице");

        String e = "";

        for(int i = 1; i < nameColumn.size(); i++){
            e = e + nameColumn.get(i) + ", ";
        }

        StringBuilder sb = new StringBuilder(e);
        sb.deleteCharAt(sb.length()-2);
        e = sb.toString();
        query = e.trim();

        System.out.println(query + " | Строка стобцов для INSERT");
    }

    public static void alterTable() throws SQLException {
        if (Calculation.nowColumns >= columns) {
            int create = Calculation.nowColumns - columns;
            create = create + 1;
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
