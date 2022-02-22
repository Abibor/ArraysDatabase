package app.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {
    private static final Model instance = new Model();

    //доступ к БД
    private static final String url = "jdbc:postgresql://localhost:5432/database_arrays";
    private static final String user = "postgres";
    private static final String password = "453659";

    public static int columns;
    public static String query;

    //Ниже переменные для начала работы с БД
    private static String d = "";
    private static String e = "";
    public static int nowColumns;
    public static String query1;

    int count = 0;
    String[] getArray;

    public static Model getInstance() {
        return instance;
    }

    private Model() {

    }

    public void start(List<String> stringNumberList) {
        List<Integer> numberList = new ArrayList<>();

        System.out.println(stringNumberList);
        nowColumns = stringNumberList.size();
        System.out.println(nowColumns + " | Количество элементов в списке для добавления в БД");

        for (String value : stringNumberList) {
            d = d + value + ", ";
        }

        StringBuilder sb = new StringBuilder(d);
        sb.deleteCharAt(sb.length() - 2);
        d = sb.toString();
        e = d.trim();

        System.out.println(e + " | Строка для INSERT значения VALUE");

        for (String s : stringNumberList) numberList.add(Integer.valueOf(s));
        Collections.sort(numberList);

        System.out.println(numberList + " - Отсортированная список элементов");

        countColumn();
        int columns1 = columns;
        System.out.println(columns1 + " | Количество столбцов в БД");

        alterTable();
        columnName();

        query1 = "INSERT INTO list_arrays (" + query + ") VALUES (" + e + ")";
        System.out.println(query1 + " | Итоговая строка для вставки значений в БД");

        insertRow(query1);
    }

    //Метод вычисляющий колличество строк в БД
    public Integer countDB() {
        String query = "SELECT COUNT (*) FROM list_arrays";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery())  {
            rs.next();
            count = rs.getInt(1);

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return count;
    }


    public void countColumn() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement("SELECT * FROM list_arrays");
             ResultSet rs = ps.executeQuery()) {
            ResultSetMetaData rsmd = rs.getMetaData();
            columns = rsmd.getColumnCount();
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void columnName() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement("SELECT * FROM list_arrays");
             ResultSet rs = ps.executeQuery()) {
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

            StringBuilder e = new StringBuilder();
            for (int i = 1; i < nowColumns + 1; i++) {
                e.append(nameColumn.get(i)).append(", ");
            }
            StringBuilder sb = new StringBuilder(e.toString());
            sb.deleteCharAt(sb.length() - 2);
            e = new StringBuilder(sb.toString());
            query = e.toString().trim();
            System.out.println(query + " | Строка стобцов для INSERT");
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void alterTable() {
        if (nowColumns >= columns) {
            int create = nowColumns - columns;
            create = create + 1;
            System.out.println("created columns = " + create);
            Integer[] clmns = new Integer[create];

            for (int i = 0; i < clmns.length; i++) {
                columns++;
                clmns[i] = columns;
                String sql = "ALTER TABLE list_arrays ADD COLUMN column_" + clmns[i] + " varchar(10)";

                try (Connection con = DriverManager.getConnection(url, user, password);
                     PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.execute();

                } catch (SQLException ex) {
                    Logger lgr = Logger.getLogger(Model.class.getName());
                    lgr.log(Level.SEVERE, ex.getMessage(), ex);
                }
            }
        } else {
            System.out.println("Колличество столбцов хватает для количества элементов массива");
        }
    }

    public void insertRow(String query) {
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(query))
              {
                  ps.execute();
                  System.out.println("Query done! Check it");
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        query1 = "";
        d = "";
        e = "";
    }

    //Метод возвращающий массивы из Базы Данных массивов
    public String[] getDBid(Integer id){
        countColumn();
        System.out.println(columns + " | Колличество столбцов");

        String sql = "SELECT * FROM list_arrays WHERE id = " + id;

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            getArray = new String[columns+1];

            while (rs.next()) {
                for(int t = 2; t < getArray.length; t++){
                    getArray[t] = rs.getString(t);
                }
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return getArray;
    }
}

