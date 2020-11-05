package by.epamlab.controller;

import by.epamlab.constants.ConstantsJSP;
import by.epamlab.service.LoggerWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AbstractController")
public class AbstractController extends HttpServlet {

    protected void forwardWithUrl(HttpServletRequest request, HttpServletResponse response,
                                  String url) throws ServletException, IOException
    {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

    protected void forwardWithError(HttpServletRequest request, HttpServletResponse response,
                                    String url, String errorMessage) throws ServletException, IOException
    {
        request.setAttribute(ConstantsJSP.PARAM_ERROR_KEY, errorMessage);
        forwardWithUrl(request, response, url);
    }

    protected void forwardWithErrorAndLog(HttpServletRequest request, HttpServletResponse response,
                                          String url, Exception e) throws ServletException, IOException
    {
        LoggerWriter.writeError(this.getClass(), e);
        request.setAttribute(ConstantsJSP.PARAM_ERROR_KEY, e.getMessage());
        forwardWithUrl(request, response, url);
    }

    protected void redirectToMain(HttpServletRequest request, HttpServletResponse response,
                                  String pageView) throws IOException
    {
        response.sendRedirect(request.getContextPath() + ConstantsJSP.SERVLET_MAIN + "?" +
                ConstantsJSP.VIEW_KEY + "=" + pageView);
    }

    protected void redirectToMainWithError(HttpServletRequest request, HttpServletResponse response,
                                           String pageView, String errorMessage) throws IOException
    {
        response.sendRedirect(request.getContextPath() + ConstantsJSP.SERVLET_MAIN + "?" +
                ConstantsJSP.VIEW_KEY + "=" + pageView + "&" +
                ConstantsJSP.PARAM_ERROR_KEY + "=" + errorMessage);
    }
}
