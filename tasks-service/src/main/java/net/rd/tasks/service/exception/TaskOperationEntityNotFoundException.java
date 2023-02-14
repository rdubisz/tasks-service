package net.rd.tasks.service.exception;

public class TaskOperationEntityNotFoundException extends RuntimeException {
    public TaskOperationEntityNotFoundException(Long id) {
        super("Task-operation id(" + id + ") does not exist");
    }
}
