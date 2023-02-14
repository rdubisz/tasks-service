package net.rd.tasks.service.exception;

import net.rd.tasks.service.model.TaskDefinitionModel;

public class TaskDefinitionInvalidException extends RuntimeException {
    public TaskDefinitionInvalidException(TaskDefinitionModel model) {
        super("Task-definition invalid: " + model);
    }
}
