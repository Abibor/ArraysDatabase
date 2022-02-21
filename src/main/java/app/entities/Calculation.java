package app.entities;

import app.model.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculation {

    private static String d = "";
    private static int columns1;
    public static int nowColumns;
    public static String query1;

    public static void main(String [] args) throws SQLException {
        List<String> stringNumberList = new ArrayList<>();
        List<Integer> numberList = new ArrayList<>();

        stringNumberList.add("2");
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

        TableOperations.countColumn();
        columns1 = TableOperations.columns;
        System.out.println(columns1 + " | Количество столбцов в БД");

        TableOperations.alterTable();
        TableOperations.columnName();

        query1 = "INSERT INTO list_arrays (" + TableOperations.query + ") VALUES ("+ e + ")";
        //String query = "INSERT INTO table_name VALUES ("+ e + ")";

        System.out.println(query1 + " | Итоговая строка для вставки значений в БД");

        TableOperations.insertRow(query1);

    }
}
