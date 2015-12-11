package ua.com.juja.sqlcmd.controller;

/**
 * Created by oleksandr.baglai on 11.12.2015.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.service.Service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private Service service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "redirect:/menu";
    }

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help() {
        return "help";
    }

    @RequestMapping(value = "/connect", method = RequestMethod.GET)
    public String connect(HttpSession session) {
        if (getManager(session) == null) {
            return "connect";
        } else {
            return "menu";
        }
    }

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public String connecting(HttpServletRequest request, HttpSession session) {
        String databaseName = request.getParameter("dbname");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            DatabaseManager manager = service.connect(databaseName, userName, password);
            session.setAttribute("db_manager", manager);
            return "redirect:/menu";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", e.getMessage());
            return "error";
        }
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public String find(HttpServletRequest request, HttpSession session) {
        DatabaseManager manager = getManager(session);

        if (manager == null) { // TODO how to remove duplicate
            return "redirect:connect";
        }

        String tableName = request.getParameter("table");
        request.setAttribute("table", service.find(manager, tableName));

        return "find";
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String menu(HttpServletRequest request) {
        request.setAttribute("items", service.commandsList());
        return "menu";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, HttpSession session) {
        DatabaseManager manager = getManager(session);

        if (manager == null) {
            return "redirect:connect";
        }

        request.setAttribute("list", service.tables(manager));
        return "list";
    }

    private DatabaseManager getManager(HttpSession session) {
        return (DatabaseManager) session.getAttribute("db_manager");
    }
}
