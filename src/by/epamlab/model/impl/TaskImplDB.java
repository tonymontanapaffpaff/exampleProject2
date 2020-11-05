package by.epamlab.model.impl;

import by.epamlab.constants.ConstantsJSP;
import by.epamlab.exceptions.DaoException;
import by.epamlab.exceptions.ValidationException;
import by.epamlab.ifaces.TaskDAO;
import by.epamlab.model.beans.Task;
import by.epamlab.model.enums.Operations;
import by.epamlab.model.enums.PageViews;
import by.epamlab.service.PoolConnection;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class TaskImplDB implements TaskDAO {


    public TaskImplDB(ResourceBundle resourceBundle) {
    }

    @Override
    public List<Task> getTasks(int userId, String view) throws DaoException {

        final int ID_POS = 1;
        final int DESC_POS = 2;
        final int DATE_POS = 3;

        List<Task> tasks = new ArrayList<>();

        try (
                Connection cn = PoolConnection.getConnection();
                PreparedStatement psSelectTasks = cn.prepareStatement(PageViews.valueOf(view.toUpperCase()).getQuery())
        ) {
            psSelectTasks.setInt(ID_POS, userId);

            try (ResultSet rs = psSelectTasks.executeQuery()) {
                while (rs.next()) {
                    int idTask = rs.getInt(ID_POS);
                    String description = rs.getString(DESC_POS);
                    Date date = rs.getDate(DATE_POS);
                    tasks.add(new Task(idTask, description, date));
                }
            }
            return tasks;
        } catch (SQLException e) {
           throw new DaoException(ConstantsJSP.DAO_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public void operateTask(int userId, Map<String, String[]> parameterMap) throws DaoException, ValidationException {
        try {

            Operations operation = Operations.valueOf(parameterMap.get(ConstantsJSP.TYPE_OPERATION_KEY)[0].toUpperCase());
            String query = operation.getQuery();
            operation.getActFunction().handleTask(userId, parameterMap, query);

        } catch (SQLException e) {
            throw new DaoException(ConstantsJSP.DAO_EXCEPTION_MESSAGE, e);
        }
    }
}
