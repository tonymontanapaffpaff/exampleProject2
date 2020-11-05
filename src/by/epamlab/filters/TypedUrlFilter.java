package by.epamlab.filters;

import by.epamlab.constants.ConstantsJSP;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "TypedUrlFilter", value = {"/start", "/login", "/register", "/logout", "/main"})
public class TypedUrlFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String refferer = httpRequest.getHeader(ConstantsJSP.HTTP_REQUEST_HEADER_REFERER);
        if (refferer == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath());
            return;
        }
        filterChain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
