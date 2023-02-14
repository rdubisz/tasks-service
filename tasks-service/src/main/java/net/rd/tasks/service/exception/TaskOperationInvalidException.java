package net.rd.tasks.service.exception;

import net.rd.tasks.service.model.TaskOperationModel;

public class TaskOperationInvalidException extends RuntimeException {
    public TaskOperationInvalidException(TaskOperationModel model) {
        super("Task-operation invalid: " + model);
    }
}
