<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>SQLCmd</title>
    </head>
    <body>
        <a href="help">Help</a><br>
        <a href="connect">Connect</a><br>
        <a href="celar">Clear</a><br>
        <a href="create">Create</a><br>
        <a href="find">Find</a><br>
        <a href="tables">Tables</a><br>
        <a href="exit">Exit</a><br>
    </body>
</html>


<!--public class MainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        resp.getOut().print("\n<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>\n<html><head><title>SQLCmd</title></head>    <body>        Hello World	    ";
        for (Item item : menuItems) {
            resp.getOut().print("<a href="/sqlcmd/projects?category=${" + item.id + "}">" + item.name + """/></a>";
        }
        resp.getOut().print("</body></html>");
    }
}-->