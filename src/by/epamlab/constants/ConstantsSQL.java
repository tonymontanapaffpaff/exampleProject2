package by.epamlab.constants;

public class ConstantsSQL {

    public static String SELECT_USERS = "SELECT * FROM users";
    public static String SELECT_REGISTERED_USER = "SELECT userId, name FROM users WHERE name=? AND password=?";
    public static String SELECT_CHECKING_USER = "SELECT userId, name FROM users WHERE name=?";
    public static String INSERT_NEW_USER = "INSERT INTO users(name, password) VALUES (?, ?)";

    public static String SELECT_TASKS_WITH_ID = "SELECT taskId, description, doDate from tasks where idUser=?";

    public static String SELECT_CURRENT_TASKS = "SELECT taskId, description, doDate " +
            "from tasks where doDate <= CURRENT_DATE() and isFix=0 and isRecycle=0 and idUser=?";
    public static String SELECT_TOMORROW_TASKS = "SELECT taskId, description, doDate " +
            "from tasks where doDate = (CURRENT_DATE() + 1) and isFix=0 and isRecycle=0 and idUser=?";
    public static String SELECT_SOMEDAY_TASKS = "SELECT taskId, description, doDate " +
            "from tasks where doDate > (CURRENT_DATE() + 1) and isFix=0 and isRecycle=0 and idUser=?";
    public static String SELECT_FIXED_TASKS = "SELECT taskId, description, doDate " +
            "from tasks where isFix = true and isRecycle = false and idUser=?";
    public static String SELECT_RECYCLE_TASKS = "SELECT taskId, description, doDate " +
            "from tasks where isRecycle = true and idUser=?";

    public static String INSERT_NEW_TASK = "INSERT INTO `tasks`(`idUser`, `description`,`doDate`) VALUES (?, ?, ?)";

    public static final String UPDATE_FIXED_TASKS = "UPDATE `tasks` SET `isFix`=1 WHERE `taskId`=? and idUser=?";
    public static final String UPDATE_RECYCLED_TASKS = "UPDATE `tasks` SET `isRecycle`=1 WHERE `taskId`=? and idUser=?";
    public static final String REMOVE_RECYCLED_TASKS = "DELETE FROM `tasks` WHERE `taskId`=? and idUser=?";
    public static final String RESTORE_RECYCLED_TASKS = "UPDATE `tasks` SET `isFix`=0,`isRecycle`=0 WHERE `taskId`=? and idUser=?";
    public static final String REMOVE_ALL_RECYCLED_TASKS = "DELETE FROM `tasks` WHERE `isRecycle`=1 and idUser=?";
    public static final String UNFIX_TASKS = "UPDATE `tasks` SET `isFix`=0 WHERE `taskId`=? and idUser=?";
}
