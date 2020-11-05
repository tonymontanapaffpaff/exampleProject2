package by.epamlab.controller;

import by.epamlab.constants.ConstantsJSP;
import by.epamlab.exceptions.DaoException;
import by.epamlab.exceptions.ValidationException;
import by.epamlab.ifaces.UserDAO;
import by.epamlab.model.beans.User;
import by.epamlab.model.factories.UserFactory;
import by.epamlab.model.utils.InputValidate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@WebServlet(name = "RegisterController", value = "/register")
public class RegisterController extends AbstractController {
    private static final long serialVersionUID = 2L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String ERR_VALID = " has been registered. Please, Sign In or create new user";

        try {
            HttpSession session = request.getSession();

            String login = request.getParameter(ConstantsJSP.LOGIN_KEY);
            String password = request.getParameter(ConstantsJSP.PASS_KEY);
            String confirmPassword = request.getParameter(ConstantsJSP.PASS_CONF_KEY);
            UserDAO userDAO = UserFactory.getClassFromFactory();
            InputValidate.doValidate(password, confirmPassword, login);

            Optional<User> optUser = userDAO.addAndGetUser(login, password);
            User user = optUser.orElseThrow(() -> new ValidationException(login + ERR_VALID));
            session.setAttribute(ConstantsJSP.LOGIN_KEY, user.getName());
            session.setAttribute(ConstantsJSP.ID_KEY, user.getId());
            redirectToMain(request, response, ConstantsJSP.PARAM_TODAY_VALUE);

        } catch (ValidationException e) {
            forwardWithError(request, response, ConstantsJSP.PAGE_REG, e.getMessage());

        } catch (DaoException e) {
            forwardWithErrorAndLog(request, response, ConstantsJSP.PAGE_INDEX, e);

        }

    }
}
