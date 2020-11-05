package by.epamlab.filters;

import by.epamlab.constants.ConstantsJSP;
import by.epamlab.model.beans.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", value = {"/action", "/main"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute(ConstantsJSP.USER_KEY);
        if (user == null) {
            session.invalidate();
            HttpServletResponse httpResponse =
                    (HttpServletResponse) resp;
            httpResponse.sendRedirect(ConstantsJSP.PAGE_LOGIN);
            return;
        }

        chain.doFilter(req, resp);
    }

}
