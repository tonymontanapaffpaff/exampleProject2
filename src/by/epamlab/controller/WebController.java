package by.epamlab.controller;

import by.epamlab.constants.ConstantsJSP;
import by.epamlab.exceptions.InitException;
import by.epamlab.model.factories.TaskFactory;
import by.epamlab.model.factories.UserFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "WebController", value = "/start",
        initParams = {
                @WebInitParam(name = ConstantsJSP.PROPS_NAME_KEY, value = ConstantsJSP.PROPS_NAME_VALUE)
        },
        loadOnStartup = 1
)
public class WebController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final static Logger LOGGER = Logger.getLogger(WebController.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            String propsName = config.getInitParameter(ConstantsJSP.PROPS_NAME_KEY);
            ResourceBundle rb = ResourceBundle.getBundle(propsName);
            UserFactory.init(rb);
            TaskFactory.init(rb);

        } catch (InitException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            throw new ServletException(e);
        }
    }
}
