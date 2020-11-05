package by.epamlab.model.factories;

import by.epamlab.exceptions.InitException;
import by.epamlab.ifaces.TaskDAO;
import by.epamlab.ifaces.UserDAO;
import by.epamlab.model.impl.TaskImplDB;

import java.util.ResourceBundle;
import java.util.function.Function;

public class TaskFactory {

    private enum Sources {
        DB(TaskImplDB::new);

        private final Function<ResourceBundle, TaskDAO> function;

        Sources(Function<ResourceBundle, TaskDAO> function) {
            this.function = function;
        }

        public TaskDAO getImpl(ResourceBundle rb) {
            return function.apply(rb);
        }
    }

    private static TaskDAO taskImpl;

    public static void init(ResourceBundle rb) throws InitException {
        String taskImplName = rb.getString("factory.tasks");
        TaskFactory.Sources source = TaskFactory.Sources.valueOf(taskImplName.toUpperCase());
        taskImpl = source.getImpl(rb);
    }

    public static TaskDAO getClassFromFactory() {
        return taskImpl;
    }

}
