package by.epamlab.ifaces;

import by.epamlab.exceptions.DaoException;
import by.epamlab.exceptions.ValidationException;
import by.epamlab.model.beans.Task;

import java.util.List;
import java.util.Map;

public interface TaskDAO {
    List<Task> getTasks(int userId, String period) throws DaoException;

    void operateTask(int userId, Map<String, String[]> parameterMap) throws DaoException, ValidationException;
}
