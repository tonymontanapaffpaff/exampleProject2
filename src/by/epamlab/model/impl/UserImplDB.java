package by.epamlab.model.impl;

import by.epamlab.constants.ConstantsJSP;
import by.epamlab.constants.ConstantsSQL;
import by.epamlab.exceptions.DaoException;
import by.epamlab.ifaces.UserDAO;
import by.epamlab.model.beans.User;
import by.epamlab.service.PoolConnection;

import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserImplDB implements UserDAO {

    public UserImplDB(ResourceBundle rb) {

            String url = rb.getString("db.name");
            String user = rb.getString("db.user");
            String password = rb.getString("db.password");

            PoolConnection.init(url, user, password);
    }

    @Override
    public Optional<User> getUser(String login, String password) throws DaoException {

        try (
                Connection cn = PoolConnection.getConnection();
                PreparedStatement ps = cn.prepareStatement(ConstantsSQL.SELECT_REGISTERED_USER)
        ) {

            Optional<User> optUser = Optional.empty();

            ps.setString(ConstantsJSP.USER_LOGIN_GET_INDEX, login);
            ps.setString(ConstantsJSP.USER_PASSWORD_GET_INDEX, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt(ConstantsJSP.USER_ID_GET_INDEX);
                    String userName = rs.getString(ConstantsJSP.USER_NAME_GET_INDEX);
                    optUser = Optional.of(new User(userId, userName));
                }
                return optUser;
            }
        } catch (SQLException e) {
            throw new DaoException(ConstantsJSP.DAO_EXCEPTION_MESSAGE + e);
        }
    }

    @Override
    public Optional<User> addAndGetUser(String login, String password) throws DaoException {

        try (Connection cn = PoolConnection.getConnection();
             PreparedStatement psSelect = cn.prepareStatement(ConstantsSQL.SELECT_CHECKING_USER);
             PreparedStatement psInsert = cn.prepareStatement(ConstantsSQL.INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)
        ) {
            Optional<User> optUser = Optional.empty();

            psSelect.setString(ConstantsJSP.USER_CHECK_NAME_INDEX, login);
            psInsert.setString(ConstantsJSP.USER_LOGIN_INSERT_INDEX, login);
            psInsert.setString(ConstantsJSP.USER_PASSWORD_INSERT_INDEX, password);

            synchronized (this) {
                try (ResultSet rs = psSelect.executeQuery()) {
                    if (!rs.next()) {
                        psInsert.executeUpdate();
                    }
                }
            }
            try (ResultSet rsUpdate = psInsert.getGeneratedKeys()) {
                if (rsUpdate.next()) {
                    optUser = Optional.of(new User(
                            rsUpdate.getInt(ConstantsJSP.USER_ID_INDEX), login));
                }
                return optUser;
            }
        } catch (SQLException e) {
            throw new DaoException(ConstantsJSP.DAO_EXCEPTION_MESSAGE, e);
        }
    }
}
