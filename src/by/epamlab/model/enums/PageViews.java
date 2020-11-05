package by.epamlab.model.enums;

import by.epamlab.constants.ConstantsJSP;
import by.epamlab.constants.ConstantsSQL;

import java.util.EnumSet;
import java.util.Set;

public enum PageViews {
    TODAY(
            ConstantsSQL.SELECT_CURRENT_TASKS,
            EnumSet.of(Operations.ADD, Operations.FIX, Operations.REMOVE),
            ConstantsJSP.PARAM_TODAY_VALUE
    ),
    TOMORROW(
            ConstantsSQL.SELECT_TOMORROW_TASKS,
            EnumSet.of(Operations.ADD, Operations.FIX, Operations.REMOVE),
            ConstantsJSP.PARAM_TOMORROW_VALUE
    ),
    SOMEDAY(
            ConstantsSQL.SELECT_SOMEDAY_TASKS,
            EnumSet.of(Operations.ADD, Operations.FIX, Operations.REMOVE),
            ConstantsJSP.PARAM_SOMEDAY_VALUE
    ),
    FIXED(
            ConstantsSQL.SELECT_FIXED_TASKS,
            EnumSet.of(Operations.UNFIX, Operations.REMOVE),
            ConstantsJSP.PARAM_FIXED_VALUE
    ),
    RECYCLE(
            ConstantsSQL.SELECT_RECYCLE_TASKS,
            EnumSet.of(Operations.DELETE, Operations.DELETE_ALL, Operations.RESTORE),
            ConstantsJSP.PARAM_RECYCLE_VALUE
    );

    private String query;
    private Set<Operations> operations;
    private String name;

    PageViews(String query, Set<Operations> operations, String name) {
        this.query = query;
        this.operations = operations;
        this.name = name;
    }

    public String getQuery() {
        return query;
    }

    public Set<Operations> getOperations() {
        return operations;
    }

    public String getName() {
        return name;
    }
}
