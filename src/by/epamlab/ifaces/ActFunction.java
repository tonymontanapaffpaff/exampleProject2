package by.epamlab.ifaces;

import by.epamlab.exceptions.ValidationException;

import java.sql.SQLException;
import java.util.Map;

public interface ActFunction {
    void handleTask(int userId, Map<String, String[]> parameterMap, String query) throws SQLException, ValidationException;
}
