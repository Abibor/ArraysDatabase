package app.entities;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableOperations {

    //доступ к БД
    private static String url = "jdbc:postgresql://localhost:5432/database_arrays";
    private static String user = "postgres";
    private static String password = "453659";
    public static int columns;
    public static String query;

    //Ниже переменные для начала работы с БД
    private static String d = "";
    private static int columns1;
    public static int nowColumns;
    public static String query1;


    public static void start(List<String> stringNumberList) throws SQLException {
        //List<String> stringNumberList = new ArrayList<>();
        List<Integer> numberList = new ArrayList<>();

        //stringNumberList.add("2");
        //stringNumberList.add("22");
        //stringNumberList.add("222");
        //stringNumberList.add("2222");
        //stringNumberList.add("-2");
        //stringNumberList.add("5");
        //stringNumberList.add("6");
        //stringNumberList.add("7");
        //stringNumberList.add("7");
        //stringNumberList.add("7");
        //stringNumberList.add("7");

        System.out.println(stringNumberList);
        nowColumns = stringNumberList.size();
        System.out.println(nowColumns + " | Количество элементов в списке для добавления в БД");

        for (int i = 0; i < stringNumberList.size(); i++) {
            d = d + stringNumberList.get(i) + ", ";
        }

        StringBuilder sb = new StringBuilder(d);
        sb.deleteCharAt(sb.length()-2);
        d = sb.toString();
        String e = d.trim();

        System.out.println(e + " | Строка для INSERT значения VALUE");


        for(String s : stringNumberList) numberList.add(Integer.valueOf(s));
        Collections.sort(numberList);

        System.out.println(numberList + " - Отсортированная список элементов");

        //TableOperations.countColumn();
        countColumn();
        //columns1 = TableOperations.columns;
        columns1 = columns;
        System.out.println(columns1 + " | Количество столбцов в БД");

        //ableOperations.alterTable();
        alterTable();
        //TableOperations.columnName();
        columnName();

        //query1 = "INSERT INTO list_arrays (" + TableOperations.query + ") VALUES ("+ e + ")";
        query1 = "INSERT INTO list_arrays (" + query + ") VALUES ("+ e + ")";
        //String query = "INSERT INTO table_name VALUES ("+ e + ")";

        System.out.println(query1 + " | Итоговая строка для вставки значений в БД");

        //TableOperations.insertRow(query1);
        insertRow(query1);
    }


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

        //Счетчик колличества столбцов
        int columnCount = meta.getColumnCount();
        //Массив для хранения имен столбцов
        ArrayList<String> nameColumn = new ArrayList<>();

        for (int i = 1; i < columnCount + 1; i++) {
            String columnName = meta.getColumnName(i);
            nameColumn.add(columnName);
        }

        System.out.println(nameColumn + " | Имена столбцов в таблице");

        String e = "";

        for (int i = 1; i < Calculation.nowColumns + 1; i++) {
            e = e + nameColumn.get(i) + ", ";
        }

        StringBuilder sb = new StringBuilder(e);
        sb.deleteCharAt(sb.length() - 2);
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
