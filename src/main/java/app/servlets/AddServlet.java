package app.servlets;

import app.entities.Array;
import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AddServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //для отображения принятых значений из http в сервлет
        PrintWriter writer = resp.getWriter();

        //Обращаемся к единственному объекту model
        Model model = Model.getInstance();

        String[] numbers = req.getParameterValues("number");

        List<String> stringNumberList = new ArrayList<>();
        List<Integer> numberList = new ArrayList<>();

        /*
        try {
            writer.println("<h4>Courses</h4>");
            for(String course: numbers)
                writer.println("<li>" + course + "</li>");
        } finally {
            writer.close();
        }*/
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
            for(String s : stringNumberList) numberList.add(Integer.valueOf(s));

            Collections.sort(numberList);

            //model.addDB(numberList);

            req.setAttribute("myArray", numberList);
        }

        /*
        //добавляем объект массива array в БД
        model.add(array);

        //myArray = array.getArray();
        String a = "Работает";
        req.setAttribute("myArray", a);*/

        doGet(req, resp);
    }
}