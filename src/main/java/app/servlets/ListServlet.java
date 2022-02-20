package app.servlets;

import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/list.jsp");
        requestDispatcher.forward(req, resp);

        Model model = Model.getInstance();
        Integer count = model.countDB();
        req.setAttribute("count", count);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Обращаемся к динственному объекту model
        Model model = Model.getInstance();

        //Вытащим из запроса параметры
        String idNumber = req.getParameter("id");
        int id = Integer.parseInt(idNumber);

        if(!idNumber.isEmpty()){

            //метод возвращающий отсортированный массив
            String[] arrays = model.getDBid(id);
            req.setAttribute("arrays", arrays);
        }
        doGet(req, resp);
    }

}