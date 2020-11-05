package by.epamlab.model.factories;

import by.epamlab.exceptions.InitException;
import by.epamlab.ifaces.UserDAO;
import by.epamlab.model.impl.UserImplDB;

import java.util.ResourceBundle;
import java.util.function.Function;

public class UserFactory {

    private enum Sources {
        DB (UserImplDB::new);

        private final Function<ResourceBundle, UserDAO> function;

        Sources(Function<ResourceBundle, UserDAO> function) {
            this.function = function;
        }

        public UserDAO getImpl(ResourceBundle rb) {
            return function.apply(rb);
        }
    }

    private static UserDAO userImpl;

    public static void init(ResourceBundle rb) {
        String userImplName = rb.getString("factory.users");
        Sources source = Sources.valueOf(userImplName.toUpperCase());
        userImpl = source.getImpl(rb);
    }

    public static UserDAO getClassFromFactory() {
        return userImpl;
    }

}
