package net.rd.tasks.service.exception;

public class TaskDefinitionEntityNotFoundException extends RuntimeException {
    public TaskDefinitionEntityNotFoundException(Long id) {
        super("Task-definition id(" + id + ") does not exist");
    }
}
