package by.epamlab.model.utils;

import by.epamlab.constants.ConstantsJSP;
import by.epamlab.exceptions.ValidationException;
import by.epamlab.service.PoolConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class ActionTaskDB {

    public static void addTask(int userId, Map<String, String[]> parameterMap, String query) throws SQLException {

        final int USER_ID_POS = 1;
        final int DESCRIPTION_POS = 2;
        final int DATE_POS = 3;

        try (Connection cn = PoolConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {

            Date date = Date.valueOf(parameterMap.get(ConstantsJSP.DATE_KEY)[0]);
            String description = parameterMap.get(ConstantsJSP.DESCRIPTION_KEY)[0];

            ps.setInt(USER_ID_POS, userId);
            ps.setString(DESCRIPTION_POS, description);
            ps.setDate(DATE_POS, date);
            ps.executeUpdate();
        }
    }

    public static void operateTask(int userId, Map<String, String[]> parameterMap, String query) throws SQLException, ValidationException {

        final String VALID_ERR = "no selected tasks";
        final int TASK_ID_POS = 1;
        final int USER_ID_POS = 2;

        try (Connection cn = PoolConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {

            Optional<String[]> optIds = Optional.ofNullable(parameterMap.get(ConstantsJSP.ONE_TASK_KEY));

            int[] idsTask = Arrays.stream(optIds.orElseThrow(() -> new ValidationException(VALID_ERR)))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int id : idsTask) {
                ps.setInt(TASK_ID_POS, id);
                ps.setInt(USER_ID_POS, userId);
                ps.executeUpdate();
            }
        }

    }

    public static void deleteAllTasks(int userId, Map<String, String[]> parameterMap, String query) throws SQLException {
        try (Connection cn = PoolConnection.getConnection();
             PreparedStatement ps = cn.prepareStatement(query)) {

            ps.setInt(1, userId);
            ps.executeUpdate();

        }
    }
}
