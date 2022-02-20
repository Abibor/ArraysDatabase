package app.model;

import app.entities.Array;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {
    private static final Model instance = new Model();

    //доступ к БД
    String url = "jdbc:postgresql://localhost:5432/database_arrays";
    String user = "postgres";
    String password = "453659";

    String[] getArray;
    int count = 0;

    //List для хранения
    List<String> number;

    String select;

    String a;
    String b;
    String c;
    String d;
    String e;

    public static Model getInstance() {
        return instance;
    }

    private Model() {

    }

    //Метод вычисляющий колличество строк в БД
    public Integer countDB() {

        String query = "SELECT COUNT (*) from list_arrays";

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

    //Метод сохраняющий элементы массива в БД
    private void addDB(List<Integer> numberList) {

        for (Integer n : numberList) number.add(String.valueOf(n));
        for (String n : number) {
            select = n + ", ";
        }

        String query = "INSERT INTO list_arrays(first_number, second_number, third_number, fourth_number, fifth_number) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, a);
            pst.setString(2, b);
            pst.setString(3, c);
            pst.setString(4, d);
            pst.setString(5, e);
            pst.executeUpdate();

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void countColumn(){
        String sql = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {



        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    //Метод возвращающий массивы по id Базы Данных массивов
    public String[] getDBid(Integer id){
        String sql = "SELECT * FROM list_arrays WHERE id = " + id;

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                getArray = new String[]{rs.getString("first_number") + ", " + rs.getString("second_number") + ", " + rs.getString("third_number") + ", " + rs.getString("fourth_number") + ", " + rs.getString("fifth_number")};
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Model.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return getArray;
    }
}

