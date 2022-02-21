<%--
  Created by IntelliJ IDEA.
  User: array
  Date: 11.02.2022
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new array</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Sort Array Project!</h1>
</div>

<div class="w3-container w3-padding">
    <%
        if (request.getAttribute("myArray") != null) {
            out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">×</span>\n" +
                    "   <h5>" + request.getAttribute("myArray") + "</h5>\n" +
                    "</div>");
        }

    %>
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Add elements to database!</h2>
        </div>

        <form method="post" class="w3-selection w3-hover-light-gray w3-padding">

            <style>
                ul.hr {
                    /* Обнуляем значение отступов и полей */
                    margin: 5px;
                    padding: 0;
                }

                ul.hr li {
                    display: inline-block; /* Строчно-блочный элемент */
                    padding: 3px; /* Поля вокруг текста */
                }
            </style>

            <ul class="hr">
                <div id="area">
                    <li>
                        <input type="number" name="number" class="w3-input w3-animate-input w3-border w3-round-large"/>
                    </li>
                    <li>
                        <input type="number" name="number" class="w3-input w3-animate-input w3-border w3-round-large"/>
                    </li>
                    <li>
                        <input type="number" name="number" class="w3-input w3-animate-input w3-border w3-round-large"/>
                    </li>
                    <li>
                        <input type="number" name="number" class="w3-input w3-animate-input w3-border w3-round-large"/>
                    </li>
                    <li>
                        <input type="number" name="number" class="w3-input w3-animate-input w3-border w3-round-large"/>
                    </li>
                </div>
            </ul>

            <script type="text/javascript">
                var e = document.getElementById('area');

                function add() {
                    var nodet = document.createElement('li');
                    nodet.innerHTML = "<input type='number' name='number' class = 'w3-input w3-animate-input w3-border w3-round-large'>";
                    e.appendChild(nodet);
                }

                function remove() {
                    var nodet = document.getElementsByTagName('li');
                    var q = e.getElementsByTagName('input');
                    if (q.length > 1) {
                        q[q.length - 1].parentNode.removeChild(q[q.length - 1]);
                        nodet[nodet.length - 1].parentNode.removeChild(nodet[nodet.length - 1]);
                    }
                }
            </script>

            <div class="buttons">
                <input type="button" value="ADD" class="w3-btn w3-light-blue w3-round-large w3-margin-bottom"
                       onclick="add();"/>
                <input type="button" value="DEL" class="w3-btn w3-light-blue w3-round-large w3-margin-bottom"
                       onclick="remove();"/>
            </div>

            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Submit</button>
        </form>

    </div>
</div>


<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-black w3-round-large" onclick="location.href='/'">Back to main</button>
</div>

</body>
</html>

