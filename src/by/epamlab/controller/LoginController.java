package by.epamlab.controller;

import by.epamlab.constants.ConstantsJSP;
import by.epamlab.exceptions.DaoException;
import by.epamlab.exceptions.ValidationException;
import by.epamlab.ifaces.UserDAO;
import by.epamlab.model.beans.User;
import by.epamlab.model.factories.UserFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends AbstractController {
    private static final long serialVersionUID = 3L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String ERR_VALID = "Wrong login or password ";

        try {
            HttpSession session = request.getSession();

            String login = request.getParameter(ConstantsJSP.LOGIN_KEY);
            String password = request.getParameter(ConstantsJSP.PASS_KEY);
            UserDAO userDAO = UserFactory.getClassFromFactory();
            Optional<User> optUser = userDAO.getUser(login, password);
            User user = optUser.orElseThrow(() -> new ValidationException(ERR_VALID));
            session.setAttribute(ConstantsJSP.USER_KEY, user);
            session.setAttribute(ConstantsJSP.LOGIN_KEY, user.getName());
            session.setAttribute(ConstantsJSP.ID_KEY, user.getId());
            redirectToMain(request, response, ConstantsJSP.PARAM_TODAY_VALUE);

        } catch (ValidationException e) {
            forwardWithError(request, response, ConstantsJSP.PAGE_LOGIN, e.getMessage());
        } catch (DaoException e) {
            forwardWithErrorAndLog(request, response, ConstantsJSP.PAGE_INDEX, e);

        }
    }
}
