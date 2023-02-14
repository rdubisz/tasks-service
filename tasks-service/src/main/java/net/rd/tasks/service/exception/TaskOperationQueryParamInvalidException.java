package net.rd.tasks.service.exception;

import net.rd.tasks.service.model.TaskOperationQueryParamModel;

public class TaskOperationQueryParamInvalidException extends RuntimeException {
    public TaskOperationQueryParamInvalidException(TaskOperationQueryParamModel model) {
        super("Query-parameter invalid: " + model);
    }
}
