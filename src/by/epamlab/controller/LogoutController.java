package by.epamlab.controller;

import by.epamlab.constants.ConstantsJSP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutController", value = "/logout")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 4L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();

        //redirect to home page
        response.sendRedirect(request.getContextPath() + ConstantsJSP.PAGE_HOME);
    }
}
