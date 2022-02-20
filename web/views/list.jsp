<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: array
  Date: 11.02.2022
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Arrays" %>
<%@ page import="app.model.Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Arrays list</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Sort Array Project!</h1>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-light-blue">
            <h2>Вывод сохраненного массива. Всего элементов: <%
                Model model = Model.getInstance();
                int count = model.countDB();
                out.println(count); %></h2>
        </div>

        <form method="post" class="w3-selection w3-hover-light-gray w3-padding">
            <p align="center"><input type="text" name="id" class="w3-input w3-animate-input w3-border w3-round-large"
                                     style="width: 30%"></p>

            <button type="submit" class="w3-btn w3-light-blue w3-round-large w3-margin-bottom">Submit</button>
        </form>


        <%
            String[] arrays = (String[]) request.getAttribute("arrays");

            if (arrays != null) {
                out.println("<ul class=\"w3-ul\">");
                out.println("<li class=\"w3-hover-sand\">" + Arrays.toString(arrays) + "</li>");
                out.println("</ul>");

            } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                    +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                    "   <h5>Введите идентификатор массива!</h5>\n" +
                    "</div>");


        %>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-black w3-round-large" onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>
