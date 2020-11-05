package by.epamlab.controller;

import by.epamlab.constants.ConstantsJSP;
import by.epamlab.exceptions.DaoException;
import by.epamlab.exceptions.ValidationException;
import by.epamlab.ifaces.TaskDAO;
import by.epamlab.model.factories.TaskFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ActionController", value = "/action")
public class ActionController extends AbstractController {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String pageView = request.getParameter(ConstantsJSP.VIEW_KEY);

        try {
            HttpSession session = request.getSession();

            int userId = (int) session.getAttribute(ConstantsJSP.ID_KEY);
            String login = (String) session.getAttribute(ConstantsJSP.LOGIN_KEY);
            TaskDAO taskDAO = TaskFactory.getClassFromFactory();
            taskDAO.operateTask(userId, request.getParameterMap());

            redirectToMain(request, response, pageView);

        } catch (DaoException e) {
            forwardWithErrorAndLog(request, response, ConstantsJSP.PAGE_INDEX, e);
        } catch (ValidationException e) {
            redirectToMainWithError(request, response, pageView, e.getMessage());

        }

    }
}
