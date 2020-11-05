package by.epamlab.model.enums;

import static by.epamlab.constants.ConstantsSQL.*;
import static by.epamlab.constants.ConstantsJSP.*;
import by.epamlab.ifaces.ActFunction;
import by.epamlab.model.utils.ActionTaskDB;


public enum Operations {
    ADD(
            INSERT_NEW_TASK, ActionTaskDB::addTask, ADD_TYPE_VALUE
    ),
    FIX(
            UPDATE_FIXED_TASKS, ActionTaskDB::operateTask, FIX_TYPE_VALUE
    ),
    UNFIX(
            UNFIX_TASKS, ActionTaskDB::operateTask, UNFIX_TYPE_VALUE
    ),
    REMOVE(
            UPDATE_RECYCLED_TASKS, ActionTaskDB::operateTask, REMOVE_TYPE_VALUE
    ),
    DELETE(
            REMOVE_RECYCLED_TASKS, ActionTaskDB::operateTask, DELETE_TYPE_VALUE
    ),
    DELETE_ALL(
            REMOVE_ALL_RECYCLED_TASKS, ActionTaskDB::deleteAllTasks, ALL_DELETE_TYPE_VALUE
    ),
    RESTORE(
            RESTORE_RECYCLED_TASKS, ActionTaskDB::operateTask, RESTORE_TYPE_VALUE
    );

    private String query;
    private String operationName;
    private ActFunction actFunction;

    Operations(String query, ActFunction actFunction, String operationName) {
        this.query = query;
        this.actFunction = actFunction;
        this.operationName = operationName;
    }

    public String getQuery() {
        return query;
    }

    public ActFunction getActFunction() {
        return actFunction;
    }

    public String getOperationName() {
        return operationName;
    }
}
