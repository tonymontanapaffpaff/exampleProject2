package by.epamlab.controller;

import by.epamlab.constants.ConstantsJSP;
import by.epamlab.exceptions.DaoException;
import by.epamlab.ifaces.TaskDAO;
import by.epamlab.model.beans.Task;
import by.epamlab.model.enums.Operations;
import by.epamlab.model.enums.PageViews;
import by.epamlab.model.factories.TaskFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "TaskController", value = "/main")
public class MainController extends AbstractController {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();

            String errorMassage = request.getParameter(ConstantsJSP.PARAM_ERROR_KEY);
            String pageView = request.getParameter(ConstantsJSP.VIEW_KEY);
            Set<Operations> operations = PageViews.valueOf(pageView.toUpperCase()).getOperations();

            int userId = (int) session.getAttribute(ConstantsJSP.ID_KEY);
            TaskDAO taskDAO = TaskFactory.getClassFromFactory();
            List<Task> tasks = taskDAO.getTasks(userId, pageView);

            request.setAttribute(ConstantsJSP.TASKS_KEY, tasks);
            request.setAttribute(ConstantsJSP.VIEW_KEY, pageView);
            request.setAttribute(ConstantsJSP.OPERATIONS_KEY, operations);
            request.setAttribute(ConstantsJSP.PARAM_ERROR_KEY, errorMassage);

            forwardWithUrl(request, response, ConstantsJSP.PAGE_MAIN);

        } catch (DaoException e) {
            forwardWithErrorAndLog(request, response, ConstantsJSP.PAGE_INDEX, e);
        }
    }
}
