package by.epamlab.service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerWriter {

    public static void writeError(Class<?> forClass, Exception e) {

        Logger logger = Logger.getLogger(forClass.getName());
        logger.log(Level.SEVERE, e.toString(), e);

    }

}
