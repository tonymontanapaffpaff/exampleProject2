package by.epamlab.ifaces;

import by.epamlab.exceptions.DaoException;
import by.epamlab.exceptions.InitException;
import by.epamlab.model.beans.User;

import java.util.Map;
import java.util.Optional;

public interface UserDAO {

    Optional<User> getUser(String login, String password) throws DaoException;

    Optional<User> addAndGetUser(String login, String password) throws DaoException;
}
