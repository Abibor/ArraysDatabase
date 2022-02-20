package app.entities;

import app.model.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculation {

    private static String d = "";
    private static int columns;
    public static int nowColumns;

    public static void main(String [] args) throws SQLException {
        List<String> stringNumberList = new ArrayList<>();
        List<Integer> numberList = new ArrayList<>();

        stringNumberList.add("2");
        stringNumberList.add("22");
        stringNumberList.add("222");
        stringNumberList.add("2222");
        stringNumberList.add("-2");
        //stringNumberList.add("5");
        //stringNumberList.add("6");
        //stringNumberList.add("7");
        //stringNumberList.add("7");
        //stringNumberList.add("7");
        //stringNumberList.add("7");

        System.out.println(stringNumberList);
        nowColumns = stringNumberList.size();
        System.out.println(nowColumns);

        for (int i = 0; i < stringNumberList.size(); i++) {
            d = d + stringNumberList.get(i) + ", ";
        }
        System.out.println(d);

        StringBuilder sb = new StringBuilder(d);
        sb.deleteCharAt(sb.length()-2);
        d = sb.toString();
        String e = d.trim();

        System.out.println(e);

        //String query = "INSERT INTO list_arrays(first_number, second_number, third_number, fourth_number, fifth_number) VALUES ("+ e + ")";
        String query = "INSERT INTO list_arrays VALUES ("+ e + ")";
        //String query = "INSERT INTO table_name VALUES ("+ e + ")";

        System.out.println(query);

        for(String s : stringNumberList) numberList.add(Integer.valueOf(s));
        Collections.sort(numberList);

        System.out.println(numberList);

        TableOperations.countColumn();
        columns = TableOperations.columns;
        System.out.println("columns = " + columns);

        //TableOperations.alterTable();
        //TableOperations.insertRow(query);
        TableOperations.columnName();

    }
}
