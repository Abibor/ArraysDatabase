package app.servlets;

import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddServlet extends HttpServlet {

    List<String> stringNumberList;
    List<String> pushList;
    List<Integer> numberList;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Model model = Model.getInstance();

        String[] numbers = req.getParameterValues("number");

        boolean check = true;

        String a = "Вы пропустили ввод элемента";

        for (String number : numbers) {
            if (number.isEmpty()) {
                req.setAttribute("myArray", a);
                check = false;
            }
        }

        if(check) {
            stringNumberList = Arrays.asList(numbers);
            numberList = new ArrayList<>();

            for(String s : stringNumberList) numberList.add(Integer.valueOf(s));
            Collections.sort(numberList);
            req.setAttribute("myArray", numberList);
        }

        pushList = new ArrayList<>();
        for(int i : numberList) pushList.add(String.valueOf(i));

        System.out.println(pushList);

        model.start(pushList);

        doGet(req, resp);
    }
}